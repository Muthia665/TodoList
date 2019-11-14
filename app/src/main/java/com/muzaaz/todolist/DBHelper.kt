package com.muzaaz.todolist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(context: Context) : ManagedSQLiteOpenHelper(context, "todo.db", null, 1) {

    companion object {
        private var instant: DBHelper? = null
        @Synchronized
        fun getInstance(context: Context): DBHelper {
            if (instant == null) {
                instant = DBHelper(context.applicationContext)
            }
            return instant as DBHelper
        }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.createTable(
            Model.TABLE_NAME, true, Model.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Model.NAMA to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(Model.TABLE_NAME, true)
    }
}
    val Context.database : DBHelper
    get() = DBHelper.getInstance(applicationContext)
