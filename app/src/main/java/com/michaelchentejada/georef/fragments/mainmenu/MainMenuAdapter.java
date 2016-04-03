package com.michaelchentejada.georef.fragments.mainmenu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * <p> An ArrayAdapter used to display the menu entries on the ListView</p>
 * Created by Michael Chen on 04/02/2016.
 */
public class MainMenuAdapter extends ArrayAdapter<MainMenuEntry>{
	/**
	 * The padding around the icons
	 */
	private final int mDrawablePadding;

	public MainMenuAdapter(Context context, MainMenuEntry[] objects) {
		super(context, android.R.layout.simple_list_item_1, objects);

		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		mDrawablePadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, metrics);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// If there are no views ready for recycling, create a new view
		if (convertView == null){
			LayoutInflater inflater = ((AppCompatActivity) getContext()).getLayoutInflater();
			convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

			((TextView) convertView).setCompoundDrawablePadding(mDrawablePadding);
		}

		// Fill in the blanks
		final MainMenuEntry menuEntry = getItem(position);
		final TextView textView = (TextView) convertView;
		textView.setText(menuEntry.text);
		textView.setCompoundDrawablesWithIntrinsicBounds(menuEntry.icon, 0, 0, 0);

		return textView;
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).text;
	}
}
