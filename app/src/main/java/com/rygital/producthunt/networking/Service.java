package com.rygital.producthunt.networking;

import com.rygital.producthunt.models.CategoriesListResponse;
import com.rygital.producthunt.models.ProductListResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {
    private final NetworkService networkService;

    Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getProductList(final GetProductListCallback callback, String slug) {
        return networkService.getPosts("/v1/categories/" + slug + "/posts")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ProductListResponse>>() {
                    @Override
                    public Observable<? extends ProductListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ProductListResponse>(){
                    @Override
                    public void onCompleted(){

                    }

                    @Override
                    public void onError(Throwable e){
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(ProductListResponse productListResponse){
                        callback.onSuccess(productListResponse);
                    }
                });
    }

    public Subscription getCategoriesList(final GetCategoriesListCallback callback) {
        return networkService.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CategoriesListResponse>>() {
                    @Override
                    public Observable<? extends CategoriesListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CategoriesListResponse>(){
                    @Override
                    public void onCompleted(){

                    }

                    @Override
                    public void onError(Throwable e){
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(CategoriesListResponse categoriesListResponse){
                        callback.onSuccess(categoriesListResponse);
                    }
                });
    }

    public interface GetProductListCallback {
        void onSuccess(ProductListResponse productListResponse);
        void onError(NetworkError networkError);
    }

    public interface GetCategoriesListCallback {
        void onSuccess(CategoriesListResponse productListResponse);
        void onError(NetworkError networkError);
    }
}
