package com.michaelchentejada.georef.fragments.filterservices;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.Gson;
import com.michaelchentejada.georef.Singleton;
import com.michaelchentejada.georef.fragments.LoaderResult;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FilterLoader extends AsyncTaskLoader<LoaderResult<ServiceType[]>> {

	private ServiceType[] mResult;

	public FilterLoader(Context context) {
		super(context);
		startLoading();
	}

	@Override
	protected void onStartLoading() {
		if (mResult == null) {
			deliverResult(new LoaderResult<ServiceType[]>(null, LoaderResult.LoaderState.LOADING));
			forceLoad();
		} else {
			deliverResult(new LoaderResult<>(mResult, LoaderResult.LoaderState.SUCCESS));
		}
	}

	@Override
	public LoaderResult<ServiceType[]> loadInBackground() {

		final String json;

		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(Singleton.MY_IP + "service_types.php").build();

			Response response = client.newCall(request).execute();
			json = response.body().string();
		} catch (IOException e){
			return new LoaderResult<>(null, LoaderResult.LoaderState.ERROR_INTERNET);
		}

		Gson gson = new Gson();
		mResult = gson.fromJson(json, ServiceType[].class);

		return new LoaderResult<>(mResult, LoaderResult.LoaderState.SUCCESS);
	}
}
