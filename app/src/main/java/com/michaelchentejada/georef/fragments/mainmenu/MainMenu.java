package com.michaelchentejada.georef.fragments.mainmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.michaelchentejada.georef.R;
import com.michaelchentejada.georef.fragments.about.AboutFragment;
import com.michaelchentejada.georef.fragments.filterservices.FilterServiceFragment;
import com.michaelchentejada.georef.fragments.myservices.MyServicesFragment;

/**
 * <p> The main menu of the application and the home fragment</p>
 * Created by Michael Chen on 04/02/2016.
 */
public class MainMenu extends Fragment implements AdapterView.OnItemClickListener {

	private static final MainMenuEntry[] MAIN_MENU = new MainMenuEntry[]{
			new MainMenuEntry(R.string.main_menu_map, R.drawable.ic_map_black),
			new MainMenuEntry(R.string.main_menu_get_service, R.drawable.ic_live_help),
			new MainMenuEntry(R.string.main_menu_offer_service, R.drawable.ic_mail),
			new MainMenuEntry(R.string.main_menu_local_info, R.drawable.ic_book),
			new MainMenuEntry(R.string.main_menu_settings, R.drawable.ic_settings),
			new MainMenuEntry(R.string.main_menu_about, R.drawable.ic_info),
	};

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		final View layout = inflater.inflate(R.layout.titled_list_view, container, false);

		// Initialize the adapter
		final BaseAdapter adapter = new MainMenuAdapter(getContext(), MAIN_MENU);

		// Initialize the list view
		final ListView listView = (ListView) layout.findViewById(R.id.list);
		listView.setOnItemClickListener(this);
		listView.setAdapter(adapter);

		return layout;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch ((int) id) {
			case R.string.main_menu_map:
				break;
			case R.string.main_menu_get_service: {
				final Fragment filterFragment = new FilterServiceFragment();
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, filterFragment, FilterServiceFragment.class.getName());
				ft.addToBackStack(FilterServiceFragment.class.getName());
				ft.commit();
				break;
			}
			case R.string.main_menu_offer_service:{
				final Fragment filterFragment = new MyServicesFragment();
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, filterFragment, MyServicesFragment.class.getName());
				ft.addToBackStack(MyServicesFragment.class.getName());
				ft.commit();
				break;
			}
			case R.string.main_menu_local_info:
				break;
			case R.string.main_menu_settings:
				break;
			case R.string.main_menu_about: {
				final DialogFragment dialog = new AboutFragment();
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				dialog.show(ft, AboutFragment.class.getName());
				break;
			}
		}
	}
}
