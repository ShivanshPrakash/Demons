package shivansh.demons;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shivansh on 17/11/26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = MyAdapter.class.getSimpleName();
    private int mNumberItems;
    ArrayList<String> tasklist;
    MainActivity mainActivity;
    Database dbHelper;
    public MyAdapter(int numberOfItems, ArrayList<String> TaskList, Database dbHelper, MainActivity mainActivity) {
        mNumberItems = numberOfItems;
        tasklist = TaskList;
        this.dbHelper=dbHelper;
        this.mainActivity=mainActivity;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.row;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(tasklist.get(position));
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView listItemNumberView;
        ImageButton button;
        public MyViewHolder(View itemView) {
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.task_title);
            button = (ImageButton) itemView.findViewById(R.id.deleteBtn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String task = String.valueOf(listItemNumberView.getText());
                    dbHelper.deleteTask(task);
                    mainActivity.loadTaskList();
                }
            });
        }
        void bind(String todo) {
            listItemNumberView.setText(todo);
        }
    }
}
