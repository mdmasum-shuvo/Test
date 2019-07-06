package com.masum.test.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.masum.datamodel.Login;
import com.masum.datamodel.LoginResponse;
import com.masum.datamodel.Resource;
import com.masum.test.repository.LoginRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    private LoginRepository loginRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository();
    }


    public MutableLiveData<LoginResponse> getResponse(Login login) {
        return loginRepository.getLoginResponse(login);
    }
    public LoginRepository getLoginRepository() {
        return loginRepository;
    }
}
