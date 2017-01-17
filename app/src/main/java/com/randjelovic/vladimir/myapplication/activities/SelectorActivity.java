package com.randjelovic.vladimir.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListView;
import android.widget.TextView;

import com.randjelovic.vladimir.myapplication.AsyncTasks.SynchDatabase;
import com.randjelovic.vladimir.myapplication.AsyncTasks.TaskListener;
import com.randjelovic.vladimir.myapplication.common.MyApplication;
import com.randjelovic.vladimir.myapplication.expandableadapter.Group;
import com.randjelovic.vladimir.myapplication.expandableadapter.MyExpandableListAdapter;
import com.randjelovic.vladimir.myapplication.R;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.nio.channels.Selector;
import java.util.Arrays;
import java.util.List;

import data.dao.SlideDao;
import data.dao.TestDao;
import data.dto.TestScore;
import data.models.Test;

public class SelectorActivity extends AppCompatActivity implements TaskListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static final String TAG = SelectorActivity.class.getName();
    private Intent starterIntent;
    private ViewPager mViewPager;
    private static TextView testResults;
    private static TextView statisticsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        starterIntent = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_selector, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.syncDb) {
            new SynchDatabase(this).execute("");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public Intent getStarterIntent() {
        return starterIntent;
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";


        SparseArray<Group> groups = new SparseArray<Group>();
        ExpandableListView listView;
        MyExpandableListAdapter adapter;

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView=null;

            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                rootView = inflater.inflate(R.layout.fragment_testing, container, false);
                createData();
                MyExpandableListAdapter adapter = new MyExpandableListAdapter(inflater, groups);
                listView = (ExpandableListView) rootView.findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                rootView = inflater.inflate(R.layout.fragment_results, container, false);
                testResults = (TextView) rootView.findViewById(R.id.testResults);
                testResults.setText(MyApplication.getLastResults());
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 3){
                rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
                statisticsData = (TextView) rootView.findViewById(R.id.statistics_data);
                statisticsData.setText("This is statistics");
            }
            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }



        public void createData() {
            TestDao testDao = new TestDao();
            SlideDao slideDao = new SlideDao();
            List<Test> testList = testDao.getAll();
            Long j=0L;
            for (Test test : testList) {
                Group group = new Group(test.getTestName());
                group.children.add(test.getDescription());
                group.image= BitmapFactory.decodeStream(new ByteArrayInputStream(test.getTestPromoImage()));
//                List<Slide> slideList = slideDao.getAllSlidesByTest(j);
//                for (Slide slide : slideList) {
//                    group.children.add(slide.getSlideName());
//                }
                groups.append(j.intValue(), group);
                j++;
            }
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (testResults != null){testResults.setText(MyApplication.getLastResults());}
            if (statisticsData != null){statisticsData.setText("STATISTICS DATA");}
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "TESTING";
                case 1:
                    return "RESULTS";
                case 2:
                    return "STATISTICS";
            }
            return null;
        }
    }

    public class Statistics extends AsyncTask<String, Integer, String> {
        private final String TAG = this.getClass().getName();
        private final String AUTHENTICATION_HEADER = "Authorization";
        private String results = "";

        @Override
        protected String doInBackground(String... strings) {
            publishProgress(0);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set(AUTHENTICATION_HEADER, MyApplication.getBasicAuth());
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity requestEntity = new HttpEntity (requestHeaders);
            RestTemplate restTemplate = new RestTemplate(true);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<String> responseEntity = null;

            try {
                responseEntity = restTemplate.exchange(MyApplication.getAppContext().getResources().getString(R.string.url_tests)+"/statistics", HttpMethod.GET, requestEntity, String.class);
                results= responseEntity.getBody();
            } catch (Exception e) {
                Log.v(TAG, "Exception: " + e.getMessage());
                throw e;
            }
            return results;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "Progress: " + values[0]);
        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);
            Log.d(TAG, "POST - Result: " + results);
//            textViewResults.setText(results);
            MyApplication.setLastResults(results);
        }
    }
}
