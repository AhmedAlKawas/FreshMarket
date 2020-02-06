package com.example.freshmarket.repository;

import com.example.freshmarket.network.model.response.Category;
import com.example.freshmarket.network.services.WebServicesCategories;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesRepository {

    WebServicesCategories webServicesCategories;

    public CategoriesRepository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        String BASE_URL = "https://5bcce576cf2e850013874767.mockapi.io/task/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        webServicesCategories = retrofit.create(WebServicesCategories.class);

    }

    private static class Loader {
        static CategoriesRepository categoriesRepository = new CategoriesRepository();
    }

    public static CategoriesRepository getInstance() {
        return Loader.categoriesRepository;
    }

    public Observable<List<Category>> getCategoriesList() {
        return Observable.create(emitter -> {
            webServicesCategories.getAllCategories().subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io()).subscribe(new Observer<List<Category>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<Category> categories) {
                    emitter.onNext(categories);
                }

                @Override
                public void onError(Throwable e) {
                    emitter.onError(e);
                }

                @Override
                public void onComplete() {

                }
            });
        });
    }

}
