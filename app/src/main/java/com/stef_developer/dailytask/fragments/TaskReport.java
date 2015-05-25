package com.stef_developer.dailytask.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.stef_developer.dailytask.MainActivity;
import com.stef_developer.dailytask.R;
import com.stef_developer.dailytask.view.TaskChart;

import java.text.DateFormatSymbols;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskReport.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskReport extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment taskreport.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskReport newInstance(String param1, String param2) {
        TaskReport fragment = new TaskReport();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TaskReport() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Task Report");
        final View view = inflater.inflate(R.layout.fragment_taskreport, container, false);

        Spinner spinnerBulan = (Spinner) view.findViewById(R.id.bulan);
        String[] bulan = new DateFormatSymbols().getMonths();
        ArrayAdapter<String> spinnerBulanAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, bulan);
        spinnerBulan.setAdapter(spinnerBulanAdapter);

        Spinner spinnerTahun = (Spinner) view.findViewById(R.id.tahun);
        String[] tahun = {"2015", "2016", "2017", "2018", "2019", "2020"};
        ArrayAdapter<String> spinnerTahunAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, tahun);
        spinnerTahun.setAdapter(spinnerTahunAdapter);

        TaskChart taskChart = new TaskChart(getActivity());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        ViewGroup viewGroup = (ViewGroup)view.findViewById(R.id.piechart);
        viewGroup.addView(taskChart, params);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
    }

}
