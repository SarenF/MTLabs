package com.example.saren.lab2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHandler(ctx: Context): ManagedSQLiteOpenHelper(ctx,"labDB") {
    companion object {
        private var instance: DBHandler? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHandler {
            if (instance == null) {
                instance = DBHandler(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("Translations",true,
            "_id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            "Russian" to TEXT,
            "English" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}

val Context.database: DBHandler
    get() = DBHandler.getInstance(applicationContext)