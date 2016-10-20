package layout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import demo.fitzz.com.planner.R;
import tools.AddDataToList;
import tools.DBOpenHelper;
import tools.SwipMenuAndRefresh;


public class Fragment_travel extends Fragment {
    SwipeMenuListView listView;
    private SwipMenuAndRefresh swipMenuAndRefresh;
    private AddDataToList addDataToList;
    private DBOpenHelper helper;
    private Cursor cursor;
    private SQLiteDatabase db;
    private final String[] COLUMNS = new String[]{"_id","title","detail","isDone","classify","importance"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_travel, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        listView = (SwipeMenuListView) getActivity().findViewById(R.id.listView_travel);
        swipMenuAndRefresh = new SwipMenuAndRefresh(getActivity(),listView);
        addDataToList = new AddDataToList(this.getActivity(),listView,"3");
        swipMenuAndRefresh.initSwipMenu();
        swipMenuAndRefresh.initRefresh(R.id.refresh_travel);
        addDataToList.addDataToList();
        Log.d("onStart", "!!!");
    }
}
