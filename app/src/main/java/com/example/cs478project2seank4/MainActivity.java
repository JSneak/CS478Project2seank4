package com.example.cs478project2seank4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> mvTitleList;
    ArrayList<String> mvDirectorList;
    ArrayList<String> mvPreviewList;
    ArrayList<String> mvMovieWikiList;
    ArrayList<String> mvDirectorWikiList;
    ArrayList<Integer> mvThumbnailList;
    RecyclerView nameView;

    public void openLink(int urlPos, String typeOfLink) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        if(typeOfLink == "movie")
            i.setData(Uri.parse(mvPreviewList.get(urlPos)));
        if(typeOfLink == "director")
            i.setData(Uri.parse(mvDirectorWikiList.get(urlPos)));
        if(typeOfLink == "wiki")
            i.setData(Uri.parse(mvMovieWikiList.get(urlPos)));
        startActivity(i);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameView = (RecyclerView) findViewById(R.id.recycler_view);

        List<String> movieTitles = Arrays.asList("Parasite", "Train To Busan", "I Saw The Devil", "A Taxi Driver", "Burning", "Psychokinesis", "The Host", "Mother", "The Battleship Island", "The Admiral");
        List<String> movieDirectors = Arrays.asList("Bong Joon-ho", "Yeon Sang-ho", "Kim Jee-woon", "Jang Hoon", "Lee Chang-dong", "Yeon Sang-ho", "Bong Joon-ho", "Bong Joon-ho", "Ryoo Seung-wan", "Kim Han-min");
        List<String> moviePreviewLinks = Arrays.asList("https://www.youtube.com/watch?v=SEUXfv87Wpk", "https://www.youtube.com/watch?v=pyWuHv2-Abk", "https://www.youtube.com/watch?v=xwWgp1bqVwE", "https://www.youtube.com/watch?v=ZbUwOP9HZQk", "https://www.youtube.com/watch?v=oihHs2Errwk", "https://www.youtube.com/watch?v=Int0YR7Z67Y", "https://www.youtube.com/watch?v=1HRTy26s4hw", "https://www.youtube.com/watch?v=0oBwQHWeYxo", "https://www.youtube.com/watch?v=Jj96xMM9wRQ", "https://www.youtube.com/watch?v=7PBIHkKi0wM");
        List<Integer> movieThumbnails = Arrays.asList(R.drawable.parasite, R.drawable.traintobusan, R.drawable.sawthedevil, R.drawable.ataxidriver, R.drawable.burning, R.drawable.psychokinesis, R.drawable.thehost, R.drawable.mother, R.drawable.battleshipisland, R.drawable.theadmiral);
        List<String> movieWikiLink = Arrays.asList("https://en.wikipedia.org/wiki/Parasite_(2019_film)","https://en.wikipedia.org/wiki/Train_to_Busan","https://en.wikipedia.org/wiki/I_Saw_the_Devil", "https://en.wikipedia.org/wiki/A_Taxi_Driver","https://en.wikipedia.org/wiki/Burning_(film)","https://en.wikipedia.org/wiki/Psychokinesis_(film)","https://en.wikipedia.org/wiki/The_Host_(2006_film)","https://en.wikipedia.org/wiki/Mother_(2009_film)","https://en.wikipedia.org/wiki/The_Battleship_Island","https://en.wikipedia.org/wiki/The_Admiral:_Roaring_Currents");
        List<String> movieDirectorWikiLink = Arrays.asList("https://en.wikipedia.org/wiki/Bong_Joon-ho_filmography", "https://en.wikipedia.org/wiki/Yeon_Sang-ho", "https://en.wikipedia.org/wiki/Kim_Jee-woon", "https://en.wikipedia.org/wiki/Jang_Hoon", "https://en.wikipedia.org/wiki/Lee_Chang-dong", "https://en.wikipedia.org/wiki/Yeon_Sang-ho", "https://en.wikipedia.org/wiki/Bong_Joon-ho_filmography","https://en.wikipedia.org/wiki/Bong_Joon-ho_filmography","https://en.wikipedia.org/wiki/Ryoo_Seung-wan", "https://en.wikipedia.org/wiki/Kim_Han-min");

        /*
            TODO:   Change Names to titles, Create MovieInfo Class
                    Movie Info Class should have properties: MovieTitle, MovieDirector, moviePreviewLinks, MovieThumnbnail, MovieWikiLink, MovieDirectorWikiLink
                    Then we need to have a List<MovieInfo> listItems and somehow shove it into an ArrayList
         */

        //Define the listener with a lambda and access the name of the list item from the view
        RVClickListener onClickListener = (view,position)-> {
            Log.i("ON_CLICK", "Opening " + position + " URL");
            openLink(position, "movie");
        };

        MenuItem.OnMenuItemClickListener openVideoLink = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i("OnMenuClick", "Video GroupId: " + item.getGroupId() + ", Menu Info: " + item.getMenuInfo() + ", Item Id: " + item.getItemId());
                openLink(item.getGroupId(), "movie"); /* need to change this to the parent id */
                return true;
            }
        };

        MenuItem.OnMenuItemClickListener openMovieWikiLink = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i("OnMenuClick", "Movie GroupId: " + item.getGroupId() + ", Menu Info: " + item.getMenuInfo() + ", Item Id: " + item.getItemId());
                openLink(item.getGroupId(), "wiki"); /* need to change this to the parent id */
                return true;
            }
        };

        MenuItem.OnMenuItemClickListener openDirectorWikiLink = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i("OnMenuClick", "Director GroupId: " + item.getGroupId() + ", Menu Info: " + item.getMenuInfo() + ", Item Id: " + item.getItemId());
                openLink(item.getGroupId(), "director"); /* need to change this to the parent id */
                return true;
            }
        };





        /* New implementation */
        mvTitleList = new ArrayList<>();
        mvTitleList.addAll(movieTitles);

        mvDirectorList = new ArrayList<>();
        mvDirectorList.addAll(movieDirectors);

        mvPreviewList = new ArrayList<>();
        mvPreviewList.addAll(moviePreviewLinks);

        mvThumbnailList = new ArrayList<>();
        mvThumbnailList.addAll(movieThumbnails);

        mvMovieWikiList = new ArrayList<>();
        mvMovieWikiList.addAll(movieWikiLink);

        mvDirectorWikiList = new ArrayList<>();
        mvDirectorWikiList.addAll(movieDirectorWikiLink);

        MyAdapter adapter = new MyAdapter(mvTitleList, mvDirectorList, mvThumbnailList, onClickListener, openVideoLink, openMovieWikiLink, openDirectorWikiLink);

//        MyAdapter adapter = new MyAdapter(myList, listener);
        nameView.setHasFixedSize(true);
        nameView.setAdapter(adapter);
//        nameView.setLayoutManager(new GridLayoutManager(this,3)); //use this line to see as a grid
        nameView.setLayoutManager(new LinearLayoutManager(this)); //use this line to see as a standard vertical list


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.gridButton:
                nameView.setLayoutManager(new GridLayoutManager(this,2)); //use this line to see as a grid
                return true;
            case R.id.listButton:
                nameView.setLayoutManager(new LinearLayoutManager(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
