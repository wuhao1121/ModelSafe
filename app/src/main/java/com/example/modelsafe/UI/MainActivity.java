package com.example.modelsafe.UI;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.entity.PopItem;
import com.example.modelsafe.Adapter.pageAdapter;
import com.example.modelsafe.Adapter.popWindowsAdapter;
import com.example.modelsafe.R;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private TextView mTools;
    private TextView mOptimization;
    private TextView mAppmanger;
    private ImageView mTran_bar;
    private ViewPager mPager;
    private View mViewtoos;
    private View mViewoptimization;
    private View mAppmanger1;
    private ArrayList<View> mPagerList;
    private TextView cutTxt;
    private View mMore;
    private PopItem item;
    private ArrayList<PopItem> list = new ArrayList<>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注意，要继承自Activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        setImageParam();
        InitContentVIew();

    }

    /**
     * 初始化界面内容
     */
    private void InitContentVIew() {
        LayoutInflater inflater = LayoutInflater.from(this);
        mViewtoos = inflater.inflate(R.layout.activitymain_tools,null);
        mViewoptimization = inflater.inflate(R.layout.actvitymain_optimization,null);
        mAppmanger1 = inflater.inflate(R.layout.activitymain_appmanger,null);
        mPagerList.add(mViewtoos);
        mPagerList.add(mViewoptimization);
        mPagerList.add(mAppmanger1);
        mPager.setAdapter(new pageAdapter(mPagerList));
    }

    /**
     * 初始化图片参数
     */
    private void setImageParam() {
        //获取默认屏幕的宽度
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int imagewidth = (int)(width/4*0.75);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTran_bar.getLayoutParams();
        params.width = imagewidth;
        mTran_bar.setLayoutParams(params);
        TranslateAnimation move = new TranslateAnimation(0, (width / 4 - imagewidth) / 2, 0, 0);
        move.setDuration(100);
        move.setFillAfter(true);
        mTran_bar.setAnimation(move);

    }

    private void initView() {
        mTools = findViewById(R.id.tv_tools);
        mOptimization = findViewById(R.id.tv_optimization);
        mAppmanger = findViewById(R.id.tv_appmanger);
        mTran_bar = findViewById(R.id.trans_bar);
        mPager = findViewById(R.id.pager);
        mTools.setOnClickListener(this);
        mOptimization.setOnClickListener(this);
        mAppmanger.setOnClickListener(this);
        mPager.setOnPageChangeListener(this);
        mPagerList = new ArrayList<>();
        cutTxt = mTools;
        setTextColor(mTools);
        mMore = findViewById(R.id.more);
        initMoreList();
        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                mMore.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                showPopWindows();
            }
        });

    }

    private void showPopWindows() {
        View view = getLayoutInflater().inflate(R.layout.pup_main, null, false);
        mListView = view.findViewById(R.id.list_view);
        popWindowsAdapter adapter = new popWindowsAdapter(list, getApplicationContext());
        mListView.setAdapter(adapter);
    }

    private void initMoreList() {
        item = new PopItem("用户中心");
        list.add(item);
        item = new PopItem("系统设置");
        list.add(item);
        item = new PopItem("设置项");
        list.add(item);
        item = new PopItem("设置项");
        list.add(item);
        item = new PopItem("设置项");
        list.add(item);
        item = new PopItem("设置项");
        list.add(item);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tools:
                //改变下标图片的方法
                imgtrans(mTools);
                setTextColor(mTools);
                mPager.setCurrentItem(0);
                break;
            case R.id.tv_optimization:
                imgtrans(mOptimization);
                setTextColor(mOptimization);
                mPager.setCurrentItem(1);
                break;
            case R.id.tv_appmanger:
                imgtrans(mAppmanger);
                setTextColor(mAppmanger);
                mPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    /**
     * 设置切换颜色
     *
     * @param endtexts
     */
    private void setTextColor(TextView endtexts) {
        switch (endtexts.getId()) {
            case R.id.tv_tools:
                mTools.setTextColor(getResources().getColor(R.color.green));
                mOptimization.setTextColor(getResources().getColor(R.color.white));
                mAppmanger.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_optimization:
                mTools.setTextColor(getResources().getColor(R.color.white));
                mOptimization.setTextColor(getResources().getColor(R.color.green));
                mAppmanger.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_appmanger:
                mTools.setTextColor(getResources().getColor(R.color.white));
                mOptimization.setTextColor(getResources().getColor(R.color.white));
                mAppmanger.setTextColor(getResources().getColor(R.color.green));
                break;
            default:
                break;
        }
    }

    /**
     * 设置下标的位置
     *
     * @param endtexts
     */
    private void imgtrans(TextView endtexts) {
        int startmid = cutTxt.getLeft() + cutTxt.getWidth() / 2;
        int startleft = startmid - mTran_bar.getWidth() / 2;
        int endmid = endtexts.getLeft() + endtexts.getWidth() / 2;
        int endLeft = endmid - mTran_bar.getWidth() / 2;
        TranslateAnimation translateAnimation = new TranslateAnimation(startleft, endLeft, 0, 0);
        translateAnimation.setDuration(100);
        translateAnimation.setFillAfter(true);
        mTran_bar.startAnimation(translateAnimation);
        cutTxt = endtexts;

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int pageId) {
        switch (pageId) {
            case 0:
                imgtrans(mTools);
                setTextColor(mTools);
                break;
            case 1:
                imgtrans(mOptimization);
                setTextColor(mOptimization);
                break;
            case 2:
                imgtrans(mAppmanger);
                setTextColor(mAppmanger);
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
