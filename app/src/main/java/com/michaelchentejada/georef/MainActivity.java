package com.michaelchentejada.georef;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.michaelchentejada.georef.fragments.mainmenu.MainMenu;

/**
 * <p>The main activity of the application. Initializes the first Fragment only; the rest of the
 * logic is handled in the fragments themselves.</p>
 *
 * <p>Created by Michael Chen on 04/02/2016.</p>
 */
public class MainActivity extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Add the toolbar as the action bar
		final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// If savedInstanceState is equal to null, the app is being initialized. Open the initial
		// fragment
		if (savedInstanceState == null){
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(R.id.content_frame, new MainMenu(), MainMenu.class.getName());
			ft.commit();
		}
	}
}
