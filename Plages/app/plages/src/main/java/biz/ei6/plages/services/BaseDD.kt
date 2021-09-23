package biz.ei6.plages.services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import biz.ei6.plages.Plage


class DBHelper(context : Context, name : String, factory : SQLiteDatabase.CursorFactory?, version : Int)
    : SQLiteOpenHelper(context, name, factory,version) {

    override fun onCreate(bd: SQLiteDatabase) {
       bd.execSQL(CREATE_BDD)
    }

    override fun onUpgrade(bd: SQLiteDatabase, version1: Int, version2: Int) {
        bd.execSQL("DROP TABLE $TABLE")
        onCreate(bd)
    }

    companion object {
        val TABLE = "plages"
        val COL_ID = "_id"

        val COL_NOM="nom"
        val COL_DESCRIPTION="decription"
        val COL_IMAGE="image"
        val COL_URL="url"
        val COL_LARGE = "largeur"
        val COL_LONG="longueur"
        val COL_LATITUDE="latitude"
        val COL_LONGITUDE="longitude"

        val CREATE_BDD = "CREATE TABLE $TABLE ( $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_NOM TEXT NOT NULL, $COL_DESCRIPTION TEXT NOT NULL, $COL_IMAGE TEXT NOT NULL, $COL_URL TEXT NOT NULL, " +
                "$COL_LARGE INTEGER NOT NULL, $COL_LONG INTEGER NOT NULL, " +
                "$COL_LATITUDE DOUBLE NOT NULL, $COL_LONGITUDE DOUBLE NOT NULL);"
    }
}

class BaseService( contexte : Context) {

    var helper: DBHelper

    init {
        helper = DBHelper(contexte, "plages", null, 1)
    }

    fun insert(plage: Plage) {
        val data = ContentValues()
        data.put(DBHelper.COL_NOM, plage.nom)
        data.put(DBHelper.COL_DESCRIPTION, plage.description)
        data.put(DBHelper.COL_IMAGE, plage.image)
        data.put(DBHelper.COL_URL, plage.url)
        data.put(DBHelper.COL_LARGE, plage.largeur)
        data.put(DBHelper.COL_LONG, plage.longueur)
        data.put(DBHelper.COL_LATITUDE, plage.latitude)
        data.put(DBHelper.COL_LONGITUDE, plage.longitude)

        helper.writableDatabase.insert(DBHelper.TABLE, null, data)
    }

    fun query(): MutableList<Plage> {
        val ret = mutableListOf<Plage>()
        val curseur = helper.readableDatabase.query(
            DBHelper.TABLE,
            arrayOf(
                DBHelper.COL_NOM, DBHelper.COL_DESCRIPTION, DBHelper.COL_IMAGE, DBHelper.COL_URL,
                DBHelper.COL_LARGE, DBHelper.COL_LONG, DBHelper.COL_LATITUDE, DBHelper.COL_LONGITUDE
            ),
            null, null, null, null, DBHelper.COL_NOM
        )
        while (curseur.moveToNext()) {
            ret.add(
                Plage(
                    curseur.getString(curseur.getColumnIndex(DBHelper.COL_NOM)),
                    curseur.getString(curseur.getColumnIndex(DBHelper.COL_DESCRIPTION)),
                    curseur.getString(curseur.getColumnIndex(DBHelper.COL_IMAGE)),
                    curseur.getString(curseur.getColumnIndex(DBHelper.COL_URL)),
                    curseur.getInt(curseur.getColumnIndex(DBHelper.COL_LARGE)),
                    curseur.getInt(curseur.getColumnIndex(DBHelper.COL_LONG)),
                    curseur.getDouble(curseur.getColumnIndex(DBHelper.COL_LATITUDE)),
                    curseur.getDouble(curseur.getColumnIndex(DBHelper.COL_LONGITUDE))
                )
            )
        }

        return ret
    }
}
