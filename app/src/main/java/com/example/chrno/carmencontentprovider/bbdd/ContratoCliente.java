package com.example.chrno.carmencontentprovider.bbdd;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Chrno on 12/01/2016.
 */
public class ContratoCliente {

    private ContratoCliente(){

    }

    public static abstract class TablaDisco implements BaseColumns {
        public static final String TABLA = "disco";
        public static final String NOMBRE = "nombre";
        public static final String ID_INTERPRETE = "idInterprete";

        //La autoridad es la cadena q identifica a qué contentprovider se llama
        public final static String AUTHORITY = "com.example.chrno.carmencontentprovider.Proveedor";
        //Definir como llego a la tabla cliente (a q tabla estoy llegando)
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLA;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }


    public static abstract class TablaCancion implements BaseColumns {
        public static final String TABLA = "cancion";
        public static final String TITULO = "titulo";
        public static final String ID_DISCO = "iddisco";

        public final static String AUTHORITY = "com.example.chrno.carmencontentprovider.Proveedor";
        //Definir como llego a la tabla cliente (a q tabla estoy llegando)
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLA;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }

    public static abstract class TablaInterprete implements BaseColumns {
        public static final String TABLA = "interprete";
        public static final String NOMBRE = "artista";


        public final static String AUTHORITY = "com.example.chrno.carmencontentprovider.Proveedor";
        //Definir como llego a la tabla cliente (a q tabla estoy llegando)
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLA;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }



}














