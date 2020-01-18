package com.appsnipp.milo.viewmodel;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appsnipp.milo.model.Upcoming;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
public class UpcomingViewModel extends ViewModel {
    private static final String API_KEY = "366305204cf4e7339266903ca1ae1ed1";
    private MutableLiveData<ArrayList<Upcoming>> listUpcoming = new MutableLiveData<>();

    public void setUpcoming(final String upcoming) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Upcoming> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" +API_KEY+ "&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject weather = list.getJSONObject(i);
                        Upcoming upcomingItems = new Upcoming(weather);
                        listItems.add(upcomingItems);
                    }
                    listUpcoming.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
    public LiveData<ArrayList<Upcoming>> getUpcoming() {
        return listUpcoming;
    }
}
