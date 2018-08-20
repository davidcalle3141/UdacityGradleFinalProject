package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingResource;

class EspressoIdlingResource {
    private static final String RESOURCE = "GLOBAL";
    private static final CustomIdlingResource mCustomIdlingResource =
            new CustomIdlingResource(RESOURCE);

    public static void Lock(){
        mCustomIdlingResource.Lock();
    }
    public static void Unlock(){
        mCustomIdlingResource.Unlock();
    }
    public static IdlingResource getIdlingResource(){
        return mCustomIdlingResource;
    }
}
