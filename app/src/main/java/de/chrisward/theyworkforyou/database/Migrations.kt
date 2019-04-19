package de.chrisward.theyworkforyou.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

internal object Migrations {
    val FROM_0_TO_1: Migration = object : Migration(0, 1) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `mps` (`member_id` INTEGER, `person_id` INTEGER, `name` TEXT, `party` TEXT, `constituency` TEXT, PRIMARY KEY (`member_id`))")
            database.execSQL("CREATE TABLE IF NOT EXISTS `lords` (`member_id` INTEGER, `person_id` INTEGER, `name` TEXT, `party` TEXT, PRIMARY KEY (`member_id`))")
        }
    }
}
