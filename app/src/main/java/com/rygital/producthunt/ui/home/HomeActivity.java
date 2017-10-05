package com.rygital.producthunt.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rygital.producthunt.BaseApp;
import com.rygital.producthunt.R;
import com.rygital.producthunt.models.CategoriesListData;
import com.rygital.producthunt.models.CategoriesListResponse;
import com.rygital.producthunt.models.ProductListData;
import com.rygital.producthunt.models.ProductListResponse;
import com.rygital.producthunt.networking.Service;
import com.rygital.producthunt.ui.product.ProductActivity;

import javax.inject.Inject;

public class HomeActivity extends BaseApp implements HomeView {
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView list;
    private HomeAdapter adapter;

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;

    @Inject
    public Service service;
    private HomePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        presenter = new HomePresenter(service, this);
        presenter.getProductList(-1);
        presenter.getCategoriesList();
    }

    private void renderView(){
        setContentView(R.layout.activity_home);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        list = (RecyclerView) findViewById(R.id.list);
        swipeContainer.setColorSchemeResources(R.color.colorAccent);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void init(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        list.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HomeAdapter(getApplicationContext(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(ProductListData Item) {
                        presenter.startProductActivity(Item);
                    }
                });

        list.setAdapter(adapter);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getProductList(-1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showWait() {
        swipeContainer.setRefreshing(true);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        swipeContainer.setRefreshing(false);
        Toast.makeText(getApplicationContext(), appErrorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getProductListSuccess(ProductListResponse productListResponse, String categoryName) {
        adapter.clear();
        adapter.addAll(productListResponse.getPosts());
        swipeContainer.setRefreshing(false);
        toolbar.setTitle(categoryName);
    }

    @Override
    public void getCategoriesListSuccess(CategoriesListResponse categoriesListResponse) {
        Menu submenu = navigationView.getMenu().addSubMenu("Categories");
        for(CategoriesListData item : categoriesListResponse.getCategories()){
            submenu.add(0, item.getId(), 0, item.getName());
        }
        submenu.setGroupCheckable(0, true, true);

        navigationView.invalidate();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                presenter.getProductList(item.getItemId());

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void startProductActivity(ProductListData item) {
        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);

        intent.putExtra(ProductActivity.EXTRA_SCR, item.getScreenshot_url().getImage_url());
        intent.putExtra(ProductActivity.EXTRA_TITLE, item.getName());
        intent.putExtra(ProductActivity.EXTRA_DESC, item.getTagline());
        intent.putExtra(ProductActivity.EXTRA_URL, item.getRedirect_url());

        startActivity(intent);
    }
}
