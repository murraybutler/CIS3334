package edu.css.unit10;

/**
 * Created by murray.butler on 3/27/18.
 * A model to handle data getters and setters for database
 * @version 1.0
 */

public class Comment {
    private long id;
    private String comment;

    /**
     * Constructor for Comment class
     * @param id long index of comment
     * @param comment String comment value
     */
    public Comment(long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    /**
     * Returns id of comment
     * @return id comment id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets value of id
     * @param id comment id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets comment string
     * @return String of comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment string
     * @param comment String comment value
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * toString override method for the class
     * @return String value of comment
     */
    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }
}

