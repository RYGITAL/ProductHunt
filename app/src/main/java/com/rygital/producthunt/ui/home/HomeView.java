package com.rygital.producthunt.ui.home;

import com.rygital.producthunt.models.CategoriesListResponse;
import com.rygital.producthunt.models.ProductListData;
import com.rygital.producthunt.models.ProductListResponse;

public interface HomeView {
    void showWait();
    void onFailure(String appErrorMessage);
    void getProductListSuccess(ProductListResponse productListResponse, String categoryName);
    void getCategoriesListSuccess(CategoriesListResponse categoriesListResponse);
    void startProductActivity(ProductListData item);
}
