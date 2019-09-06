package com.custy.mvpandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.custy.mvpandroid.module.home.HomeFragment;
import com.custy.mvpandroid.module.project.ProjectFragment;
import com.custy.mvpandroid.module.mine.MineFragment;
import com.custy.mvpandroid.utils.ToastUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "bottomNavigationSelectItem";
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_PROJECT = 1;
    private static final int FRAGMENT_MINE = 2;
    //    private static final int FRAGMENT_MEDIA = 3;
    private Toolbar mToolbar;
    public static DrawerLayout  mDrawerLayout;
    private NavigationView mNavigationView;
    private HomeFragment mHomeFragment;
    private ProjectFragment mProjectFragment;
    private MineFragment mMineFragment;
    private BottomNavigationView mBottomNavigationView;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        if (savedInstanceState != null) {
            mHomeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(mHomeFragment.getClass().getName());
            mProjectFragment = (ProjectFragment) getSupportFragmentManager().findFragmentByTag(mProjectFragment.getClass().getName());
            mMineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(mMineFragment.getClass().getName());
            //恢复recreate之前的位置
            showFragment(savedInstanceState.getInt(POSITION));
            mBottomNavigationView.setSelectedItemId(savedInstanceState.getInt(POSITION));
        } else {
            showFragment(FRAGMENT_HOME);
        }
    }

    @Override
    public void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_home:
                                showFragment(FRAGMENT_HOME);
                                break;
                            case R.id.action_project:
                                showFragment(FRAGMENT_PROJECT);
                                break;
                            case R.id.action_mine:
                                showFragment(FRAGMENT_MINE);
                                break;
                        }
                        return true;
                    }
                }
        );
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void showFragment(int index) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        position=index;
        switch (index) {
            case FRAGMENT_HOME:
            mToolbar.setTitle(R.string.title_home);
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (mHomeFragment==null) {
                mHomeFragment=HomeFragment.getInstance();
                fragmentTransaction.add(R.id.container,mHomeFragment,HomeFragment.class.getName());
                }else {
                    fragmentTransaction.show(mHomeFragment);
                }
                break;
            case FRAGMENT_PROJECT:
                mToolbar.setTitle(R.string.title_project);
                if (mProjectFragment==null) {
                    mProjectFragment=ProjectFragment.getInstance();
                    fragmentTransaction.add(R.id.container,mProjectFragment,ProjectFragment.class.getName());
                }else {
                    fragmentTransaction.show(mProjectFragment);
                }
                break;
            case FRAGMENT_MINE:
                mToolbar.setTitle(R.string.title_mine);
                if (mMineFragment==null) {
                    mMineFragment=MineFragment.getInstance();
                    fragmentTransaction.add(R.id.container,mMineFragment,MineFragment.class.getName());
                }else {
                    fragmentTransaction.show(mMineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        //如果不为空 先隐藏
        if (mHomeFragment!=null) {
            fragmentTransaction.hide(mHomeFragment);
        }
        if (mProjectFragment!=null) {
            fragmentTransaction.hide(mProjectFragment);
        }
        if (mMineFragment!=null) {
            fragmentTransaction.hide(mMineFragment);
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void showToast(String msg) {
        ToastUtils.showToast(this,msg);
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_setting:
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                showToast(getResources().getString(R.string.nav_setting));
                return false;
            case R.id.nav_share:
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                showToast(getResources().getString(R.string.nav_share));
                return false;
        }
        return false;
    }
}
