package layout;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import demo.fitzz.com.planner.MainActivity;
import demo.fitzz.com.planner.R;
import tools.DBOpenHelper;


public class BlankFragment extends Fragment {
    SwipeMenuListView listView;
    private DBOpenHelper helper;
    private Cursor cursor;
    private SQLiteDatabase db;
    private final String[] COLUMNS = new String[]{"_id","title","detail","isDone","classify","importance"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        listView = (SwipeMenuListView) getActivity().findViewById(R.id.listView);
        helper = new DBOpenHelper(this.getActivity());
        db = helper.getReadableDatabase();
        cursor = db.query("plan",COLUMNS,null,null,null,null,null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,cursor,new String[]{"title"},new int[]{R.id.title_item},0);
        
        listView.setAdapter(adapter);
        initSwipmenu();
        initRefresh();
        Log.d("onStart", "!!!");
    }
    private void initSwipmenu() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item

                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set a icon
                openItem.setIcon(R.drawable.gray_circle);
                // set item title fontsize
                openItem.setTitleSize(1);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("de");
                // set a icon
                deleteItem.setIcon(R.drawable.ic_note_add_white_48dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Log.d("item:", "onMenuItemClick: open");
                        break;
                    case 1:
                        // delete
                        Log.d("item", "onMenuItemClick: del");
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }


    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    private void initRefresh() {
        MaterialRefreshLayout layout = (MaterialRefreshLayout) getActivity().findViewById(R.id.refresh);
        layout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                Toast.makeText(getActivity(),"refresh",Toast.LENGTH_SHORT).show();
                materialRefreshLayout.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.finishRefreshLoadMore();
            }
        });
    }
}
