package com.michaelchentejada.georef.fragments.myservices.newservice;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.michaelchentejada.georef.R;
import com.michaelchentejada.georef.Singleton;
import com.michaelchentejada.georef.fragments.LoaderResult;
import com.michaelchentejada.georef.fragments.filterservices.FilterLoader;
import com.michaelchentejada.georef.fragments.filterservices.ServiceType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddServiceFragment extends Fragment implements LoaderManager.LoaderCallbacks<LoaderResult<ServiceType[]>>, View.OnClickListener {
	private View progressBar, errorBar, contentBar;
	private Spinner mSpinner;
	private Loader mLoader;
	private ArrayAdapter mAdapter;
	private List<ServiceType> mTypes;
	private EditText mName, mFee, mDesc;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		final View layout = inflater.inflate(R.layout.add_service, container, false);

		progressBar = layout.findViewById(R.id.progressBar);
		errorBar = layout.findViewById(R.id.error_bar);
		contentBar = layout.findViewById(R.id.content_bar);

		mTypes = new ArrayList<>();
		mSpinner = (Spinner) layout.findViewById(R.id.spinner);
		mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, mTypes);
		mSpinner.setAdapter(mAdapter);

		mName = (EditText) layout.findViewById(R.id.editTextName);
		mFee = (EditText) layout.findViewById(R.id.editTextFees);
		mDesc = (EditText) layout.findViewById(R.id.editTextDesc);

		layout.findViewById(R.id.retry).setOnClickListener(this);

		return layout;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onLoadFinished(Loader<LoaderResult<ServiceType[]>> loader,
							   LoaderResult<ServiceType[]> data) {

		mLoader = loader;
		LoaderResult.LoaderState state = data.getState();

		// Hide all views
		progressBar.setVisibility(View.GONE);
		errorBar.setVisibility(View.GONE);
		contentBar.setVisibility(View.GONE);

		switch (state) {
			case LOADING:
				progressBar.setVisibility(View.VISIBLE);
				break;
			case ERROR_INTERNET:
				errorBar.setVisibility(View.VISIBLE);
				break;
			case SUCCESS:
				final ServiceType prompt = new ServiceType();
				prompt.id = -1;
				prompt.name = getString(R.string.filter_spinner_hint);

				mTypes.clear();
				mTypes.add(prompt);
				mTypes.addAll(Arrays.asList(data.getResult()));
				mAdapter.notifyDataSetChanged();

				contentBar.setVisibility(View.VISIBLE);
				break;
		}
	}

	@Override
	public Loader<LoaderResult<ServiceType[]>> onCreateLoader(int id, Bundle args) {
		return new FilterLoader(getContext());
	}

	@Override
	public void onLoaderReset(Loader<LoaderResult<ServiceType[]>> loader) {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.retry:
				mLoader.startLoading();
				break;
			case R.id.submit:
				insertRecord();
				break;
		}
	}


	private void insertRecord(){
		// User id
		final String id = Settings.Secure.ANDROID_ID;

		// Validate input
		if (TextUtils.isEmpty(mName.getText().toString())){
			//TODO: ERROR
		}

		if (TextUtils.isEmpty(mFee.getText().toString())){
			//TODO: ERROR
		}

		if (mSpinner.getSelectedItemPosition() == 0){
			//TODO: ERROR
		}

		if (TextUtils.isEmpty(mDesc.getText().toString())){
			//TODO: ERROR
		}

		final int typeId = mTypes.get(mSpinner.getSelectedItemPosition()).id;

		new Thread(){
			@Override
			public void run() {
				try {
					OkHttpClient client = new OkHttpClient();
					RequestBody formBody = new FormBody.Builder()
							.add("user", id)
							.add("name", mName.getText().toString())
							.add("desc", mDesc.getText().toString())
							.add("fee", mFee.getText().toString())
							.add("type", typeId + "")
							.build();
					Request request = new Request.Builder()
							.url(Singleton.MY_IP + "submitService.php")
									.post(formBody)
									.build();

					Response response = client.newCall(request).execute();
				}catch (IOException e){}
			}
		}.start();
	}
}
