package edu.css.unit10assign;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by murray.butler on 3/29/18.
 * @version 1.0
 * Class for Trophy objects
 */
@Entity(tableName = "trophy",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = { @Index(value = "id")}
)

/**
 * Class definition for Trophy
 */
public class Trophy {
    @PrimaryKey(autoGenerate = true)
    long id;

    public long userId;

    String description;

    /**
     * Constructor for Trophy objects
     * @param userId long User ID in database
     * @param description String description of Trophy object
     */
    public Trophy(long userId, String description) {
        this.userId = userId;
        this.description = description;
    }
}
