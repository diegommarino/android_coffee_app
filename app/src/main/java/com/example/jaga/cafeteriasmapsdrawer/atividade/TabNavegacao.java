package com.example.jaga.cafeteriasmapsdrawer.atividade;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.jaga.cafeteriasmapsdrawer.R;

/**
 * Created by jaga on 12/05/16.
 */
public class TabNavegacao implements ActionBar.TabListener{
    private Fragment fragment;
    private FragmentActivity fragmentActivity;

    public TabNavegacao(FragmentActivity fragmentActivity, Fragment fragment){

        this.fragment = fragment;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        Log.i("Script", "onTabReselected()");
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        FragmentTransaction fragmentTransation = fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransation.replace(R.id.fragment, fragment);
        fragmentTransation.commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        FragmentTransaction fragmentTransation = fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransation.remove(fragment);
        fragmentTransation.commit();
    }
}
