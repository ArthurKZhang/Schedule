package com.arthur.schedule;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.arthur.schedule.OnDoing.OnDoingFragment;
import com.arthur.schedule.OnFinished.FinishedFragment;
import com.arthur.schedule.addevent.AddEventFragment;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FinishedFragment fFrag;
    private OnDoingFragment onDoFrag;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //TODO Add new event action
                FragmentManager fManager = getFragmentManager();
                FragmentTransaction fTrans = fManager.beginTransaction();
                AddEventFragment addNewFrag = new AddEventFragment();
                addNewFrag.setContext(Main2Activity.this);
                fTrans.add(R.id.drawer_layout,addNewFrag);
                fTrans.commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTrans = fManager.beginTransaction();
        onDoFrag = new OnDoingFragment();
        onDoFrag.setContext(this);
        fTrans.add(R.id.fragment_content,onDoFrag);
        fTrans.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction fragTrans = fragManager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.ondoing) {
            // Handle the camera action
            if(onDoFrag==null){
                onDoFrag = new OnDoingFragment();
                onDoFrag.setContext(this);
            }
            fragTrans.replace(R.id.fragment_content,onDoFrag);
            Toast.makeText(Main2Activity.this,"ON Doing",Toast.LENGTH_LONG).show();
        } else if (id == R.id.finished) {
            if (fFrag==null){
                fFrag = new FinishedFragment();
                fFrag.setContext(this);
            }
            fragTrans.replace(R.id.fragment_content,fFrag);

            Toast.makeText(Main2Activity.this,"Finished",Toast.LENGTH_LONG).show();
        }

        navigationView.getMenu().findItem(id).setChecked(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        fragTrans.commit();
        return false;
    }
}
