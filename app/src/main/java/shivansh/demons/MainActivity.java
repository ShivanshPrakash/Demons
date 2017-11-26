package shivansh.demons;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView Task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new Database(this);
        Task = (ListView) findViewById(R.id.Task);
        loadTaskList();
    }

    private void loadTaskList() {
        ArrayList<String> tasklist = dbHelper.getTaskList();
        if(mAdapter==null){
            mAdapter = new ArrayAdapter<String>(this,R.layout.row,R.id.task_title,tasklist);
            Task.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.clear();
            mAdapter.addAll(tasklist);
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return  super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add New Task")
                        .setMessage("What's your new demon")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                dbHelper.insertNew(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public  void deleteTask(View view){
        View parent  = (View)view.getParent();
        TextView taskTextV = (TextView)findViewById(R.id.task_title);
        String task  = String.valueOf(taskTextV.getText());
        dbHelper.deleteTask(task);
        loadTaskList();
    }
}
