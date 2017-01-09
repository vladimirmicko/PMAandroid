package com.randjelovic.vladimir.myapplication.activities;

import android.app.Application;
import android.content.Intent;
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

import com.randjelovic.vladimir.myapplication.expandableadapter.Group;
import com.randjelovic.vladimir.myapplication.expandableadapter.MyExpandableListAdapter;
import com.randjelovic.vladimir.myapplication.R;

import java.util.List;

import data.dao.SlideDao;
import data.dao.TestDao;
import data.database.DbHelper;
import data.models.Slide;
import data.models.Test;

public class SelectorActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static final String TAG = SelectorActivity.class.getName();


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selector, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent mainIntent = new Intent(SelectorActivity.this, GetTest.class);
            SelectorActivity.this.startActivity(mainIntent);
        }
        if (id == R.id.item_show_picture) {

            Intent mainIntent = new Intent(SelectorActivity.this, ShowPicture.class);
            SelectorActivity.this.startActivity(mainIntent);
//            SelectorActivity.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        SparseArray<Group> groups = new SparseArray<Group>();
        ExpandableListView listView;
        MyExpandableListAdapter adapter;


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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
//                fillDb();
                createData();

                MyExpandableListAdapter adapter = new MyExpandableListAdapter(inflater, groups);
                listView = (ExpandableListView) rootView.findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                rootView = inflater.inflate(R.layout.fragment_results, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 3){
                rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
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
                List<Slide> slideList = slideDao.getAllSlidesByTest(j);
                for (Slide slide : slideList) {
                    group.children.add(slide.getSlideName());
                }
                groups.append(j.intValue(), group);
                j++;
            }
        }

        public void fillDb(){
            TestDao testDao = new TestDao();
            SlideDao slideDao = new SlideDao();
            testDao.getDbHelper().onUpgrade(testDao.getDb(), 1, 2);

            Test test = new Test();
            test.setTestName("Micko 1");
            test.setDescription("Super test");
            Long id = testDao.insert(test);

            Slide slide = new Slide();
            slide.setSlideName("Ovo je slide 1");
            slide.setTestId(0L);
            slideDao.insert(slide);

            slide = new Slide();
            slide.setSlideName("Ovo je slide 2");
            slide.setTestId(0L);
            slideDao.insert(slide);


            List lll = testDao.getAll();
            List s1 = slideDao.getAllSlidesByTest(1L);
//            List s2 = slideDao.getAllSlidesByTest(2L);
//            List s3 = slideDao.getAllSlidesByTest(3L);



            Log.d(TAG, "Number of retrieved tests: " + lll.size());

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
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
