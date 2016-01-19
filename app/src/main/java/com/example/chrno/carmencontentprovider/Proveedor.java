package com.example.chrno.carmencontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.chrno.carmencontentprovider.bbdd.Ayudante;
import com.example.chrno.carmencontentprovider.bbdd.ContratoCliente;

import static com.example.chrno.carmencontentprovider.bbdd.ContratoCliente.TablaCancion.CONTENT_URI;

/**
 * Created by Chrno on 12/01/2016.
 */
public class Proveedor extends ContentProvider {

    public static final UriMatcher convierteUri2Int;
    public static final int DISCOS = 1;
    public static final int DISCOS_ID = 2;
    public static final int CANCIONES = 3;
    public static final int CANCION_ID = 4;
    public static final int INTERPRETE = 5;
    public static final int INTERPRETE_ID = 6;
    private Ayudante abd;
    private static SQLiteDatabase db;
    static {
        convierteUri2Int = new UriMatcher(UriMatcher.NO_MATCH);
        convierteUri2Int.addURI(ContratoCliente.TablaDisco.AUTHORITY, ContratoCliente.TablaDisco.TABLA, DISCOS);
        convierteUri2Int.addURI(ContratoCliente.TablaDisco.AUTHORITY, ContratoCliente.TablaDisco.TABLA + "/#", DISCOS_ID);
        convierteUri2Int.addURI(ContratoCliente.TablaCancion.AUTHORITY, ContratoCliente.TablaCancion.TABLA, CANCIONES);
        convierteUri2Int.addURI(ContratoCliente.TablaCancion.AUTHORITY, ContratoCliente.TablaCancion.TABLA + "/#", CANCION_ID);
        convierteUri2Int.addURI(ContratoCliente.TablaInterprete.AUTHORITY, ContratoCliente.TablaInterprete.TABLA, INTERPRETE);
        convierteUri2Int.addURI(ContratoCliente.TablaInterprete.AUTHORITY, ContratoCliente.TablaInterprete.TABLA + "/#", INTERPRETE_ID);

    }


    @Override
    public boolean onCreate() {
        abd = new Ayudante((this.getContext()));
        db=abd.getReadableDatabase();
        return true;
    }



    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (convierteUri2Int.match(uri)) {
            case DISCOS:
                return ContratoCliente.TablaDisco.MJLTIPLE_MIME;
            case DISCOS_ID:
                return ContratoCliente.TablaDisco.SINGLE_MIME;
            case CANCIONES:
                return ContratoCliente.TablaCancion.MJLTIPLE_MIME;
            case CANCION_ID:
                return ContratoCliente.TablaCancion.SINGLE_MIME;
            case INTERPRETE:
                return ContratoCliente.TablaInterprete.MJLTIPLE_MIME;
            case INTERPRETE_ID:
                return ContratoCliente.TablaInterprete.SINGLE_MIME;
            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida " + uri);
        }

    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = abd.getWritableDatabase();
        // Comprobar que la uri utilizada para hacer la insercion es correcta
        /*if (convierteUri2Int.match(uri) != DISCOS || convierteUri2Int.match(uri) != CANCIONES || convierteUri2Int.match(uri)!=INTERPRETE ) {
            throw new IllegalArgumentException("URI desconocida : " + uri);//SI no es correcta la Uri
        }*/

        //Si el ContentValues es nulo, crea un ContentValues
        ContentValues contentValues;
        if (values == null) {
            throw new IllegalArgumentException("Discos null ");
        }


