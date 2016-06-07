package com.example.jaga.cafeteriasmapsdrawer.atividade;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.jaga.cafeteriasmapsdrawer.R;
import com.example.jaga.cafeteriasmapsdrawer.fragmento.FragmentBebidasQuente;
import com.example.jaga.cafeteriasmapsdrawer.fragmento.FragmentBedidasFria;
import com.example.jaga.cafeteriasmapsdrawer.fragmento.FragmentOutros;

/**
 * Created by jaga on 12/05/16.
 */
public class ListaBebidasAtividade extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_lista_bebidas);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_drawable));
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab tabA = actionBar.newTab();
		tabA.setIcon(R.drawable.ic_cold_drink);
		tabA.setTabListener(new TabNavegacao(this, new FragmentBedidasFria()));
		actionBar.addTab(tabA, false);

		Tab tabB = actionBar.newTab();
		tabB.setIcon(R.drawable.ic_hot_drink);
		tabB.setTabListener(new TabNavegacao(this, new FragmentBebidasQuente()));
		actionBar.addTab(tabB, false);

		Tab tabC = actionBar.newTab();
		tabC.setIcon(R.drawable.ic_food);
		tabC.setTabListener(new TabNavegacao(this, new FragmentOutros()));
		actionBar.addTab(tabC, false);

		if(savedInstanceState != null){
			int indiceTab = savedInstanceState.getInt("indiceTab");
			getActionBar().setSelectedNavigationItem(indiceTab);
		}
		else{
			getActionBar().setSelectedNavigationItem(0);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putInt("indiceTab", getActionBar().getSelectedNavigationIndex());
	}

}
