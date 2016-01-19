package com.example.chrno.carmencontentprovider.Entidades;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.chrno.carmencontentprovider.bbdd.ContratoCliente;

/**
 * Created by Chrno on 12/01/2016.
 */
public class Disco {
    private int id;
    private String nombre;
    private int idInterprete;

   public Disco(){
   }

    public Disco(int id, String nombre, int idInterprete) {
        this.id = id;
        this.nombre = nombre;
        this.idInterprete = idInterprete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdInterprete() {
        return idInterprete;
    }

    public void setIdInterprete(int idInterprete) {
        this.idInterprete = idInterprete;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idInterprete=" + idInterprete +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoCliente.TablaDisco._ID,this.id);
        cv.put(ContratoCliente.TablaDisco.NOMBRE,this.nombre);
        cv.put(ContratoCliente.TablaDisco.ID_INTERPRETE,this.idInterprete);
        return cv;
    }

    public void setDesdeMovil(Cursor c){ //A partir del cursor del Content Provider de Musica del móvil
        //Datos del móvil pasan a un objeto disco, y de éste a un content values(recoge los valores) y de éste a la base de datos
        this.id = c.getInt(c.getColumnIndex(MediaStore.Audio.Albums._ID));
        this.nombre = c.getString(c.getColumnIndex(MediaStore.Audio.Albums.ALBUM));
        this.idInterprete= c.getInt(c.getColumnIndex("artist_id"));
        System.out.println("idArtista es " + c.getColumnIndex("artist_id"));
        System.out.println("Imprimir columnas del album: " + MediaStore.Audio.AlbumColumns.ARTIST);

    }

    /*public void setDesdeBBDD(Cursor c){
        //Los datos de nuestra base de datos, para pasarlos a la interfaz, se realiza a través del objeto disco
        this.id = c.getInt(c.getColumnIndex(ContratoCliente.TablaDisco._ID));
        this.nombre = c.getString(c.getColumnIndex(ContratoCliente.TablaDisco.NOMBRE));
        this.idInterprete = c.getInt(c.getColumnIndex(ContratoCliente.TablaDisco.ID_INTERPRETE));
    }*/

}
