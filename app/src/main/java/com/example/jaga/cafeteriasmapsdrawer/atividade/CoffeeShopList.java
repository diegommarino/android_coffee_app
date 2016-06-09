package com.example.jaga.cafeteriasmapsdrawer.atividade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jaga.cafeteriasmapsdrawer.R;
import com.example.jaga.cafeteriasmapsdrawer.adapters.CoffeeShopListAdapter;
import com.example.jaga.cafeteriasmapsdrawer.beans.CafeteriaBean;
import com.example.jaga.cafeteriasmapsdrawer.dao.DataAccessObject;

import java.util.ArrayList;

public class CoffeeShopList extends AppCompatActivity {

    private ListView listViewCafeterias;
    private ArrayList<CafeteriaBean> cafeterias;
    private CafeteriaBean cafeteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_coffee_shop_list);

        listViewCafeterias = (ListView) findViewById(R.id.CafeteriaslistView);
        DataAccessObject dao = new DataAccessObject(this);

        cafeterias = dao.selectInstances("cafeteria");
        listViewCafeterias.setAdapter(new CoffeeShopListAdapter(this, cafeterias));

        registerForContextMenu(listViewCafeterias);

        listViewCafeterias.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listViewCafeterias.getItemAtPosition(position);
                cafeteria = (CafeteriaBean) o;
                Toast.makeText(CoffeeShopList.this, "Selected: " + cafeteria.getNome(), Toast.LENGTH_LONG).show();
                return false;
            }
        });


        listViewCafeterias.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CoffeeShopList.this, ListaBebidasAtividade.class);
                Object o = listViewCafeterias.getItemAtPosition(position);
                cafeteria = (CafeteriaBean) o;
                intent.putExtra("cafeteriaId", cafeteria.getId());
                startActivity(intent);
            }
        });
    }

}
