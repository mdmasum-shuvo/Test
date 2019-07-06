package com.masum.test.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.masum.Listener.DataLoadFailedListener;
import com.masum.common.BaseActivity;
import com.masum.datamodel.Login;
import com.masum.datamodel.LoginResponse;
import com.masum.datamodel.Resource;
import com.masum.test.R;
import com.masum.test.databinding.ActivityMainBinding;
import com.masum.test.viewmodel.DataResponseViewModel;
import com.masum.test.viewmodel.LoginViewModel;

import java.util.List;

public class MainActivity extends BaseActivity implements DataLoadFailedListener {

    ActivityMainBinding binding;
    LoginViewModel viewModel;

    @Override
    protected int getLayoutResourceFile() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponent() {
        binding = (ActivityMainBinding) getBinding();
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        viewModel.getLoginRepository().setListener(this);
    }

    @Override
    protected void initFunctionality() {



        /*   */
    }

    @Override
    protected void initListener() {

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etUsername.getText().toString();
                String etPassword = binding.etPassword.getText().toString();
                Login login = new Login();
                login.setEmail(email);
                login.setPassword(etPassword);
                if (!isValid(login)) {
                    return;
                }
                viewModel.getResponse(login).observe(MainActivity.this, new Observer<LoginResponse>() {
                    @Override
                    public void onChanged(@Nullable LoginResponse o) {
                        startActvity(MainActivity.this, AnotherActivity.class, false);
                        showToast(getString(R.string.loggin_success));
                    }
                });

            }
        });
    }

    private boolean isValid(Login login) {
        if (login.getPassword().isEmpty() || login.getPassword() == "" || login.getEmail() == null || login.getEmail() == "") {
            showAlertDialog(getString(R.string.error), getString(R.string.empty_check));
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(login.getEmail()).matches()) {
            showAlertDialog(getString(R.string.error), getString(R.string.email_check));
            return false;
        }

        return true;
    }

    @Override
    public void onFailed(String errorCode, String msg) {
        showAlertDialog(errorCode, msg);
    }
}
