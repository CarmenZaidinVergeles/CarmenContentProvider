package com.example.chrno.carmencontentprovider;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Chrno on 13/01/2016.
 */
public class Adaptador extends CursorAdapter {

    private TextView tv1, tv2, tv3;


    public Adaptador(Context context, Cursor cursor){
        super(context, cursor,true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item, parent, false);
        return v;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        tv1 =(TextView)view.findViewById(R.id.tvAlbum2);
        tv2 =(TextView)view.findViewById(R.id.tvCancion2);
        tv3 =(TextView)view.findViewById(R.id.tvInterprete2);

        String[] array = cursor.getColumnNames();

        for(String columnas:array){
            if(columnas.contains("titulo")){

                tv1.setText(cursor.getString(cursor.getColumnIndex("titulo")));
            }

            if(columnas.contains("nombre")){
                tv2.setText(cursor.getString(cursor.getColumnIndex("nombre")));
            }

            if(columnas.contains("artista")){
                tv3.setText(cursor.getString(cursor.getColumnIndex("artista")));
            }

        }


    }
}
