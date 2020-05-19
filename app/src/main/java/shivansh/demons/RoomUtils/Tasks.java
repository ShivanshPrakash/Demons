package shivansh.demons.RoomUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Shivansh ON 2019-12-22.
 */
@Entity(tableName = "tasks")
public class Tasks {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "id")
    public int id;

    @ColumnInfo(name = "task_name")
    public String name;

    @ColumnInfo(name = "active")
    public boolean status;
}
