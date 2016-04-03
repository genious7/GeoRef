package com.michaelchentejada.georef.fragments.myservices;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelchentejada.georef.R;
import com.michaelchentejada.georef.fragments.LoaderResult;
import com.michaelchentejada.georef.fragments.filterservices.FilterLoader;
import com.michaelchentejada.georef.fragments.filterservices.ServiceType;
import com.michaelchentejada.georef.fragments.myservices.newservice.AddServiceFragment;

public class MyServicesFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<LoaderResult<ServiceType[]>>{

	View mErrorBar, mProgressBar;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		final View layout = inflater.inflate(R.layout.my_services, container, false);

		layout.findViewById(R.id.add).setOnClickListener(this);

		mErrorBar = layout.findViewById(R.id.error_bar);
		mProgressBar = layout.findViewById(R.id.progressBar);

		mProgressBar.setVisibility(View.GONE);
		mErrorBar.setVisibility(View.GONE);

		return layout;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.add:{
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, new AddServiceFragment(), AddServiceFragment.class.getName());
				ft.addToBackStack(AddServiceFragment.class.getName());
				ft.commit();
				break;
			}
		}
	}

	@Override
	public Loader<LoaderResult<ServiceType[]>> onCreateLoader(int id, Bundle args) {
		return new FilterLoader(getContext());
	}

	@Override
	public void onLoadFinished(Loader<LoaderResult<ServiceType[]>> loader,
							   LoaderResult<ServiceType[]> data) {

	}

	@Override
	public void onLoaderReset(Loader<LoaderResult<ServiceType[]>> loader) {

	}
}
