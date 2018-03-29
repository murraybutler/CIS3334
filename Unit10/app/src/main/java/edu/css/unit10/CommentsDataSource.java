package edu.css.unit10;

/**
 * Created by murray.butler on 3/27/18.
 * DAO for Comments
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

class CommentsDataSource {
    //declarations
    private SQLiteDatabase database;
    private final MySQLiteHelper dbHelper;
    private final String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    /**
     * Constructor method
     * @param context current context of app
     */
    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    /**
     * Open database handle
     * @throws SQLException Exception on failure to open
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Close database handle
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * Create comment entity in database
     * @param comment String comment
     * @return pointer to new comment
     */
    public Comment createComment(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        //Insert into database followed by query of same data to set cursor to correct index in column
        //SQL Params:
        //TABLE_COMMENTS Table in current database for Comment object values
        //null
        //converted Comment values for insert
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        //SQL Params:
        //TABLE_COMMENTS Table in current database for Comment object values
        //allColumns All columns in current table
        //COLUMN_ID index in current column
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        //Move cursor to first index in column, create new Comment and move cursor to that index
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    /**
     * Delete comment entity in database
     * @param comment Comment object to be deleted
     */
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        //delete comment at comment id
        //SQL Params:
        //TABLE_COMMENTS Table in current database for Comment object values
        //COLUMN_ID column index for comment
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    /**
     * List all comments in current database
     * @return List of Comment objects
     */
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();
        //set cursor for all comlumns in database
        //SQL Params:
        //TABLE_COMMENTS Table in current database for Comment object values
        //allColumns All columns in current table
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);
        //move cursor to first index in column
        cursor.moveToFirst();
        //loop through comments in database, adding to List, moving cursor to next index
        while (cursor.moveToNext()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    /**
     * Move pointer to new Comment
     * @param cursor pointer to result set
     * @return Comment set to top of queue
     */
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment(0, null);
        //use cursor values to set the id and String value of new comment
        //cursor resides at one index ahead of current data, so we use those values
        comment.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));
        comment.setComment(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_COMMENT)));
        return comment;
    }
}
