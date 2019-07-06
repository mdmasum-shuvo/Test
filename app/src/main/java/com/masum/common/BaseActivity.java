package com.masum.common;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.masum.test.R;


public abstract class BaseActivity extends AppCompatActivity {
    @SuppressWarnings("deprecation")
    private ProgressDialog progressDialog;
    private ViewDataBinding binding;
    private LinearLayout loadingView, noDataView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutResourceFile());
        initComponent();
        initFunctionality();
        initListener();


        // uncomment this line to disable ad
        // AdUtils.getInstance(mContext).disableBannerAd();
        // AdUtils.getInstance(mContext).disableInterstitialAd();
    }

    protected abstract int getLayoutResourceFile();

    protected abstract void initComponent();

    protected abstract void initFunctionality();

    protected abstract void initListener();


    public ViewDataBinding getBinding() {
        return binding;
    }

    public void showProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog = ProgressDialog.show(BaseActivity.this, null, getString(R.string.please_wait), true);
            }
        });
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
        }


    }

    public void initLoader() {
        loadingView = (LinearLayout) findViewById(R.id.loadingView);
        noDataView = (LinearLayout) findViewById(R.id.noDataView);
    }

    public void showLoader() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }

        if (noDataView != null) {
            noDataView.setVisibility(View.GONE);
        }
    }

    public void hideLoader() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
        if (noDataView != null) {
            noDataView.setVisibility(View.GONE);
        }
    }

    public void showEmptyView() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
        if (noDataView != null) {
            noDataView.setVisibility(View.VISIBLE);
        }
    }

    public void showToast(String masg){
        Toast.makeText(this, masg, Toast.LENGTH_SHORT).show();
    }
    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void startActvity(Activity activity, Class<?> tClass, boolean isFinish) {
        Intent intent = new Intent(activity, tClass);
        startActivity(intent);
        if (isFinish) {
            activity.finish();
        }

    }
}
