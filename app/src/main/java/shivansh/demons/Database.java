package shivansh.demons;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Dell on 17/10/15.
 */

public class Database extends SQLiteOpenHelper {

    private  static  final  String DB_name = "MyData";
    private static final int DB_Ver = 1;
    private static final String DB_table = "Task";
    public static final String DB_Column = "TaskName";

    public Database(Context context) {
        super(context, DB_name, null, DB_Ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);",DB_table,DB_Column);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DELETE TABLE IF EXISTS %s",DB_table);
        db.execSQL(query);
        onCreate(db);
    }
    public void insertNew(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_Column,task);
        db.insertWithOnConflict(DB_table,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }
    public  void deleteTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_table,DB_Column + " = ?",new String[]{task});
        db.close();
    }
    public ArrayList<String> getTaskList(){
        ArrayList<String> tasklist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_table,new String[]{DB_Column},null,null,null,null,null);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_Column);
            tasklist.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return tasklist;
    }
}
