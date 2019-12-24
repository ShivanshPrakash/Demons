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
import java.util.List;

import shivansh.demons.RoomUtils.AppDatabase;

/**
 * Created by Shivansh on 17/11/26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = MyAdapter.class.getSimpleName();
    private ArrayList<Integer> taskList;
    private AppDatabase dbHelper;

    MyAdapter(List<Integer> taskList, AppDatabase dbHelper) {
        this.taskList = (ArrayList<Integer>) taskList;
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.d(TAG, "#" + position);
        holder.bind(dbHelper.taskDao().getTaskById(taskList.get(position)));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.taskDao().setStatus(false, taskList.get(holder.getAdapterPosition()));
                taskList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView listItemNumberView;
        ImageButton button;

        MyViewHolder(View itemView) {
            super(itemView);
            listItemNumberView = itemView.findViewById(R.id.task_title);
            button = itemView.findViewById(R.id.deleteBtn);
        }

        void bind(String todo) {
            listItemNumberView.setText(todo);
        }
    }
}
