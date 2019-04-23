package com.futrue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.futrue.follower.FragmentManager;

public class LordActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord);
        initObject();
        showAd();
    }


    /**
     * 函数：initObject()
     * 功能：初始化对象实例
     * 后置：onCreate()
     */
    private void initObject(){
        fragmentManager = new FragmentManager(this);
    }


    /**
     * 函数：showAd()
     * 功能：显示广告
     * 后置：onCreate()
     */
    private void showAd(){
        fragmentManager.addFragment(R.id.activity_lord, new AdFragment());
    }


    /**
     * 函数：obtainFragmentManager()
     * 功能：返回fragmentManager
     */
    public FragmentManager obtainFragmentManager(){
        return fragmentManager;
    }



    /**
     * 函数：onBackPressed()
     * 功能：重写监听返回键
     */
    @Override
    public void onBackPressed() {

        if(fragmentManager.getCount() > 1){
            fragmentManager.backFragment();
        } else  {
            Toast.makeText(this, "返回桌面", Toast.LENGTH_SHORT).show();
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
    }


}
