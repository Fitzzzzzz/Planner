package layout;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import demo.fitzz.com.planner.R;
import tools.DBOpenHelper;
import tools.SwipMenuAndRefresh;


public class Fragment_meeting extends Fragment {
    SwipeMenuListView listView;
    private SwipMenuAndRefresh swipMenuAndRefresh;
    private DBOpenHelper helper;
    private Cursor cursor;
    private SQLiteDatabase db;
    private final String[] COLUMNS = new String[]{"_id","title","detail","isDone","classify","importance"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_meeting, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        listView = (SwipeMenuListView) getActivity().findViewById(R.id.listView_meeting);
        swipMenuAndRefresh = new SwipMenuAndRefresh(getActivity(),listView);
        helper = new DBOpenHelper(this.getActivity());
        db = helper.getReadableDatabase();
        cursor = db.query("plan",COLUMNS,"classify=?",new String[]{"1"},null,null,null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this.getActivity(),R.layout.listview_item,cursor,new String[]{"title"},new int[]{R.id.title_item},0);
        swipMenuAndRefresh.initSwipMenu();
        swipMenuAndRefresh.initRefresh();
        listView.setAdapter(adapter);
        Log.d("onStart", "!!!");
    }
}
