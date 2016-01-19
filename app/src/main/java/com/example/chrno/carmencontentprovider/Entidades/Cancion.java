package com.example.chrno.carmencontentprovider.Entidades;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.chrno.carmencontentprovider.bbdd.ContratoCliente;

/**
 * Created by Chrno on 12/01/2016.
 */
public class Cancion {
    private int id;
    private String titulo;
    private int idDisco;


    public Cancion() {

    }

    public Cancion(int id, String titulo, int idDisco) {
        this.id = id;
        this.titulo = titulo;
        this.idDisco = idDisco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idDisco=" + idDisco +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoCliente.TablaCancion._ID,this.id);
        cv.put(ContratoCliente.TablaCancion.TITULO,this.titulo);
        cv.put(ContratoCliente.TablaCancion.ID_DISCO,this.idDisco);
        return cv;
    }

    public void setDeseMovil(Cursor c){ //A partir del cursor del Content Provider de Musica del móvil
        //Datos del móvil pasan a un objeto cancion, y de éste a un content values(recoge los valores) y de éste a la base de datos creada
        this.id = c.getInt(c.getColumnIndex(MediaStore.Audio.Media._ID));
        this.titulo= c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));
        this.idDisco = c.getInt(c.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

    }

    /*public void setDesdeBBDD(Cursor c){
        //Los datos de nuestra base de datos, para pasarlos a la interfaz, se realiza a través del objeto cancion
        this.id = c.getInt(c.getColumnIndex(ContratoCliente.TablaCancion._ID));
        this.titulo = c.getString(c.getColumnIndex(ContratoCliente.TablaCancion.TITULO));
        this.idDisco = c.getInt(c.getColumnIndex(ContratoCliente.TablaCancion.ID_DISCO));

    }*/

}

