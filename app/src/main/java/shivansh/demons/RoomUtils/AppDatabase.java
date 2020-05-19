package shivansh.demons.RoomUtils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.lang.ref.WeakReference;

/**
 * Created by Shivansh ON 2019-12-22.
 */
@Database(entities = {Tasks.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static AppDatabase sInstance;

    public static synchronized AppDatabase getInstance(WeakReference<Context> contextWeakReference) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(contextWeakReference.get().getApplicationContext(), AppDatabase.class, "demon_db")
                    .build();
        }
        return sInstance;
    }
}
