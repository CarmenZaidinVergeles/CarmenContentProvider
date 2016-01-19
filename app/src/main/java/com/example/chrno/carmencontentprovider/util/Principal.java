package com.example.chrno.carmencontentprovider.util;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.chrno.carmencontentprovider.Adaptador;
import com.example.chrno.carmencontentprovider.bbdd.ContratoCliente;
import com.example.chrno.carmencontentprovider.Entidades.Cancion;
import com.example.chrno.carmencontentprovider.Entidades.Disco;
import com.example.chrno.carmencontentprovider.Entidades.Interprete;
import com.example.chrno.carmencontentprovider.OrigenMusica;
import com.example.chrno.carmencontentprovider.Proveedor;
import com.example.chrno.carmencontentprovider.R;

public class Principal extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private Proveedor proveedor;

    private Uri uriD = ContratoCliente.TablaDisco.CONTENT_URI;
    private Uri uriC = ContratoCliente.TablaCancion.CONTENT_URI;
    private Uri uriI = ContratoCliente.TablaInterprete.CONTENT_URI;
    private ListView lv;
    private Adaptador ad;
    private Disco disco;
    private Cancion cancion;
    private Interprete interprete;
    private SharedPreferences preferencia;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        iniciar();

        //Cursor cursorDisco = getContentResolver().query(uriD, null , null, null,null); //Obtener el cursor de la tabla Disco
        //Cursor cursorCancion = getContentResolver().query(uriC, null, null, null, null);//Obtener el cursor de la tabla Cancion
        //Cursor cursorInterprete = getContentResolver().query(uriI, null, null, null, null);//Obtener el cursor de la tabla Interprete

//        //Insertar en mi base de datos
//        OrigenMusica.sacarCanciones(this);
//        OrigenMusica.sacarAlbums(this);
//        OrigenMusica.sacarInterpretes(this);
//
//        Cursor c = getContentResolver().query(ContratoCliente.TablaCancion.CONTENT_URI, null, null, null, null);
//        ad = new Adaptador(this,c);
//        ListView lv = (ListView)findViewById(R.id.listView);
//        lv.setAdapter(ad);
//
//        getLoaderManager().initLoader(1, null, this);
    }

    public void iniciar() {
        preferencia = getSharedPreferences("prefer", this.MODE_PRIVATE);

        if (preferencia.getBoolean("sincro", false)) {
            sacarDeMiBBDD();

        } else {
            //Insertar en mi base de datos los datos del móvil
            OrigenMusica.sacarCanciones(this);
            OrigenMusica.sacarAlbums(this);
            OrigenMusica.sacarInterpretes(this);

            //Sacar los datos de mi móvil
            sacarDeMiBBDD();

            SharedPreferences.Editor ed;
            ed = preferencia.edit();
            ed.putBoolean("sincro", true);
            ed.commit();
        }
    }

    public void sacarDeMiBBDD(){
        Cursor c = getContentResolver().query(ContratoCliente.TablaCancion.CONTENT_URI, null, null, null, null);
        ad = new Adaptador(this, c);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(ad);

        getLoaderManager().initLoader(1, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        return new CursorLoader(this, uriC, null, null, null, ContratoCliente.TablaCancion.TITULO+" collate localized asc");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ad.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
      ad.swapCursor(null);

    }
}





