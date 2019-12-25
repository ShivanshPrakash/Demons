package shivansh.demons;

import android.content.Context;
import androidx.lifecycle.LiveData;

import java.lang.ref.WeakReference;
import java.util.List;

import shivansh.demons.RoomUtils.AppDatabase;
import shivansh.demons.RoomUtils.TaskDao;
import shivansh.demons.RoomUtils.Tasks;

/**
 * Created by Shivansh ON 2019-12-24.
 */
class TasksRepository {
    private TaskDao taskDao;
    private LiveData<List<Tasks>> allTaskID;

    TasksRepository(final WeakReference<Context> appContext) {
        taskDao = AppDatabase.getInstance(appContext).taskDao();
        allTaskID = taskDao.getAll();
    }

    void insertTask(String taskTitle) {
        final Tasks task = new Tasks();
        task.status = true;
        task.name = taskTitle;
        new Thread(new Runnable() {
            @Override
            public void run() {
                taskDao.insert(task);
            }
        }).start();
    }

    void setInactive(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                taskDao.setStatus(false, id);
            }
        }).start();
    }

    LiveData<List<Tasks>> getAllTaskId() {
        return allTaskID;
    }
}
