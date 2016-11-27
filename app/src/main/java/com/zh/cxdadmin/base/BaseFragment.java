package com.zh.cxdadmin.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zh.cxdadmin.R;
import com.zh.cxdadmin.utils.ToastUtil;

/**
 * Created by win7 on 2016/9/18.
 */
public class BaseFragment extends Fragment {
    protected BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) this.getActivity();
        Log.d(BaseApplication.LOG_TAG, getClass().getName());
    }


 /*   @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        textView = (TextView) activity.findViewById(R.id.toolbar_tv);
        if(toolbar!=null){
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
    }*/


  /*  public ActionBar getSupportActionBar(){
        return ((AppCompatActivity)activity).getSupportActionBar();
    }

    protected void setTitle(String title){
//        getSupportActionBar().setTitle(title);
        textView.setText(title);
    }*/
}
