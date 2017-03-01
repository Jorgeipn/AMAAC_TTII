package com.enterprises.devare.amaac_avanzaado.modelo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.enterprises.devare.amaac_avanzaado.modelo.Nivel;
import com.enterprises.devare.amaac_avanzaado.modelo.Pictograma;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String NAME_DATABASE = "amaac.sqlite";//Nombre de la Base de Datos
    private static final int DB_SHEME_VERSION = 1;//Version de la DB por si hay una nueva version y haya que actualizar la BD

    //Context context Es una referencia a la Activity
    //<editor-fold desc="CONSTRUCTOR DBHelper">
    public DBHelper(Context context) {
        super(context, NAME_DATABASE, null, DB_SHEME_VERSION);
    }
    //</editor-fold>

    //Este método se encarga de inicializar la base de datos, se ejecuta siempre cuando se crea la clase
    //<editor-fold desc="MÉTODO onCreate()">
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_PICTOGRAMA);
        db.execSQL(CREATE_TABLE_NIVEL);

    }
    //</editor-fold>

    //Método usado en el caso de que haga falta actualizar la versión de la base de datos
    //<editor-fold desc="MÉTODO onUpgrade">
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //se elimina la veersion anterior de la tabla
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PICTOGRAMA);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NIVEL);
        //se crea la nueva version de la tabla
        onCreate(db);
    }
    //</editor-fold>

    //Nombre de la tabla pictograma
    public static final String TABLE_PICTOGRAMA = "pictograma";

    //Nombre de la tabla nivel
    public static final String TABLE_NIVEL = "nivel";

    //Nombre de los campos de la tabla pictograma
    public static final String ID = "idPictograma";
    public static final String TIPO = "tipo";
    public static final String CATEGORIA = "categoria";
    public static final String NOMBRE = "nombre";
    public static final String ID_DRAWABLE = "idDrawable";
    public static final String ID_SONIDO = "idSonido";
    public static final String ID_SONIDO2 = "idSonido2";
    public static final String ID_SONIDO3 = "idSonido3";

    //Nombre de los campos de la tabla nivel
    public static final String ID_NIVEL = "idNivel";
    public static final String TIPO_VISTA = "tipo";
    public static final String NOMBRE_NIVEL = "nombre";
    public static final String PROGRESSO = "progresso";
    public static final String ID_DRAWABLE_NIVEL = "idDrawable";

    //Sentencia para crear la tabla pictograma

    public static final String CREATE_TABLE_PICTOGRAMA = "CREATE TABLE " + TABLE_PICTOGRAMA + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TIPO + " INTEGER,"
            + CATEGORIA + " INTEGER,"
            + NOMBRE + " TEXT NOT NULL,"
            + ID_DRAWABLE + " INTEGER,"
            + ID_SONIDO + " INTEGER,"
            + ID_SONIDO2 + " INTEGER,"
            + ID_SONIDO3 + " INTEGER);";

    //Sentencia para crear la tabla nivel
    public static final String CREATE_TABLE_NIVEL = "CREATE TABLE " + TABLE_NIVEL + " ("
            + ID_NIVEL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TIPO_VISTA + " INTEGER,"
            + NOMBRE_NIVEL + " TEXT NOT NULL,"
            + PROGRESSO + " INTEGER,"
            + ID_DRAWABLE_NIVEL + " INTEGER);";


    /* Agrega un nuevo pictograma a la database*/
    //<editor-fold desc="MÉTODO agregarPictograma()">
    public void insertPictograma(Pictograma picto) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIPO, picto.getTipo());
        values.put(CATEGORIA, picto.getCategoria());
        values.put(NOMBRE, picto.getNombre());
        values.put(ID_DRAWABLE, picto.getIdDrawable());
        values.put(ID_SONIDO, picto.getIdSonido());
        values.put(ID_SONIDO2, picto.getIdSonido2());
        values.put(ID_SONIDO3, picto.getIdSonido3());

        db.insert(TABLE_PICTOGRAMA, null, values); //Insert query to store the record in the database
        db.close();
    }
    //</editor-fold>

    /*Agrega un nivel a la database*/
    //<editor-fold desc="MÉTODO agregarNivel()">
    public void agregarNivel(Nivel nivel) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIPO_VISTA, nivel.getTipo());
        values.put(NOMBRE_NIVEL, nivel.getNombre());
        values.put(PROGRESSO, nivel.getProgresso());
        values.put(ID_DRAWABLE_NIVEL, nivel.getIdDrawable());

        db.insert(TABLE_NIVEL, null, values); //Insert query to store the record in the database
        db.close();
    }
    //</editor-fold>

    /* will return the list of all users*/
    //<editor-fold desc="MÉTODO getCategoria_Pictogramas() QUE RETORNA TODOS LOS PICTOGRAMAS POR SU CATEGORIA">
    public List<Pictograma> getCategoria_Pictogramas(int categoria) {

        List<Pictograma> usersList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PICTOGRAMA + " WHERE " + CATEGORIA + "=" + categoria;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pictograma pic = new Pictograma(cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));
                usersList.add(pic);
            } while (cursor.moveToNext());
        }
        return usersList;
    }
    //</editor-fold>

    /* will return he user's object if id matches*/
    //<editor-fold desc="MÉTODO getPictograma()">
    public Pictograma getPictograma(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PICTOGRAMA, new String[]{ID,
                        NOMBRE, CATEGORIA, ID_DRAWABLE}, ID + "=?",
                new String[]{String.valueOf(user_id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Pictograma pic = new Pictograma(cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));
        return pic;
    }
    //</editor-fold>

    /*getAllNivel() retorna la lista de los niveles*/
    //<editor-fold desc="MÉTODO getAllNiveles()">
    public List<Nivel> getAllNiveles() {

        List<Nivel> ListaNiveles = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NIVEL;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Nivel nivel = new Nivel(cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
                ListaNiveles.add(nivel);
            } while (cursor.moveToNext());
        }
        return ListaNiveles;
    }
    //</editor-fold>

    //<editor-fold desc="MÉTODO count() ">
    public int count() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCount = db.rawQuery("select count(*) from " + TABLE_PICTOGRAMA, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();

        return count;
    }
    //</editor-fold>

    /* will give the total number of records in the table*/
    //<editor-fold desc="MÉTODO getUsersCount()">
    public int getUsersCount() {

        String countQuery = "SELECT  * FROM " + TABLE_PICTOGRAMA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();

    }
    //</editor-fold>

    /*will be used to update the existing user record*/
    //<editor-fold desc="MÉTODO  updateUser">
    public int updateUser(Pictograma picto) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIPO, picto.getTipo());
        values.put(CATEGORIA, picto.getCategoria());
        values.put(NOMBRE, picto.getNombre());
        values.put(ID_DRAWABLE, picto.getIdDrawable());
        // updating record
        return db.update(TABLE_PICTOGRAMA, values, ID + " = ?", // update query to make changes to the existing record
                new String[]{String.valueOf(picto.getId())});
    }
    //</editor-fold>

    /*to delete the record from the table*/
    //<editor-fold desc="MÉTODO deleteContact()">
    public void deleteContact(Pictograma user) {

        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_PICTOGRAMA, ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    //</editor-fold>

    //Para cerrar la conexión de la base de datos
    //<editor-fold desc="MÉTODO close() PARA CERRAR LA BASE DE DATOS">
    public void close() {
        this.close();
    }
    //</editor-fold>

}
