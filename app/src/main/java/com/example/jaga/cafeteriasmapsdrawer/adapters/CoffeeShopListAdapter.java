package com.example.jaga.cafeteriasmapsdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jaga.cafeteriasmapsdrawer.R;
import com.example.jaga.cafeteriasmapsdrawer.beans.CafeteriaBean;

import java.util.List;

/**
 * Created by jaga on 09/06/16.
 */
public class CoffeeShopListAdapter extends BaseAdapter{
    private List<CafeteriaBean> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CoffeeShopListAdapter(Context aContext,  List<CafeteriaBean> listData) {
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
            convertView = layoutInflater.inflate(R.layout.layout_coffee_shop_list_item, null);
            holder = new ViewHolder();
            holder.textViewNome = (TextView) convertView.findViewById(R.id.textViewNome);
            holder.textViewDescricao = (TextView) convertView.findViewById(R.id.textViewDescricao);
            holder.textViewTelefone = (TextView) convertView.findViewById(R.id.textViewTelefone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CafeteriaBean CafeteriaBean = this.listData.get(position);
        holder.textViewNome.setText(CafeteriaBean.getNome());
        holder.textViewDescricao.setText(CafeteriaBean.getDescricao());
        holder.textViewTelefone.setText(CafeteriaBean.getTelefone());

        return convertView;
    }

    static class ViewHolder {
        TextView textViewNome;
        TextView textViewDescricao;
        TextView textViewTelefone;
    }
}
