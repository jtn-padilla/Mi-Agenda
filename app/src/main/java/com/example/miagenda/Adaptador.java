package com.example.miagenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context contexto;
    private ArrayList<Entidad> listItems;

    public Adaptador(Context contexto, ArrayList<Entidad> listItems) {
        this.contexto = contexto;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entidad item = (Entidad) getItem(position);

        convertView = LayoutInflater.from(contexto).inflate(R.layout.elem_lista,null);
        ImageView ivGenero = (ImageView) convertView.findViewById(R.id.ivGenero);
        TextView tvContacto = (TextView) convertView.findViewById(R.id.tvContacto);

        ivGenero.setImageResource(item.getImg());
        tvContacto.setText(item.getNombre());

        return convertView;
    }
}
