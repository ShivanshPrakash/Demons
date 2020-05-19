package shivansh.demons;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.lang.ref.WeakReference;
import java.util.List;

import shivansh.demons.RoomUtils.Tasks;

/**
 * Created by Shivansh ON 2019-12-24.
 */
public class TasksViewModel extends AndroidViewModel {
    private TasksRepository mRepository;
    private LiveData<List<Tasks>> allTaskId;

    public TasksViewModel(Application application) {
        super(application);
        mRepository = new TasksRepository(new WeakReference<Context>(application));
        allTaskId = mRepository.getAllTaskId();
    }

    LiveData<List<Tasks>> getAllTaskId() {
        return allTaskId;
    }

    void addTask(String task) {
        mRepository.insertTask(task);
    }

    public void setInactive(int id) {
        mRepository.setInactive(id);
    }
}
