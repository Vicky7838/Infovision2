package com.android.vicky.infovision;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BottomBar mBottomBar;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    // Tab titles
    TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fab = (FloatingActionButton)findViewById(R.id.fab2);
        mBottomBar = BottomBar.attach(this, savedInstanceState);

        mBottomBar.setItems(R.menu.botto);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                //switch between tabs
                switch (menuItemId) {
                    case R.id.recent_item:
                        break;
                    case R.id.location_item:
                        break;
                    case R.id.favorite_item:
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }

        });


        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);


            }




            private void setupViewPager(ViewPager viewPager) {
                SingleTabFragment data = SingleTabFragment.newInstance("A");
                SingleTabFragment data1 = SingleTabFragment.newInstance("B");
                SingleTabFragment data2 = SingleTabFragment.newInstance("C");
                adapter.addFragment(data, "All");
                adapter.addFragment(data1, "Articles");
                adapter.addFragment(data2, "Posts");
//                Bundle bundle = new Bundle();
                /*bundle.putString("all", adapter.getPageTitle(0).toString());
                bundle.putString("art", adapter.getPageTitle(1).toString());
                data.setArguments(bundle);
                Log.d("tabs",adapter.getPageTitle(0).toString());
                Log.d("tabs",adapter.getPageTitle(1).toString());*/

                viewPager.setAdapter(adapter);
            }


            class TabsPagerAdapter extends FragmentPagerAdapter {
                private final List<Fragment> mFragmentList = new ArrayList<>();
                private final List<String> mFragmentTitleList = new ArrayList<>();

                public TabsPagerAdapter(FragmentManager manager) {
                    super(manager);
                }

                @Override
                public Fragment getItem(int position) {
                    return mFragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return mFragmentList.size();
                }


                public void addFragment(Fragment fragment, String title) {
                    mFragmentList.add(fragment);
                    mFragmentTitleList.add(title);
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return mFragmentTitleList.get(position);
                }
            }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
