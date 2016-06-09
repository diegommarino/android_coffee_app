package com.example.jaga.cafeteriasmapsdrawer.atividade;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.jaga.cafeteriasmapsdrawer.R;
import com.example.jaga.cafeteriasmapsdrawer.fragments.FragmentBebidasFria;
import com.example.jaga.cafeteriasmapsdrawer.fragments.FragmentBebidasQuente;
import com.example.jaga.cafeteriasmapsdrawer.fragments.FragmentOutros;

import java.util.ArrayList;
import java.util.List;

public class ListaBebidasAtividade extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista_bebidas);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_hot_drink);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_cold_drink);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_food);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle extras = getIntent().getExtras();

        Fragment bebidasQuentes = new FragmentBebidasQuente();
        bebidasQuentes.setArguments(extras);
        adapter.addFragment(bebidasQuentes, "Bebidas Quentes");

        Fragment bebidasFrias = new FragmentBebidasFria();
        bebidasFrias.setArguments(extras);
        adapter.addFragment(bebidasFrias, "Bebidas Geladas");

        Fragment outros = new FragmentOutros();
        outros.setArguments(extras);
        adapter.addFragment(outros, "Outros");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return null to display only the icon
            return null;
        }
    }
}
