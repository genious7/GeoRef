package com.michaelchentejada.georef.fragments.filterservices;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.michaelchentejada.georef.R;
import com.michaelchentejada.georef.fragments.LoaderResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael Chen on 04/02/2016.
 */
public class FilterServiceFragment extends Fragment implements LoaderManager.LoaderCallbacks<LoaderResult<ServiceType[]>>, View.OnClickListener {
	private View progressBar, errorBar, contentBar;
	private Loader mLoader;

	private Spinner mSpinner;
	private ArrayAdapter mAdapter;
	private List<ServiceType> mTypes;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		final View layout = inflater.inflate(R.layout.service_filter, container, false);

		progressBar = layout.findViewById(R.id.progressBar);
		errorBar = layout.findViewById(R.id.error_bar);
		contentBar = layout.findViewById(R.id.content_bar);

		mTypes = new ArrayList<>();
		mSpinner = (Spinner) layout.findViewById(R.id.spinner);
		mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, mTypes);
		mSpinner.setAdapter(mAdapter);

		layout.findViewById(R.id.retry).setOnClickListener(this);
		layout.findViewById(R.id.submit).setOnClickListener(this);

		return layout;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<LoaderResult<ServiceType[]>> onCreateLoader(int id, Bundle args) {
		return new FilterLoader(getContext());
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

		switch (state){
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
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.retry:
				mLoader.startLoading();
				break;
			case R.id.submit:
				if (mSpinner.getSelectedItemPosition() == 0){
					// Prompt is selected
					new AlertDialog.Builder(getContext())
							.setTitle("Error")
							.setMessage("A service type must be selected")
							.setPositiveButton(android.R.string.ok, null)
							.show();
				}


				break;
		}
	}

	@Override
	public void onLoaderReset(Loader<LoaderResult<ServiceType[]>> loader) {}

}
