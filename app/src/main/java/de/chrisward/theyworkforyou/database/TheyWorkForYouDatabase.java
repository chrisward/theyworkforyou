package de.chrisward.theyworkforyou.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import de.chrisward.theyworkforyou.model.Lord;
import de.chrisward.theyworkforyou.model.MP;

@Database(
        entities = {MP.class, Lord.class},
        version = 1
)
public abstract class TheyWorkForYouDatabase extends RoomDatabase {
    public abstract MPStore mpStore();
    public abstract LordStore lordStore();

    private static final String DB_NAME = "theyworkforyou.db";
    private static volatile TheyWorkForYouDatabase dbInstance = null;

    public synchronized static TheyWorkForYouDatabase get(Context context) {
        if (dbInstance == null) {
            RoomDatabase.Builder<TheyWorkForYouDatabase> builder;
            builder = Room.databaseBuilder(context.getApplicationContext(), TheyWorkForYouDatabase.class, DB_NAME);
            dbInstance = builder.addMigrations(Migrations.FROM_0_TO_1).build();
        }

        return (dbInstance);
    }
}
