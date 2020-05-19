package shivansh.demons.RoomUtils;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Created by Shivansh ON 2019-12-22.
 */
@Dao
public interface TaskDao {
    @Query("SELECT * FROM tasks WHERE active = 1")
    LiveData<List<Tasks>> getAll();

    @Query("UPDATE tasks SET active = (:status) WHERE id = (:id)")
    void setStatus(boolean status, int id);

    @Insert
    void insert(Tasks task);
}
