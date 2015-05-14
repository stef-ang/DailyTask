package com.stef_developer.dailytask;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.stef_developer.dailytask.database.UserDAO;
import com.stef_developer.dailytask.table_object.User;

import java.sql.SQLException;


public class Login extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;

    private User user;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            userDAO = new UserDAO(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
    }

    public void loginClick(View view) {
        String password;
        String email;

        try {
            password = etPassword.getText().toString();
            email = etEmail.getText().toString();

            if(userDAO.cekEmailPasswordByEmail(email, password)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),
                        "Login failed!\nPlease try again",
                        Toast.LENGTH_LONG).show();
                return;
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Please fill columns correctly!",
                    Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void registerClick(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_login, menu);
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
