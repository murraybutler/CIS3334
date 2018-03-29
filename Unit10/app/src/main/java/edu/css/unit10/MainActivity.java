package edu.css.unit10;

/**
 * Created by murray.butler on 3/27/18.
 * Main activity class for app
 * @version 1.0
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;


public class MainActivity extends ListActivity {
    private CommentsDataSource datasource;

    /**
     * onCreate method for start of app
     * @param savedInstanceState Bundle for reload of previous state from instantiation
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new CommentsDataSource(this);
        datasource.open();

        List<Comment> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    /**
     * onClick method to handle click events in the app
     * @param view Current View for events and drawing
     */
    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment = null;
        switch (view.getId()) {
            //Add comment button is clicked
            case R.id.add:
                String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                comment = datasource.createComment(comments[nextInt]);
                adapter.add(comment);
                break;
            //Delete comment button is clicked
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    comment = (Comment) getListAdapter().getItem(0);
                    datasource.deleteComment(comment);
                    adapter.remove(comment);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * Method to reopen data source on app resume
     */
    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    /**
     * Method to gracefully close datasource on app exit/pause
     */
    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}