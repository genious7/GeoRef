package com.michaelchentejada.georef.fragments.about;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.michaelchentejada.georef.R;

/**
 * <p> A simple about dialog</p>
 * Created by Michael Chen on 04/02/2016.
 */
public class AboutFragment extends DialogFragment {

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

		builder.setTitle(R.string.about_title);
		builder.setMessage(R.string.about_content);
		builder.setPositiveButton(android.R.string.ok, null);

		return builder.create();
	}
}
