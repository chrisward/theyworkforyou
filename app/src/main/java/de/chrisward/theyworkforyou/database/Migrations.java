package de.chrisward.theyworkforyou.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

class Migrations {
    static final Migration FROM_0_TO_1 = new Migration(0, 1) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `mps` (`member_id` INTEGER, `person_id` INTEGER, `name` TEXT, `party` TEXT, `constituency` TEXT, PRIMARY KEY (`member_id`))");
        }
    };
}
