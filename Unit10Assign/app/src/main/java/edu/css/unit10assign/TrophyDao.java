package edu.css.unit10assign;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by murray.butler on 3/29/18.
 */
@Dao
public class TrophyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTrophy(Trophy trophy);

    @Query("SELECT * FROM trophy WHERE userId=:userId")
    List<Trophy> findTrophiesForUser(int userId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTrophy(Trophy trophy);

    @Query("delete from trophy where id = :id")
    void delete(long id);
}
