package com.corsoandroid.giustomangia.services;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.corsoandroid.giustomangia.dao.OrderDao;
import com.corsoandroid.giustomangia.datamodels.Ordine;

/**
 * Created by Andrea on 25/02/2019.
 */

@Database(entities = {Ordine.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase INSTANCE;;
    public static final String AppDatabase = "databaseRoom";
    public abstract OrderDao orderDao();

    public static AppDatabase getAppDatabase(Context context) {
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context,AppDatabase.class,AppDatabase).build();
        }

        return INSTANCE;
    }

    public void destroyInstance() {
        INSTANCE = null;
    }
}
