package com.example.yow.easystock;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.yow.easystock.BottomBar.MarketInfo.MarketInfoFragment;
import com.example.yow.easystock.BottomBar.MySelection.AddSelectionActivity;
import com.example.yow.easystock.BottomBar.MySelection.MySelectionFragment;
import com.example.yow.easystock.BottomBar.MyTransaction.MyTransactionFragment;
import com.example.yow.easystock.BottomBar.News.NewsFragment;
import com.example.yow.easystock.DrawerNavigation.About;
import com.example.yow.easystock.DrawerNavigation.AccountManager;
import com.example.yow.easystock.DrawerNavigation.AutoWarning;
import com.example.yow.easystock.DrawerNavigation.GainAndLossToday;
import com.example.yow.easystock.DrawerNavigation.GainAndLossTotal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationBar.OnTabSelectedListener {

    private ArrayList<Fragment> fragments;
    FloatingActionButton fab = null;
    boolean isInWebViewFragment = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //1.floating action button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent addSelection = new Intent(MainActivity.this,AddSelectionActivity.class);
                startActivity(addSelection);
            }
        });

        //2. Drawer Navigation
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //3.Bottom Navigation Bar
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.file, "自选").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.drawable.transaction, "交易").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.market, "行情").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.favorite, "资讯").setActiveColorResource(R.color.green))
                .setFirstSelectedPosition(0)
                .initialise();

        //4.fragment
        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);

    }

    private void setDefaultFragment() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, MySelectionFragment.newInstance());
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(MySelectionFragment.newInstance());
        fragments.add(MyTransactionFragment.newInstance());
        fragments.add(MarketInfoFragment.newInstance());
        fragments.add(NewsFragment.newInstance());
        return fragments;
    }

    //1.bottom navigation bar
    @Override
    public void onTabSelected(int position) {

        if(position == 0)
            fab.show();

        if(position == 3)
            isInWebViewFragment = true;

        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.fragment_container, fragment);
                } else {
                    ft.replace(R.id.fragment_container, fragment);
                }
                ft.commitAllowingStateLoss();
            }
        }
    }
    //1.bottom navigation bar
    @Override
    public void onTabUnselected(int position) {

        if(position == 0)
            fab.hide();

        if(position == 3)
            isInWebViewFragment = false;

        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }
    //1.bottom navigation bar
    @Override
    public void onTabReselected(int position) {

    }

    //Bottom Button back
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            try{
                NewsFragment fragment = (NewsFragment) fragments.get(3);
                if(fragment.canGoBack() && isInWebViewFragment) {
                    // Navigating web page history
                    fragment.goBack();
                }else{
                    super.onBackPressed();
                }
            }catch (Exception e){
                super.onBackPressed();
                //e.printStackTrace();
            }
        }
    }

    //2.toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //2.toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //3.drawer navigation
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Intent callAccountManager = new Intent(MainActivity.this,AccountManager.class);
            startActivity(callAccountManager);

        } else if (id == R.id.nav_gallery) {

            Intent callWarning = new Intent(MainActivity.this,AutoWarning.class);
            startActivity(callWarning);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_selection) {

            Intent callGainLossToday = new Intent(MainActivity.this,GainAndLossToday.class);
            startActivity(callGainLossToday);

        } else if (id == R.id.nav_send) {

            Intent callGainLossTotal = new Intent(MainActivity.this,GainAndLossTotal.class);
            startActivity(callGainLossTotal);

        } else if(id == R.id.nav_about){

            Intent callAbout = new Intent(MainActivity.this,About.class);
            startActivity(callAbout);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
