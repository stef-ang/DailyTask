package com.stef_developer.dailytask;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import com.stef_developer.dailytask.database.DatabaseSeeder;
import com.stef_developer.dailytask.fragments.NavigationDrawerFragment;
import com.stef_developer.dailytask.fragments.Setting;
import com.stef_developer.dailytask.fragments.TaskList;
import com.stef_developer.dailytask.fragments.TaskReport;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        TaskReport.OnFragmentInteractionListener,
        TaskList.OnFragmentInteractionListener
{

    private String eMail;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in { restoreActionBar()}.
     */
    private CharSequence mTitle;
    private DatabaseSeeder databaseSeeder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent.getStringExtra(Arguments.ARG_EMAIL) != null) {
            eMail = intent.getStringExtra(Arguments.ARG_EMAIL);
        }
        else
            eMail = null;

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, TaskList.newInstance())
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.d("POSITION: " + position, "");
        if(position == 0) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, TaskList.newInstance()).addToBackStack("")
                    .commit();
        }
        else if(position == 1) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, TaskReport.newInstance("", "")).addToBackStack("")
                    .commit();
        }
        else if(position == 2) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, Setting.newInstance(position + 1, eMail)).addToBackStack("")
                    .commit();
        }
        else if(position == 3) {
            eMail = null;
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void toggle(View view) {
        ViewGroup viewParent = (ViewGroup)view.getParent();
        ViewGroup boxKedua = (ViewGroup)viewParent.getChildAt(1);

        if(boxKedua.getVisibility() == View.GONE) {
            boxKedua.setVisibility(View.VISIBLE);
        }
        else if(boxKedua.getVisibility() == View.VISIBLE) {
            boxKedua.setVisibility(View.GONE);
        }
    }

    private View getChild(View view, int idx) {
        ViewGroup viewGroup = (ViewGroup)view;
        return viewGroup.getChildAt(idx);
    }

    public void addTask(View view) {
        Intent intent = new Intent(this, AddTask.class);
        startActivity(intent);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}