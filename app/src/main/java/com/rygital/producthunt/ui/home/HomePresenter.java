package com.rygital.producthunt.ui.home;

import com.rygital.producthunt.models.CategoriesListData;
import com.rygital.producthunt.models.CategoriesListResponse;
import com.rygital.producthunt.models.ProductListData;
import com.rygital.producthunt.models.ProductListResponse;
import com.rygital.producthunt.networking.NetworkError;
import com.rygital.producthunt.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

class HomePresenter {
    private final Service service;
    private final HomeView homeView;
    private CompositeSubscription subscriptions;

    private String categorySlug = "tech";
    private String categoryName = "Tech";


    CategoriesListResponse categories;

    HomePresenter(Service service, HomeView homeView) {
        this.service = service;
        this.homeView = homeView;
        this.subscriptions = new CompositeSubscription();
    }

    void getProductList(int id) {
        homeView.showWait();

        if (categories != null && id != -1) {
            for(CategoriesListData item : categories.getCategories())
                if (item.getId() == id) {
                    categorySlug = item.getSlug();
                    categoryName = item.getName();
                }
        }

        Subscription subscription = service.getProductList(new Service.GetProductListCallback() {
            @Override
            public void onSuccess(ProductListResponse productListResponse) {
                homeView.getProductListSuccess(productListResponse, categoryName);
            }

            @Override
            public void onError(NetworkError networkError) {
                homeView.onFailure(networkError.getAppErrorMessage());
            }
        }, categorySlug);

        subscriptions.add(subscription);
    }

    void getCategoriesList() {
        if(categories != null) {
            homeView.getCategoriesListSuccess(categories);
            return;
        }

        Subscription subscription = service.getCategoriesList(new Service.GetCategoriesListCallback() {
            @Override
            public void onSuccess(CategoriesListResponse categoriesListResponse) {
                categories = categoriesListResponse;
                homeView.getCategoriesListSuccess(categoriesListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                homeView.onFailure(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

    void startProductActivity(ProductListData item) {
        homeView.startProductActivity(item);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
