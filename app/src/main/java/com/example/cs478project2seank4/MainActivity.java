package com.example.cs478project2seank4;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView nameView = (RecyclerView) findViewById(R.id.recycler_view);

        List<String> names = Arrays.asList("mark", "ugo", "tammy", "syed", "siham", "carlos", "ayna",
                "cozmo", "regis", "regina", "hector", "aziz", "tasha", "nicole");

        myList = new ArrayList<>();
        myList.addAll(names);

        //Define the listener with a lambda and access the list of names with the position passed in
        //  RVClickListener listener = (view, position)-> Toast.makeText(this, "position: "+position, Toast.LENGTH_LONG).show();

        //Define the listener with a lambda and access the name of the list item from the view
        RVClickListener listener = (view,position)->{
            TextView name = (TextView)view.findViewById(R.id.title_TextView);
            Toast.makeText(this,name.getText(),Toast.LENGTH_SHORT).show();
        };

        MyAdapter adapter = new MyAdapter(myList, listener);
        nameView.setHasFixedSize(true);
        nameView.setAdapter(adapter);
//        nameView.setLayoutManager(new GridLayoutManager(this,3)); //use this line to see as a grid
        nameView.setLayoutManager(new LinearLayoutManager(this)); //use this line to see as a standard vertical list


    }


}
