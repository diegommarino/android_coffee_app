package com.example.jaga.cafeteriasmapsdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jaga.cafeteriasmapsdrawer.R;
import com.example.jaga.cafeteriasmapsdrawer.beans.ProdutoBean;

import java.util.List;

/**
 * Created by jaga on 08/06/16.
 */
public class CustomListAdapter extends BaseAdapter {
    private List<ProdutoBean> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext,  List<ProdutoBean> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.textViewNome = (TextView) convertView.findViewById(R.id.textViewNome);
            holder.textViewDescricao = (TextView) convertView.findViewById(R.id.textViewDescricao);
            holder.textViewPreco = (TextView) convertView.findViewById(R.id.textViewPreco);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProdutoBean ProdutoBean = this.listData.get(position);
        holder.textViewNome.setText(ProdutoBean.getNome());
        holder.textViewDescricao.setText(ProdutoBean.getDescricao());
        holder.textViewPreco.setText("R$ " + ProdutoBean.getPreco());

        return convertView;
    }

    static class ViewHolder {
        TextView textViewNome;
        TextView textViewDescricao;
        TextView textViewPreco;
    }
}
