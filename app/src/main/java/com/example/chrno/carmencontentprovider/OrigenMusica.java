package com.example.chrno.carmencontentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.chrno.carmencontentprovider.Entidades.Cancion;
import com.example.chrno.carmencontentprovider.Entidades.Disco;
import com.example.chrno.carmencontentprovider.Entidades.Interprete;
import com.example.chrno.carmencontentprovider.bbdd.ContratoCliente;

/**
 * Created by Chrno on 15/01/2016.
 */
public class OrigenMusica {
    public static void sacarCanciones(Context contexto){

        Cursor cur = contexto.getContentResolver().query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Audio.Media.IS_MUSIC+ " = 1", null, null);
        while (cur.moveToNext()) {
            Cancion cancion = new Cancion();
            cancion.setDeseMovil(cur);
            ContentValues cv = cancion.getContentValues();
            contexto.getContentResolver().insert(ContratoCliente.TablaCancion.CONTENT_URI, cv);
        }
        cur.close();
    }

    public static void sacarAlbums(Context contexto){
        System.out.println(" imprime error ");
        Cursor cur = contexto.getContentResolver().query(android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cur.moveToNext()) {
            Disco disco = new Disco();
            disco.setDesdeMovil(cur);
            ContentValues cv = disco.getContentValues();
            contexto.getContentResolver().insert(ContratoCliente.TablaDisco.CONTENT_URI, cv);
        }
        cur.close();
    }

    public static void sacarInterpretes(Context contexto){
        Cursor cur = contexto.getContentResolver().query(android.provider.MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cur.moveToNext()) {
            Interprete interprete = new Interprete();
            interprete.setDesdeMovil(cur);
            ContentValues cv = interprete.getContentValues();
            contexto.getContentResolver().insert(ContratoCliente.TablaInterprete.CONTENT_URI, cv);
        }

        cur.close();
    }
}

