package com.masum.test.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.masum.Listener.DataLoadFailedListener;
import com.masum.Listener.ItemClickListener;
import com.masum.common.BaseActivity;
import com.masum.datamodel.Resource;
import com.masum.datamodel.ResponseData;
import com.masum.test.R;
import com.masum.test.databinding.ActivityAnotherBinding;
import com.masum.test.viewmodel.DataResponseViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnotherActivity extends BaseActivity implements DataLoadFailedListener {

    private ActivityAnotherBinding binding;
    private DataResponseViewModel viewModel;
    private DataAdapter adapter;
    private List<Resource> responseDataList;

    @Override
    protected int getLayoutResourceFile() {
        return R.layout.activity_another;
    }

    @Override
    protected void initComponent() {
        binding = (ActivityAnotherBinding) getBinding();
        responseDataList = new ArrayList<>();
        viewModel = ViewModelProviders.of(this).get(DataResponseViewModel.class);
        viewModel.getRepository().setListener(this);
        binding.recylerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataAdapter(this, responseDataList);
        binding.recylerview.setAdapter(adapter);

    }

    @Override
    protected void initFunctionality() {

        initLoader();
        showLoader();
        viewModel.getResponseData().observe(this, new Observer<List<Resource>>() {
            @Override
            public void onChanged(@Nullable List<Resource> responseData) {
                hideLoader();
                if (!responseDataList.isEmpty()) {
                    responseData.clear();
                }
                responseDataList.addAll(responseData);
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    protected void initListener() {

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onitemClick(int position) {
                // showToast("click");
            }

            @Override
            public void onitemLongClick(int position) {
                deleteAlert(position);


            }
        });
    }

    @Override
    public void onFailed(String errorCode, String msg) {
        hideLoader();
        showAlertDialog(errorCode, msg);
    }


    private void deleteAlert(final int position) {

        android.app.AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new android.app.AlertDialog.Builder(AnotherActivity.this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new android.app.AlertDialog.Builder(AnotherActivity.this);
        }
        builder.setTitle(getString(R.string.remove));
        builder.setMessage(getResources().getString(R.string.delete));
        builder.setNegativeButton(getResources().getString(R.string.no), null);
        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                responseDataList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }
}
