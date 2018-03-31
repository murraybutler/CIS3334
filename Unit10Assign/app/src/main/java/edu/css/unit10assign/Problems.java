package edu.css.unit10assign;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by murray.butler on 3/31/18.
 * @version 1.0
 * A class for problems created by users.
 */
@Entity
public class Problems {
    @PrimaryKey
    public final int nums;
    public String desc;
    public long id;

    /**
     * Constructor for Problems class
     * @param nums int Problem identifier (separate from UserID)
     * @param desc String description of Problem
     * @param id UserID associated with problem, NOT primary key
     */
    public Problems(int nums, String desc, long id) {
        this.nums = nums;
        this.desc = desc;
        this.id = id;
    }
}
