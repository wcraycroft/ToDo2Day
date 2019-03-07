// TODO: when new task is added, isDone box is being reset to false (in View not Model) for all tasks.

package edu.miracosta.cs134;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        // Get a readable reference to db
        SQLiteDatabase db = mDb.getReadableDatabase();

        // Fill the list with data from the database
        mAllTasks = mDb.getAllTasks(db);

        db.close();

        // Connect controller with view
        mTaskEditText = findViewById(R.id.taskEditText);
        mTaskListView = findViewById(R.id.taskListView);

        // Associate the TaskListAdapter with the ListView
        mTaskListAdapter = new TaskListAdapter(this, R.layout.task_item, mAllTasks);
        mTaskListView.setAdapter(mTaskListAdapter);

        // TODO: doesn't need a click listener, can attach it to Checkbox's onClick
        // Instantiate ListView onItemClickListener()
        mTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get Checkbox reference
                CheckBox isDoneCB = view.findViewById(R.id.isDoneCheckBox);
                // Swap checkbox status
                isDoneCB.setChecked(!isDoneCB.isChecked());

                // Send new checked status to Model
                // Get a writable reference to db
                //SQLiteDatabase db = mDb.getWritableDatabase();
                // Get task that has just been clicked
                Task updatedTask = mAllTasks.get(position);

                // TODO: fix this part
                // Update Model
                mAllTasks.get(position).setDone(!updatedTask.isDone());

                mDb.updateTask(updatedTask);



                //db.close();

                // Notify ListAdapter that it has changed
                mTaskListAdapter.notifyDataSetChanged();
            }
        });
    }

    public void addTask(View v) {
        String description = mTaskEditText.getText().toString();
        Task newTask = new Task(description);

        // Get a writable reference to db
        SQLiteDatabase db = mDb.getWritableDatabase();

        // Add task to DB
        mDb.addTask(db, newTask);

        // Update Model
        mAllTasks.add(newTask);
        db.close();


        // Notify ListAdapter that it has changed
        mTaskListAdapter.notifyDataSetChanged();
    }

    public void clearTask(View v) {
        // Get a writable reference to db
        SQLiteDatabase db = mDb.getWritableDatabase();

        mDb.clearAllTasks(db);

        // Update Model
        mAllTasks.clear();
        db.close();

        // Notify ListAdapter that it has changed
        mTaskListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDb.close();
    }
}
