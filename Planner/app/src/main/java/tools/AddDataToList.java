package tools;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.Adapter;
import android.widget.ListView;

import demo.fitzz.com.planner.R;

/**
 * Created by 13662 on 2016/10/20.
 */
public class AddDataToList {

    private DBOpenHelper helper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private Activity mActivity;
    private final String[] COLUMNS = new String[]{"_id","title","detail","isDone","classify","importance"};
    private String mClassify;
    private String search;
    private ListView mListView;

    public AddDataToList(Activity activity, ListView listView,String classify){
        mActivity = activity;
        mListView = listView;
        mClassify = classify;
        if (classify != null) search = "classify=?";
        else search = null;
    }

    public void addDataToList(){
        helper = new DBOpenHelper(mActivity);
        db = helper.getReadableDatabase();
        if (search == null) cursor = db.query("plan",COLUMNS,search,null,null,null,null);
        else cursor = db.query("plan",COLUMNS,search,new String[]{mClassify},null,null,null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(mActivity, R.layout.listview_item,cursor,new String[]{"title"},new int[]{R.id.title_item},0);
        mListView.setAdapter(adapter);
    }


}
