package de.chrisward.theyworkforyou.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import de.chrisward.theyworkforyou.model.Lord
import de.chrisward.theyworkforyou.model.MP

@Database(entities = [MP::class, Lord::class], version = 1)
abstract class TheyWorkForYouDatabase : RoomDatabase() {
    abstract fun mpStore(): MPStore
    abstract fun lordStore(): LordStore

    companion object {

        private val DB_NAME = "theyworkforyou.db"
        @Volatile
        private var dbInstance: TheyWorkForYouDatabase? = null

        @Synchronized
        operator fun get(context: Context): TheyWorkForYouDatabase? {
            if (dbInstance == null) {
                val builder: RoomDatabase.Builder<TheyWorkForYouDatabase>
                builder = Room.databaseBuilder(context.applicationContext, TheyWorkForYouDatabase::class.java, DB_NAME)
                dbInstance = builder.addMigrations(Migrations.FROM_0_TO_1).build()
            }

            return dbInstance
        }
    }
}
