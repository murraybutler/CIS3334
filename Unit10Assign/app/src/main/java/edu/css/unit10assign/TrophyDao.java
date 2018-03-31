package edu.css.unit10assign;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by murray.butler on 3/29/18.
 * @version 1.0
 * Data Access Object for Trophy class
 */
@Dao
public interface TrophyDao {
    /**
     * Insert method
     * @param trophy Trophy to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTrophy(Trophy trophy);

    /**
     * Query for trophies per user
     * @param userId User ID number for trophy value
     * @return List of user
     */
    @Query("SELECT * FROM trophy WHERE userId=:userId")
    List<Trophy> findTrophiesForUser(int userId);

    /**
     * Update method
     * @param trophy Trophy object for update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTrophy(Trophy trophy);

    /**
     * Delete method
     * @param id UserID to be deleted
     */
    @Query("delete from trophy where id = :id")
    void delete(long id);
}
