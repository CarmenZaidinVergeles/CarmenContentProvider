package com.example.chrno.carmencontentprovider.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chrno.carmencontentprovider.bbdd.ContratoCliente;

/**
 * Created by Chrno on 12/01/2016.
 */
public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="musica.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql="create table "+ ContratoCliente.TablaDisco.TABLA+ " ("+
                ContratoCliente.TablaDisco._ID+ " integer primary key, "+
                ContratoCliente.TablaDisco.NOMBRE+" text, "+
                ContratoCliente.TablaDisco.ID_INTERPRETE+" text)";

        db.execSQL(sql);


        String sql2;
        sql2="create table "+ContratoCliente.TablaCancion.TABLA+ " ("+
        ContratoCliente.TablaCancion._ID+ " integer primary key, "+
        ContratoCliente.TablaCancion.TITULO+" text, "+
        ContratoCliente.TablaCancion.ID_DISCO+" text)";

        db.execSQL(sql2);

        String sql3;
        sql3="create table "+ContratoCliente.TablaInterprete.TABLA+ " ("+
        ContratoCliente.TablaInterprete._ID+ " integer primary key, "+
        ContratoCliente.TablaInterprete.NOMBRE+" text)";

        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
