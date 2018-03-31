package edu.css.unit10assign;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by murray.butler on 3/29/18.
 * @version 1.0
 * A class for User objects
 */
@Entity
public class User {
    @PrimaryKey
    public final int id;
    public String name;
    public int level;
    public long skillPoints;

    /**
     * Constructor for User objects
     * @param id int Individual user id number
     * @param name String User proper name
     * @param skillPoints long point value for User
     */
    public User(int id, String name, long skillPoints) {
        this.id = id;
        this.name = name;
        this.skillPoints  = skillPoints;
        this.level = 0;
    }
}
