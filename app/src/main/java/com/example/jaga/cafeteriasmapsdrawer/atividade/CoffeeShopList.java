package com.example.jaga.cafeteriasmapsdrawer.atividade;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_coffee_shop, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Object o = listViewCafeterias.getItemAtPosition(info.position);
        int teste = item.getGroupId();
        cafeteria = (CafeteriaBean) o;

        switch (item.getItemId()) {
            case R.id.show_in_map:
                showInMap(cafeteria.getNome());
                break;
            case R.id.make_call:
                makeCall(cafeteria.getTelefone());
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void showInMap(String nomeCafeteria){
        Intent intent = new Intent(CoffeeShopList.this, CoffeeShopMapsActivity.class);
        intent.putExtra("coffeeShopName", nomeCafeteria);
        startActivity(intent);
    }

    private void makeCall(String telefone){
        String editedPhone = telefone.replaceAll("[^\\d]", "");
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+editedPhone));
        startActivity(intent);
    }

}
