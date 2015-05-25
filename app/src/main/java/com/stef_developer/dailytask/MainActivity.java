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

//        TaskIcon taskIcon = new TaskIcon(this, 1, 0.70f);
//        ViewGroup taskbox = (ViewGroup)getChild(findViewById(R.id.taskbox), 1);
//        ViewGroup boxAtas = (ViewGroup)getChild(taskbox, 0);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.gravity = Gravity.CENTER;
//        boxAtas.addView(taskIcon, 0, layoutParams);
//        boxAtas.invalidate();
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

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    //    public void restoreActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setTitle(mTitle);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.main, menu);
//            restoreActionBar();
//            return true;
//        }
//        return super.onCreateOptionsMenu(menu);
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


//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            return rootView;
//        }
//
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }
//    }

}
