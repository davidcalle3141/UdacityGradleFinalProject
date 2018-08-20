package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public  class CloudAsyncUtil extends AsyncTask<Pair<Context,Intent>, Void, List<String>> {
    private static MyApi myApiService = null;
    private Intent intent;
    private Context context;



    @Override
    protected synchronized List<String> doInBackground(Pair<Context,Intent> ... params) {
        //EspressoIdlingResource.Lock();


        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver



            myApiService = builder.build();
        }

        context = params[0].first;
        intent = params[0].second;
        try {
            List<String>jokes = myApiService.makeMeLaugh().execute().getData();

            return jokes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> boo = new ArrayList<>();
        boo.add("boo");
        boo.add("baah");
        return   boo;
    }

    @Override
    protected synchronized void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);

        if(strings!=null){
            EspressoIdlingResource.Unlock();
            Log.d("yup",strings.get(1));
            intent.putExtra("jokeTitle",strings.get(0));
            intent.putExtra("jokeBody",strings.get(1));
            context.startActivity(intent);
    }


    }
}