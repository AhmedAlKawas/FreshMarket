package com.example.freshmarket.network.services;

import com.example.freshmarket.network.model.response.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WebServicesCategories {

    @GET("categories")
    Observable<List<Category>> getAllCategories();

}
