package shivansh.demons;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database dbHelper;
    private MyAdapter mAdapter;
    private RecyclerView Task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new Database(this);
        loadTaskList();
    }

    public void loadTaskList() {
        ArrayList<String> tasklist = dbHelper.getTaskList();
        int NUM_LIST_ITEMS = tasklist.size();
       if(!tasklist.isEmpty()){
           Task = (RecyclerView) findViewById(R.id.Task);
           LinearLayoutManager layoutManager = new LinearLayoutManager(this);
           Task.setLayoutManager(layoutManager);
           Task.setHasFixedSize(true);
           mAdapter = new MyAdapter(NUM_LIST_ITEMS,tasklist,dbHelper,this);
           Task.setAdapter(mAdapter);
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
                loadTaskList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    public  void deleteTask(View view){
        View parent  = (View)view.getParent();
        int val = parent.getId();
        Log.d("Dekho...",val+" ");
        TextView taskTextV = (TextView)findViewById(R.id.task_title);
        String task  = String.valueOf(taskTextV.getText());
        dbHelper.deleteTask(task);
        loadTaskList();
    }
    */
}
