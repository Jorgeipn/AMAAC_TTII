package com.enterprises.devare.amaac_avanzaado.modelo;

import android.content.res.Resources;

public class Pictograma {

    //<editor-fold desc="ETIQUETAS">
    public static final int TIPO_PIC_NORMAL=0;
    public static final int TIPO_PIC_SELECCIONADO=1;

    public static final int CAT_VERBOS_AUX  =1;
    public static final int CAT_RESPUESTAS  =2;
    public static final int CAT_VERBOS      =3;
    public static final int CAT_BEBIDAS     =4;
    public static final int CAT_FRUTAS      =5;
    public static final int CAT_VERDURAS    =6;
    public static final int CAT_COMIDA      =7;
    public static final int CAT_POSTRES     =8;
    public static final int CAT_ALIMENTOS   =9;
    public static final int CAT_ANIMALES    =10;
    public static final int CAT_OBJETOS     =11;
    public static final int CAT_ANIMO       =12;
    public static final int CAT_LUGARES     =13;
    public static final int CAT_FAMILIA     =14;
    public static final int CAT_PROFESIONES =15;

    public static final int CAT_VOCALES=16;
    public static final int CAT_CONSONANTES=17;
    public static final int CAT_BISILABAS=18;
    public static final int CAT_TRISILABAS=19;
    public static final int CAT_POLISILABAS=20;


    //</editor-fold>

    private int id;
    private int tipo;
    public  int categoria;
    public  String nombre;
    public  int idDrawable;
    public  int idSonido,idSonido2,idSonido3;

    //<editor-fold desc="CONSTRUCTOR CON PARAMETROS">
    public Pictograma(int tipo, int categoria, String nombre, int idDrawable, int idSonido, int idSonido2, int idSonido3) {
        this.tipo = tipo;
        this.categoria = categoria;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.idSonido = idSonido;
        this.idSonido2 = idSonido2;
        this.idSonido3 = idSonido3;
    }
    //</editor-fold>

    //<editor-fold desc="MÉTODOS GETTERS">
    public int getId() {
        return id;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getIdSonido2() {
        return idSonido2;
    }

    public int getIdSonido3() {
        return idSonido3;
    }

    public int getIdSonido() {
        return idSonido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public int getCategoria() {
        return categoria;
    }
    //</editor-fold>

    //<editor-fold desc="MÉTODOS SETTERS">
    public void setId(int id) {
        this.id = id;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public void setIdSonido2(int idSonido2) {
        this.idSonido2 = idSonido2;
    }

    public void setIdSonido3(int idSonido3) {
        this.idSonido3 = idSonido3;
    }

    public void setIdSonido(int idSonido) {
        this.idSonido = idSonido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    //</editor-fold>

    //<editor-fold desc="MÉTODO toString()">
    public String toString() {
        return ("Nombre: "     +nombre+"\n" +
                "Categoria: "  +categoria+"\n"+
                "IdDrawable: " +idDrawable+"\n");
    }
    //</editor-fold>
}

