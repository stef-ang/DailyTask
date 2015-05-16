package com.stef_developer.dailytask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stef_developer.dailytask.view.TaskIcon;


public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //penggunaan: TaskIcon(this, hari [int], progress [0.0-1.0 float], radius [opsional] )
        TaskIcon ti1 = new TaskIcon(this, 0, 1.0f, 40);
        TaskIcon ti2 = new TaskIcon(this, 4, 0.7f, 40);
        TaskIcon ti3 = new TaskIcon(this, 7, 0.4f, 40);
        TaskIcon ti4 = new TaskIcon(this, 12, 0, 40);
        TaskIcon ti5 = new TaskIcon(this, 30, 0, 40);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.test_layout);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //param.gravity = Gravity.CENTER_HORIZONTAL;
        mainLayout.addView(ti1, param);
        mainLayout.addView(ti2, param);
        mainLayout.addView(ti3, param);
        mainLayout.addView(ti4, param);
        mainLayout.addView(ti5, param);
        mainLayout.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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
