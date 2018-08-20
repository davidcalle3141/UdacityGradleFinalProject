package com.udacity.gradle.builditbigger;

import java.util.concurrent.atomic.AtomicInteger;

public class CustomIdlingResource implements android.support.test.espresso.IdlingResource {
private final String mResourceName;
private final AtomicInteger counter = new AtomicInteger(0);
private volatile ResourceCallback resourceCallback;

public CustomIdlingResource(String resourceName){
        mResourceName = resourceName;
        }

@Override
public boolean isIdleNow() {
        return counter.get()==0;
        }

@Override
public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;

        }

@Override
public String getName() {
        return mResourceName;
        }
public void Lock(){counter.set(1);}
public void Unlock(){counter.set(0);}
        }