package tools;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import demo.fitzz.com.planner.MainActivity;
import demo.fitzz.com.planner.R;

/**
 * Created by 13662 on 2016/10/18.
 */
public class SwipMenuAndRefresh {

    private Activity activity;
    private SwipeMenuListView listView;

    private SwipeRefreshLayout swipeRefreshLayout;

    public SwipMenuAndRefresh(Activity activity,SwipeMenuListView listView){
        this.activity = activity;
        this.listView = listView;
    }

    public  void initSwipMenu(){
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item

                SwipeMenuItem openItem = new SwipeMenuItem(
                        activity.getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set a icon
                //openItem.setIcon(R.drawable.gray_circle);
                // set item title fontsize
                openItem.setTitleSize(16);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        activity.getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("DELETE");
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.WHITE);
                // set a icon
                //deleteItem.setIcon(R.drawable.ic_note_add_white_48dp);
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
        final float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    public void initRefresh(int id) {

        swipeRefreshLayout = (SwipeRefreshLayout) activity.findViewById(id);
        swipeRefreshLayout.setColorSchemeResources(R.color.material_blue,R.color.material_red,R.color.material_green);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainActivity.myAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}
