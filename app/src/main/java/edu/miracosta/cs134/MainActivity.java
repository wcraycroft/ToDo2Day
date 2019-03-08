

package edu.miracosta.cs134;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import edu.miracosta.cs134.Model.DBHelper;
import edu.miracosta.cs134.Model.Task;

public class MainActivity extends AppCompatActivity {

    // Model declarations
    private DBHelper mDb;
    private List<Task> mAllTasks;
    // Views declarations
    private EditText mTaskEditText;
    private ListView mTaskListView;
    private TaskListAdapter mTaskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the database
        mDb = new DBHelper(this);

        // Fill the list with data from the database
        mAllTasks = mDb.getAllTasks();

        // Connect controller with view
        mTaskEditText = findViewById(R.id.taskEditText);
        mTaskListView = findViewById(R.id.taskListView);

        // Associate the TaskListAdapter with the ListView
        mTaskListAdapter = new TaskListAdapter(this, R.layout.task_item, mAllTasks);
        mTaskListView.setAdapter(mTaskListAdapter);
    }

    public void addTask(View v) {

        // Create new task from EditText String
        String description = mTaskEditText.getText().toString();
        Task newTask = new Task(description);

        // Add task to DB
        mDb.addTask(newTask);

        // Add task to Model
        mAllTasks.add(newTask);

        // Notify ListAdapter that it has changed
        mTaskListAdapter.notifyDataSetChanged();
    }

    public void clearTask(View v) {

        // Clear database
        mDb.clearAllTasks();

        // Clear Model
        mAllTasks.clear();

        // Notify ListAdapter that it has changed
        mTaskListAdapter.notifyDataSetChanged();
    }

    public void taskClicked(View view) {

        // Get position from tag (set in TaskListAdapter getView)
        int position = (Integer) view.getTag();

        // Cast clicked View as Checkbox
        CheckBox isDoneCB = (CheckBox) view;

        // Get task that has just been clicked
        Task updatedTask = mAllTasks.get(position);

        // Update Model
        updatedTask.setDone(isDoneCB.isChecked());

        // Update the Database
        mDb.updateTask(updatedTask);

        //mAllTasks.get(position).setDone(!updatedTask.isDone());

        // Notify ListAdapter that it has changed
        mTaskListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDb.close();
    }
}
