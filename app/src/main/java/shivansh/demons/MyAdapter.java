package shivansh.demons;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Shivansh on 17/11/26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = MyAdapter.class.getSimpleName();
    private int mNumberItems;
    private ArrayList<String> taskList;
    private Database dbHelper;

    MyAdapter(int numberOfItems, ArrayList<String> TaskList, Database dbHelper) {
        mNumberItems = numberOfItems;
        taskList = TaskList;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.row;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(taskList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView listItemNumberView;
        ImageButton button;

        MyViewHolder(View itemView) {
            super(itemView);
            listItemNumberView = itemView.findViewById(R.id.task_title);
            button = itemView.findViewById(R.id.deleteBtn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String task = String.valueOf(listItemNumberView.getText());
                    dbHelper.deleteTask(task);
                    mNumberItems--;
                    int removedIndex = taskList.indexOf(task);
                    taskList.remove(removedIndex);
                    notifyItemRemoved(removedIndex);
                }
            });
        }

        void bind(String todo) {
            listItemNumberView.setText(todo);
        }
    }
}
