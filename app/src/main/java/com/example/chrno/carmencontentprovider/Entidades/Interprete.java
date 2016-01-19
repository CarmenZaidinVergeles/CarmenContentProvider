package com.example.chrno.carmencontentprovider.Entidades;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.chrno.carmencontentprovider.bbdd.ContratoCliente;

/**
 * Created by Chrno on 12/01/2016.
 */
public class Interprete {
    private int id;
    private String nombre;

    public Interprete(){

    }

    public Interprete(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Interprete{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoCliente.TablaInterprete._ID,this.id);
        cv.put(ContratoCliente.TablaInterprete.NOMBRE,this.nombre);
        return cv;
    }

    public void setDesdeMovil(Cursor c){ //A partir del cursor del Content Provider de Musica del móvil
        //Datos del móvil pasan a un objeto interprete, y de éste a un content values(recoge los valores) y de éste a la base de datos
        this.id = c.getInt(c.getColumnIndex(MediaStore.Audio.Artists._ID));
        System.out.println("id del artista " + c.getInt(c.getColumnIndex(MediaStore.Audio.Artists._ID)));
        this.nombre= c.getString(c.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
        System.out.println("nombre del artista" + c.getString(c.getColumnIndex(MediaStore.Audio.Artists.ARTIST)));
    }

    public void setDesdeBBDD(Cursor c){
        //Los datos de nuestra base de datos, para pasarlos a la interfaz, se realiza a través del objeto cancion
        this.id = c.getInt(c.getColumnIndex(ContratoCliente.TablaInterprete._ID));
        this.nombre= c.getString(c.getColumnIndex(ContratoCliente.TablaInterprete.NOMBRE));
    }


}
