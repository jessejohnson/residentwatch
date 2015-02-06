package com.jessejojojohnson.residentwatch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.jessejojojohnson.residentwatch.fragments.OnboardingFragment;

import java.util.ArrayList;


public class OnBoardingActivity extends FragmentActivity {

    private static final int NUM_PAGES = 1;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_screen);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        ArrayList<String> textHeadings = new ArrayList<>();
        ArrayList<String> textContent = new ArrayList<>();
        //add stuff

        private ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            textHeadings.add("Make A Difference");
            textHeadings.add("Heading Text 2");
            textHeadings.add("Heading Text 3");
            textHeadings.add("Heading Text 4");
            textHeadings.add("Heading Text 5");

            textContent.add("Take the initiative and report problems in your community to the relevant authorities\n" +
                    "All it takes is a click to get vigilant.");
            textContent.add("Ut enim ad minim veniam,\n" +
                    "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
                    "consequat.");
        }

        @Override
        public Fragment getItem(int i) {
            return OnboardingFragment.newInstance(textHeadings.get(i), textContent.get(i), true);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_on_boarding, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
