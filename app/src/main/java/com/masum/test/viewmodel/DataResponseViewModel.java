package com.masum.test.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.masum.datamodel.Resource;
import com.masum.datamodel.ResponseData;
import com.masum.test.repository.DataResponseRepository;

import java.util.List;

public class DataResponseViewModel extends AndroidViewModel {
    private Application application;

    private DataResponseRepository repository;

    public DataResponseViewModel(Application application) {
        super(application);
        this.application = application;
        repository = new DataResponseRepository();
    }

    public MutableLiveData<List<Resource>> getResponseData() {
        return repository.getResponseData();
    }

    public DataResponseRepository getRepository() {
        return repository;
    }
}
