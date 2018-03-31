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
 * Data Access Object for User class
 */
@Dao
public interface UserDao {
    /**
     * Insert method
     * @param user User object to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(User user);

    /**
     * Full table list of users
     * @return List of all users in database
     */
    @Query("select * from user")
    public List<User> getAllUser();

    /**
     * Return single user
     * @param userId long ID of user in table
     * @return List of user
     */
    @Query("select * from user where id = :userId")
    public List<User> getUser(long userId);

    /**
     * Update method
     * @param user User to be updated
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User user);

    /**
     * Delete method for user record
     */
    @Query("delete from user")
    void removeAllUsers();
}
