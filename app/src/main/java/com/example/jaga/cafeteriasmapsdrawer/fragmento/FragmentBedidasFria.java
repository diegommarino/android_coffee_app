package com.example.jaga.cafeteriasmapsdrawer.fragmento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jaga.cafeteriasmapsdrawer.R;
import com.example.jaga.cafeteriasmapsdrawer.beans.CategoriaBean;
import com.example.jaga.cafeteriasmapsdrawer.beans.ProdutoBean;
import com.example.jaga.cafeteriasmapsdrawer.dao.DataAccessObject;

import java.util.ArrayList;

public class FragmentBedidasFria extends Fragment {
	private ListView listViewProdutos;
	private ArrayList<ProdutoBean> produtos;
	private ArrayAdapter<ProdutoBean> adaptadorProduto;
	private int adaptadorLayout = android.R.layout.simple_list_item_1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_android, container, false);
		DataAccessObject dao = new DataAccessObject(getActivity());
		CategoriaBean categoria = dao.selectCategoriaByName("Bebidas Geladas");
		TextView textView = (TextView) view.findViewById(R.id.categoryLabel);
		textView.setText("Descrição: " + categoria.getDescricao());

		listViewProdutos = (ListView) view.findViewById(R.id.productsList);
		produtos = dao.productListByCategoryId(categoria.getId());
		if (produtos.isEmpty()) {
			dao.seedTableProduto(dao.getWritableDatabase());
			produtos = dao.productListByCategoryId(categoria.getId());;
			adaptadorProduto = new ArrayAdapter<ProdutoBean>(getActivity(), adaptadorLayout, produtos);
			listViewProdutos.setAdapter(adaptadorProduto);

		} else {
			adaptadorProduto = new ArrayAdapter<ProdutoBean>(getActivity(), adaptadorLayout, produtos);
			listViewProdutos.setAdapter(adaptadorProduto);
		}
		return view;
	}
}
