package com.randjelovic.vladimir.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
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
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.randjelovic.vladimir.myapplication.AsyncTasks.SynchDatabase;
import com.randjelovic.vladimir.myapplication.AsyncTasks.TaskListener;
import com.randjelovic.vladimir.myapplication.common.MyApplication;
import com.randjelovic.vladimir.myapplication.common.Utility;
import com.randjelovic.vladimir.myapplication.expandableadapter.Group;
import com.randjelovic.vladimir.myapplication.expandableadapter.MyExpandableListAdapter;
import com.randjelovic.vladimir.myapplication.R;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import data.dao.SlideDao;
import data.dao.TestDao;
import data.dto.Statistics;
import data.models.Test;

public class SelectorActivity extends AppCompatActivity implements TaskListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static final String TAG = SelectorActivity.class.getName();
    private Intent starterIntent;
    private ViewPager mViewPager;
    private static TextView testResults;
    private static TextView statisticsData;
    private Intent myProfileIntent;

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
                Snackbar.make(view, getResources().getString(R.string.copyright), Snackbar.LENGTH_LONG)
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
        if (id == R.id.myProfile) {
            myProfileIntent = new Intent(this, MyProfileActivity.class);
            this.startActivity(myProfileIntent);
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
//                statisticsData = (TextView) rootView.findViewById(R.id.statistics_data);
//                statisticsData.setText(MyApplication.getLastStatistics());
                populateTable(rootView);
            }
            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
//            populateTable(view);
            if (testResults != null){testResults.setText(MyApplication.getLastResults());}
//            if (statisticsData != null){statisticsData.setText(MyApplication.getLastStatistics());}
        }



        public void createData() {
            TestDao testDao = new TestDao();
            SlideDao slideDao = new SlideDao();
            List<Test> testList = testDao.getAll();
            Long j=0L;
            for (Test test : testList) {
                Group group = new Group(test.getTestName());
                group.children.add(test.getDescription());
                Bitmap image = BitmapFactory.decodeStream(new ByteArrayInputStream(test.getTestPromoImage()));

                group.image=Bitmap.createScaledBitmap(image, 200, 200, true);

//                List<Slide> slideList = slideDao.getAllSlidesByTest(j);
//                for (Slide slide : slideList) {
//                    group.children.add(slide.getSlideName());
//                }
                groups.append(j.intValue(), group);
                j++;
            }
        }

        private void populateTable(View rootView){
            String stringValue=null;
            TableLayout tableLayout = (TableLayout) rootView.findViewById(R.id.table);
            TextView testName = (TextView) rootView.findViewById(R.id.testName);
            Statistics statistics = MyApplication.getStatistics();

            testName.setText("Test name: "+MyApplication.getSelectedTest().getTestName());

            if (statistics != null){
                List<Field> allFields = listAllFields(statistics);

                for(Field field : allFields){
                    if(!field.getName().contains("$") && !field.getName().equals("testId") && !field.getName().equals("$change")) {
                        TableRow tableRow = new TableRow(rootView.getContext());
                        TextView tvLabel = new TextView(rootView.getContext());
                        TextView tvResult = new TextView(rootView.getContext());

                        field.setAccessible(true);
                        try {
                            Object value = field.get(statistics);
                            if (value != null) {
                                stringValue = value.toString();
                            } else {
                                stringValue = "---";
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        String label = Utility.splitCamelCase(field.getName()).toLowerCase().replace("$","");
                        label = label.substring(label.indexOf("_")<0? 0 : label.indexOf("_"));
                        tvLabel.setText(label);
                        tvResult.setText(stringValue);

                        tableRow.addView(tvLabel);
                        tableRow.addView(tvResult);
                        tableLayout.addView(tableRow);

                        TableRow.LayoutParams resultLayoutParams = (TableRow.LayoutParams) tvResult.getLayoutParams();
                        resultLayoutParams.setMargins(50, 0, 0, 0);
                        tvResult.setLayoutParams(resultLayoutParams);
                    }
                    else if(field.getName().contains("$$")){
                        TableRow tableRow = new TableRow(rootView.getContext());
                        TextView tvLabel = new TextView(rootView.getContext());
                        TextView tvResult = new TextView(rootView.getContext());

                        field.setAccessible(true);
                        try {
                            Object value = field.get(statistics);
                            if (value != null) {
                                stringValue = value.toString();
                            } else {
                                stringValue = "---";
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        String label = Utility.splitCamelCase(field.getName()).toLowerCase().replace("$","");
                        label = label.substring(label.indexOf("_")<0? 0 : label.indexOf("_"));
                        tvLabel.setText(label);
                        tvResult.setText(stringValue);

                        tableRow.addView(tvLabel);
                        tableRow.addView(tvResult);
                        tableLayout.addView(tableRow);

                        tvLabel.setTypeface(null, Typeface.BOLD);
                        tvResult.setTypeface(null, Typeface.BOLD);

                        TableRow.LayoutParams labelLayoutParams = (TableRow.LayoutParams) tvLabel.getLayoutParams();
                        labelLayoutParams.setMargins(0, 50, 0, 0);
                        tvLabel.setLayoutParams(labelLayoutParams);

                        TableRow.LayoutParams resultLayoutParams = (TableRow.LayoutParams) tvResult.getLayoutParams();
                        resultLayoutParams.setMargins(50, 50, 0, 0);
                        tvResult.setLayoutParams(resultLayoutParams);
                    }
                }
            }


            TableRow tableRow = new TableRow(rootView.getContext());
            TextView tvLabel = new TextView(rootView.getContext());
            TextView tvResult = new TextView(rootView.getContext());

            tvLabel.setText("Number of results for test:");
            tvResult.setText("44");

            tableRow.addView(tvLabel);
            tableRow.addView(tvResult);
            tableLayout.addView(tableRow);

            TableRow.LayoutParams resultLayoutParams = (TableRow.LayoutParams) tvResult.getLayoutParams();
            resultLayoutParams.setMargins(50,0,0,0);
            tvResult.setLayoutParams(resultLayoutParams);
        }

        private List<Field> listAllFields(Object obj) {
            List<Field> fieldList = new ArrayList<Field>();
            fieldList.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
            return  fieldList;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (testResults != null){
//                testResults.setText(MyApplication.getLastResults());
            }
            if (statisticsData != null){
//                statisticsData.setText(MyApplication.getLastStatistics());
            }
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
}
