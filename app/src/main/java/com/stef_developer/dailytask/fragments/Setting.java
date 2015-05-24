package com.stef_developer.dailytask.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stef_developer.dailytask.Arguments;
import com.stef_developer.dailytask.MainActivity;
import com.stef_developer.dailytask.R;
import com.stef_developer.dailytask.database.UserDAO;
import com.stef_developer.dailytask.table_object.User;

import java.lang.ref.WeakReference;
import java.sql.SQLException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * { Setting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setting extends Fragment implements OnClickListener {
    // TODO: Rename and change types of parameters
    private String mEmail;
    private int mNumber;

    private UserDAO userDAO;
    private User user;

    private TextView tv_email;
    private EditText et_fullname;
    private EditText et_old_pwd;
    private EditText et_new_pwd;
    private EditText et_renew_pwd;
    private Button btn_save;

    private WeakReference<Activity> activityWeakRef;

//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param email Parameter 1.
     * @param number Parameter 2.
     * @return A new instance of fragment setting.
     */
    // TODO: Rename and change types and number of parameters
    public static Setting newInstance(int number, String email) {
        Setting fragment = new Setting();
        Bundle args = new Bundle();
        args.putString(Arguments.ARG_EMAIL, email);
        args.putInt(Arguments.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    public Setting() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEmail = getArguments().getString(Arguments.ARG_EMAIL);
            mNumber = getArguments().getInt(Arguments.ARG_SECTION_NUMBER);
        }
        try {
            userDAO = new UserDAO(getActivity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d("EMAIL: " + mEmail, " CEK EMAIL");
        user = userDAO.getUserByEmail(mEmail);
        activityWeakRef = new WeakReference<Activity>(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_setting, container, false);

        tv_email = (TextView) view.findViewById(R.id.tv_email);
        et_fullname = (EditText) view.findViewById(R.id.et_fullname);
        et_old_pwd = (EditText) view.findViewById(R.id.et_old_pwd);
        et_new_pwd = (EditText) view.findViewById(R.id.et_new_pwd);
        et_renew_pwd = (EditText) view.findViewById(R.id.et_re_new_pwd);
        btn_save = (Button) view.findViewById(R.id.btn_save);

        btn_save.setOnClickListener(this);

        if(user != null) {
            tv_email.setText(user.getEmail());
            et_fullname.setText(user.getFullname());
        }
        else {
            Toast.makeText(activityWeakRef.get(),
                    "The User is null",
                    Toast.LENGTH_LONG).show();
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btn_save) {
            if(et_fullname.getText().toString().isEmpty() ||
                    et_old_pwd.getText().toString().isEmpty() ||
                    et_new_pwd.getText().toString().isEmpty() ||
                    et_renew_pwd.getText().toString().isEmpty()){
                Toast.makeText(activityWeakRef.get(),
                        "Please do not columns empty!",
                        Toast.LENGTH_LONG).show();
                return;
            }

            Log.d("USER_PWD: " + user.getPassword(), "USER_PWD");
            Log.d("OLD_PWD: " + et_old_pwd.getText().toString(), "OLD_PWD");
            if(!user.getPassword().equals(et_old_pwd.getText().toString())) {
                Toast.makeText(activityWeakRef.get(),
                        "Sorry, old password is wrong!",
                        Toast.LENGTH_LONG).show();
                return;
            }

            if(!et_new_pwd.getText().toString().equals(et_renew_pwd.getText().toString())) {
                Toast.makeText(activityWeakRef.get(),
                        "Sorry, new password does not match!",
                        Toast.LENGTH_LONG).show();
                return;
            }

            user.setPassword(et_new_pwd.getText().toString());
            try {
                long result = userDAO.update(user);
                if(result != -1) {
                    Toast.makeText(activityWeakRef.get(),
                            "Setting Success",
                            Toast.LENGTH_LONG).show();
                    et_old_pwd.setText("");
                    et_new_pwd.setText("");
                    et_renew_pwd.setText("");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Arguments.ARG_SECTION_NUMBER));
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
