package com.jessejojojohnson.residentwatch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.jessejojojohnson.residentwatch.util.TypeListItem;
import com.jessejojojohnson.residentwatch.util.TypeListItemAdapter;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;


public class SelectTypeActivity extends ActionBarActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);

        recyclerView = (RecyclerView) findViewById(R.id.typeCardList);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        recyclerView.getItemAnimator().setAddDuration(2000);
        recyclerView.setHasFixedSize(true);


        //set RecyclerView's linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(SelectTypeActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);

        //populate adapter
        ArrayList<TypeListItem> listItems = new ArrayList<>();
        listItems.add(new TypeListItem("Rubbish"));
        listItems.add(new TypeListItem("Choked Gutters"));
        listItems.add(new TypeListItem("Illegal Connections"));
        listItems.add(new TypeListItem("Broken Pipes"));
        listItems.add(new TypeListItem("Potholes"));

        TypeListItemAdapter adapter = new TypeListItemAdapter(listItems);

        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_type, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
