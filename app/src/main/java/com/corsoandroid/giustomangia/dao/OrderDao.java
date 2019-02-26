package com.corsoandroid.giustomangia.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.corsoandroid.giustomangia.datamodels.Ordine;

import java.util.List;

/**
 * Created by Andrea on 25/02/2019.
 */

@Dao
public interface OrderDao {

    @Query("SELECT * FROM 'ordine'")
    List<Ordine> getAll();

    @Insert()
    void insert(Ordine ordine);

    @Delete
    void delete(Ordine ordine);

    @Query("SELECT * FROM 'ordine' WHERE id=(SELECT max(id) FROM 'ordine')")
    Ordine lastOrdine();
}
