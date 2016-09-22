package tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fitzz on 16-8-15.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "plan.db";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists plan(_id integer primary key autoincrement,title text not null,detail text not null,isDone integer not null,classify integer not null,importance integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
