package com.stef_developer.dailytask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.LinearLayout;

import com.stef_developer.dailytask.fragments.NavigationDrawerFragment;
import com.stef_developer.dailytask.fragments.Setting;
import com.stef_developer.dailytask.fragments.TaskList;
import com.stef_developer.dailytask.fragments.TaskReport;
import com.stef_developer.dailytask.view.TaskIcon;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PieChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        TaskReport.OnFragmentInteractionListener,
        Setting.OnFragmentInteractionListener,
        TaskList.OnFragmentInteractionListener
{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                .replace(R.id.container, TaskList.newInstance("", ""))
                .commit();

//        Button btnChart = (Button)findViewById(R.id.btn_chart);
//
//        // Defining click event listener for the button btn_chart
//        View.OnClickListener clickListener = new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // Draw the Income vs Expense Chart
//                openChart();
//            }
//        };
//
//        // Setting event click listener for the button btn_chart of the MainActivity layout
//        btnChart.setOnClickListener(clickListener);
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
                    .replace(R.id.container, TaskList.newInstance("", ""))
                    .commit();
        }
        else if(position == 1) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, TaskReport.newInstance("", ""))
                    .commit();
        }
        else if(position == 2) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, Setting.newInstance("", ""))
                    .commit();
        }
        else if(position == 3) {
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

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public void toggle(View view) {
        ViewGroup viewGroup = (ViewGroup)getChild(findViewById(R.id.taskbox), 1);
        ViewGroup viewGroup1 = (ViewGroup)getChild(viewGroup, 1);
        if(viewGroup1.getVisibility() == View.GONE) {
            viewGroup1.setVisibility(View.VISIBLE);
        }
        else if(viewGroup1.getVisibility() == View.VISIBLE) {
            viewGroup1.setVisibility(View.GONE);
        }
    }

    private View getChild(View view, int idx) {
        ViewGroup viewGroup = (ViewGroup)view;
        return viewGroup.getChildAt(idx);
    }

    @Override
    public void openChart(View view) {
        // Pie Chart Section Names
        String[] code = new String[] {
                "Completed Task", "Uncompleted Task", "Failed Task"
        };

        // Pie Chart Section Value
        double[] distribution = { 58, 26, 16 } ;

        // Color of each Pie Chart Sections
        int[] colors = { Color.GREEN, Color.YELLOW, Color.RED };

        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("Percentages completed Task :");
        for(int i=0 ;i < distribution.length;i++){
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        // Instantiating a renderer for the Pie Chart
        DefaultRenderer defaultRenderer  = new DefaultRenderer();
        for(int i = 0 ;i<distribution.length;i++){
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setChartTitle("Percentages completed Task :");
        defaultRenderer.setChartTitleTextSize(20);
        defaultRenderer.setZoomButtonsVisible(false);
        defaultRenderer.setClickEnabled(true);
        defaultRenderer.setDisplayValues(false);
        defaultRenderer.setZoomEnabled(false);
        defaultRenderer.setExternalZoomEnabled(false);
        defaultRenderer.setLabelsColor(Color.BLACK);
        defaultRenderer.setLabelsTextSize(18);
        defaultRenderer.setClickEnabled(true);

        PieChart pieChart = new PieChart(distributionSeries, defaultRenderer);
        pieChart.draw();
    }

}
