package com.example.jaga.cafeteriasmapsdrawer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jaga.cafeteriasmapsdrawer.R;
import com.example.jaga.cafeteriasmapsdrawer.adapters.CustomListAdapter;
import com.example.jaga.cafeteriasmapsdrawer.beans.CategoriaBean;
import com.example.jaga.cafeteriasmapsdrawer.beans.ProdutoBean;
import com.example.jaga.cafeteriasmapsdrawer.dao.DataAccessObject;

import java.util.ArrayList;

public class FragmentBebidasQuente extends Fragment {

    private ListView listViewProdutos;
    private ArrayList<ProdutoBean> produtos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        DataAccessObject dao = new DataAccessObject(getActivity());
        CategoriaBean categoria = dao.selectCategoriaByName("Bebidas Quentes");
        TextView textView = (TextView) view.findViewById(R.id.categoryLabel);
        textView.setText("Descrição: " + categoria.getDescricao());

        listViewProdutos = (ListView) view.findViewById(R.id.productsList);
        Bundle bundle = this.getArguments();
        produtos = dao.productListByCategoriaCafeteriaId(categoria.getId(), bundle.getInt("cafeteriaId"));
        listViewProdutos.setAdapter(new CustomListAdapter(view.getContext(), produtos));
        return view;
    }
}