        switch (convierteUri2Int.match(uri)){

            case DISCOS:

                long rowIdDisco = db.insert(ContratoCliente.TablaDisco.TABLA, null, values);

                if (rowIdDisco > 0) {
                    //Si se ha insertado el elemento correctamente, entonces devolvemos la uri del elemento que se acaba de insertar
                    Uri uri_actividad = ContentUris.withAppendedId(ContratoCliente.TablaDisco.CONTENT_URI, rowIdDisco);
                    getContext().getContentResolver().notifyChange(uri_actividad, null);
                    return uri_actividad;
                }
                throw new SQLException("Error al insertar fila en : " + uri);

            case CANCIONES:

                long rowIdCancion = db.insert(ContratoCliente.TablaCancion.TABLA, null, values);
                if (rowIdCancion > 0) {
                    //Si se ha insertado el elemento correctamente, entonces devolvemos la uri del elemento que se acaba de insertar
                    Uri uri_actividad = ContentUris.withAppendedId(CONTENT_URI, rowIdCancion);
                    getContext().getContentResolver().notifyChange(uri_actividad, null);
                    return uri_actividad;
                }
                throw new SQLException("Error al insertar fila en : " + uri);

            case INTERPRETE:

                long rowIdInterprete = db.insert(ContratoCliente.TablaInterprete.TABLA, null, values);
                if (rowIdInterprete > 0) {
                    //Si se ha insertado el elemento correctamente, entonces devolvemos la uri del elemento que se acaba de insertar
                    Uri uri_actividad = ContentUris.withAppendedId(ContratoCliente.TablaInterprete.CONTENT_URI, rowIdInterprete);
                    getContext().getContentResolver().notifyChange(uri_actividad, null);
                    return uri_actividad;
                }
                throw new SQLException("Error al insertar fila en : " + uri);

        }
        return null;
    }




    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();// Vuelve a abrir la base de datos para conectar con ella en modo escritura
        long idActividad = ContentUris.parseId(uri);
        int match = convierteUri2Int.match(uri);//Obtengo la uri
        int affected;
        switch (match) {
            case DISCOS: //Muchos discos
                affected = db.delete(ContratoCliente.TablaDisco.TABLA,selection,selectionArgs);
                break;
            case DISCOS_ID: //Un sólo disco
                affected = db.delete(ContratoCliente.TablaDisco.TABLA,ContratoCliente.TablaDisco._ID + "= ?" , new String [] {idActividad + ""});
                break;
            case CANCIONES: //Muchas canciones
                affected = db.delete(ContratoCliente.TablaCancion.TABLA,selection,selectionArgs);
                break;
            case CANCION_ID: //Una sola cancion
                affected = db.delete(ContratoCliente.TablaCancion.TABLA,ContratoCliente.TablaCancion._ID + "= ?" , new String [] {idActividad + ""});
                break;
            case INTERPRETE: //Muchas interpretes
                affected = db.delete(ContratoCliente.TablaInterprete.TABLA,selection,selectionArgs);
                break;
            case INTERPRETE_ID: //Una sola interprete
                affected = db.delete(ContratoCliente.TablaInterprete.TABLA,ContratoCliente.TablaCancion._ID + "= ?" , new String [] {idActividad + ""});
                break;
            default:
                throw new IllegalArgumentException("Elemento actividad desconocido: " +
                        uri);
        }
        // Notificar cambio asociado a la urigetContext().getContentResolver().notifyChange(uri, null);
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;//Devuelve el numero de filas borradas
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 1;
        /*SQLiteDatabase db = abd.getWritableDatabase();
        int affected;
        switch (convierteUri2Int.match(uri)) {
            case DISCOS:
                affected = db.update(ContratoCliente.TablaDisco.TABLA, values, selection, selectionArgs);
                break;
            case DISCOS_ID:
                //Distintas formas de obtener el idActividad
                //uri.getLastPathSegment()
                //ContentUris.parseId(uri)
                //uri.getPathSegments().get(l)
                String idActividad = uri.getPathSegments().get(1);
                affected = db.update(ContratoCliente.TablaDisco.TABLA, values,
                        ContratoCliente.TablaDisco._ID + "= ?", new String[]{idActividad});
                break;
            case CANCIONES:
                affected = db.update(ContratoCliente.TablaDisco.TABLA, values, selection, selectionArgs);
                break;
            case CANCION_ID:
                //String idActividad = uri.getPathSegments().get(1);
                affected = db.update(ContratoCliente.TablaDisco.TABLA, values,
                        ContratoCliente.TablaDisco._ID + "= ?", new String[]{idActividad});

            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;*/
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Obtener base de datos
        //SQLiteDatabase db = abd.getReadableDatabase();
        // Comparar Uri
        int match = convierteUri2Int.match(uri);

        Cursor c;

        switch (match) {
            case DISCOS:
                // Consultando todos los registros de la tabla Disco
                c = db.query(ContratoCliente.TablaDisco.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
                Log.v("Camino", "nos hemos metido por el camino del case DISCOS");
                break;
            case DISCOS_ID:
                long idActividad = ContentUris.parseId(uri);
                // Consultando un solo registro basado en el Id del Uri
                c = db.query(ContratoCliente.TablaDisco.TABLA, projection, ContratoCliente.TablaDisco._ID + "= ? ", new String[]{idActividad + ""},
                        null, null, sortOrder);
                break;

            case CANCIONES:
                String s="Select * from cancion LEFT JOIN disco ON (cancion.iddisco = disco._id) LEFT JOIN interprete ON (disco.idinterprete=interprete._ID)";
                c=db.rawQuery(s,null);
                    while (c.moveToNext()){
                        for (int i = 0; i < c.getColumnCount(); i++) {
                            Log.v("BIEN", i + " " + c.getColumnName(i));
                            Log.v("BIEN", i + " " + c.getString(i));
                        }
                    }
                return c;
//                // Consultando todos los registros de la tabla cancion
//                c = db.query(ContratoCliente.TablaCancion.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
//                Log.v("Camino", "nos hemos metido por el camino del case CANCIONES");
//                break;
            case CANCION_ID:
                long idActividad2 = ContentUris.parseId(uri);
                // Consultando un solo registro basado en el Id del Uri
                c = db.query(ContratoCliente.TablaCancion.TABLA, projection, ContratoCliente.TablaCancion._ID + "= ? ", new String[]{idActividad2 + ""},
                        null, null, sortOrder);
                break;
            case INTERPRETE:
                //Consultando todos los registros de la tabla interprete
                c = db.query(ContratoCliente.TablaInterprete.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case INTERPRETE_ID:
                long idActividad3 = ContentUris.parseId(uri);
                // Consultando un solo registro basado en el Id del Uri
                c = db.query(ContratoCliente.TablaInterprete.TABLA, projection, ContratoCliente.TablaInterprete._ID + "= ? ", new String[]{idActividad3 + ""},
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }

         switch (match){
             case CANCIONES: case CANCION_ID:
                 c.setNotificationUri(getContext().getContentResolver(), ContratoCliente.TablaCancion.CONTENT_URI);
                 break;

             case DISCOS: case DISCOS_ID:
                 c.setNotificationUri(getContext().getContentResolver(), ContratoCliente.TablaDisco.CONTENT_URI);
                 break;

             case INTERPRETE:case INTERPRETE_ID:
                 c.setNotificationUri(getContext().getContentResolver(), ContratoCliente.TablaInterprete.CONTENT_URI);
                 break;
         }
        return c;


    }



}
