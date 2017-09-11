package com.rygital.producthunt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rygital.producthunt.deps.DaggerDeps;
import com.rygital.producthunt.deps.Deps;
import com.rygital.producthunt.networking.NetworkModule;

import java.io.File;

public class BaseApp extends AppCompatActivity {
    Deps deps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public Deps getDeps() {
        return deps;
    }
}
