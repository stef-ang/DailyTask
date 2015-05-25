package com.stef_developer.dailytask;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stef_developer.dailytask.database.UserDAO;
import com.stef_developer.dailytask.table_object.User;

import java.sql.SQLException;


public class Register extends AppCompatActivity {

    private EditText etFullname;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPassword2;

    private User newUser;
    private UserDAO userDAO;
    private Boolean fieldEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        try {
            userDAO = new UserDAO(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        etFullname = (EditText) findViewById(R.id.edittext_fullname);
        etEmail = (EditText) findViewById(R.id.edittext_email);
        etPassword = (EditText) findViewById(R.id.edittext_password);
        etPassword2 = (EditText) findViewById(R.id.edittext_retype_password);
    }

    public void registerClick(View view){
        this.fieldEmpty = false;

        checkEmpty(etFullname, "Please fill Fullname field !");
        checkEmpty(etEmail, "Please fill Email field !");
        checkEmpty(etPassword, "Please fill Password field !");
        checkEmpty(etPassword2, "Please fill Re-type Password field !");

        if(!this.fieldEmpty) {
            if(!(etPassword.getText().toString().equals(etPassword2.getText().toString()))){
                Toast.makeText(getApplicationContext(),
                        "Password and Retype Password does not same\n" +
                                "Please fill password correctly!",
                        Toast.LENGTH_LONG).show();
                return;
            }
            if(!userDAO.cekUniqueEmail(etEmail.getText().toString())) {
                Toast.makeText(getApplicationContext(),
                        "Email have been used by another user\n" +
                                "Please fill other email!",
                        Toast.LENGTH_LONG).show();
                return;
            }
            newUser = new User();
            try {
                newUser.setFullname(etFullname.getText().toString());
                newUser.setEmail(etEmail.getText().toString());
                newUser.setPassword(etPassword.getText().toString());
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Please fill columns correctly!",
                        Toast.LENGTH_LONG).show();
                return;
            }
            try {
                long result = userDAO.insert(newUser);
                if(result != -1) {
                    Toast.makeText(getApplicationContext(),
                            "Register success. Please Sign in",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void checkEmpty(TextView textView, String errorMessage) {
        int length = textView.getText().toString().length();
        if(length == 0) {
            textView.setError(errorMessage);
            textView.setFocusable(true);
            textView.setFocusableInTouchMode(true);
            this.fieldEmpty = true;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_register, menu);
//        return true;
//    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        onBackPressed();
        return true;

//        return super.onOptionsItemSelected(item);
    }
}
