package com.stef_developer.dailytask.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.stef_developer.dailytask.MainActivity;
import com.stef_developer.dailytask.R;
import com.stef_developer.dailytask.adapter.TaskAdapter;
import com.stef_developer.dailytask.database.TaskDAO;
import com.stef_developer.dailytask.table_object.Task;

import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TASK_LIST = "TASK_LIST";

    private TaskDAO taskDAO;
    private View fragmentView;
    private ArrayList<Task> taskArrayList;
    private TaskAdapter taskAdapter;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TaskList.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskList newInstance() {
        TaskList fragment = new TaskList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TaskList() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Task List");
        fragmentView = inflater.inflate(R.layout.fragment_task_list, container, false);
        try {
            taskDAO = new TaskDAO(this.getActivity());
            AsyncGetTask taskGetter = new AsyncGetTask(this.getActivity());
            taskGetter.execute();
        } catch (SQLException e) {
            taskDAO = null;
            e.printStackTrace();
        }
        return fragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public void updateTaskList() {
        ListView taskList = (ListView)fragmentView.findViewById(R.id.taskList);
        taskList.setAdapter(taskAdapter);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private class AsyncGetTask extends AsyncTask<Void, Void, ArrayList<Task>> {

        private final WeakReference<Activity> activityWeakRef;

        public AsyncGetTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<Task> doInBackground(Void... params) {
            ArrayList<Task> taskList = taskDAO.getTasks();
            return taskList;
        }

        @Override
        protected void onPostExecute(ArrayList<Task> taskList2) {
            ArrayList<Task> taskList = new ArrayList<Task>();
            for(Task task : taskList2) {
                if(task.getStatus() == Task.UNFINISHED) {
                    taskList.add(task);
                }
            }
            if (activityWeakRef.get() != null && !activityWeakRef.get().isFinishing()) {
                taskArrayList =  taskList;
                try {
                    taskAdapter = new TaskAdapter(getActivity(), R.layout.task_item, taskArrayList.toArray(new Task[taskArrayList.size()]));
                    updateTaskList();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
