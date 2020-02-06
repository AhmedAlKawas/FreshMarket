package com.example.freshmarket.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.freshmarket.network.model.response.Category;
import com.example.freshmarket.repository.CategoriesRepository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CategoriesViewModel extends ViewModel {

    CategoriesRepository categoriesRepo = CategoriesRepository.getInstance();

    private MutableLiveData<List<Category>> getCategoriesResponse = new MutableLiveData<>();

    public MutableLiveData<List<Category>> returnCategoriesList() {
        return getCategoriesResponse;
    }

    public void getCategories() {

        categoriesRepo.getCategoriesList().subscribe(new Observer<List<Category>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Category> categoriesList) {
                if (null != categoriesList) {
                    getCategoriesResponse.postValue(categoriesList);
                } else
                    getCategoriesResponse.postValue(null);
            }

            @Override
            public void onError(Throwable e) {
                getCategoriesResponse.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
