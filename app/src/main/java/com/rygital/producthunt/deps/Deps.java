package com.rygital.producthunt.deps;

import com.rygital.producthunt.home.HomeActivity;
import com.rygital.producthunt.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface deps {
    void inject(HomeActivity homeActivity);
}
