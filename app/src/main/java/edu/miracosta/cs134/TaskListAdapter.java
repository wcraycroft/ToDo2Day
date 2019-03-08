package edu.miracosta.cs134;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.List;

import edu.miracosta.cs134.Model.Task;

public class TaskListAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private int mResourceId;
    private List<Task> mAllTasks;

    public TaskListAdapter(Context context, int resource, List<Task> objects) {
        super(context, resource, objects);
        // Initialize instance variables
        mContext = context;
        mResourceId = resource;
        mAllTasks = objects;
    }

    // Override getView method
    // Ctrl + o to override
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        // For each task in the list, inflate its view
        Task focusedTask = mAllTasks.get(position);

        // Create layout inflater
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(mResourceId, null);
        CheckBox isDoneCB = view.findViewById(R.id.isDoneCheckBox);
        isDoneCB.setChecked(focusedTask.isDone());
        isDoneCB.setText(focusedTask.getDescription());

        // Set position tag to be used in onClick
        isDoneCB.setTag(position);

        return view;
    }
}
