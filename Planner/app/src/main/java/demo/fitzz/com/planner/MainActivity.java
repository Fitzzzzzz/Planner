package demo.fitzz.com.planner;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import tools.DBOpenHelper;
import tools.MyAdapter;
import tools.MyViewPager;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private MyViewPager mViewpager;
    private MyAdapter myAdapter;
    private AlertDialog.Builder builder;
    //private SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.listView);
    private DBOpenHelper helper = new DBOpenHelper(this);
    private SQLiteDatabase db;
    private Cursor c;
    private String infoTitle;
    private int classify = 0;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initView();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_button,menu);
        return true;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("待办事项");
        setSupportActionBar(toolbar);
    }


    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mViewpager = (MyViewPager) findViewById(R.id.viewPager);
        myAdapter = new MyAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(myAdapter);
        mViewpager.setScrollble(false);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    public void addInfo(MenuItem item) {
        final EditText editText1 = new EditText(this);

        editText1.setHint("标题");
        builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入");
        builder.setSingleChoiceItems(new String[]{"会议", "饭局", "行程"}, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                classify = i + 1;
            }
        });
        builder.setView(editText1,60,20,80,60);
        builder.setPositiveButton("取消",null);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                infoTitle = editText1.getText().toString();
                Log.d("classify", classify+"");
                if (!infoTitle.isEmpty()){
                    db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("title",infoTitle);
                    values.put("classify",classify);
                    values.put("detail","test");
                    values.put("isDone",0);
                    values.put("importance",1);
                    db.insert("plan",null,values);
                    db.close();
                }
            }
        });

        builder.show();
    }
}
