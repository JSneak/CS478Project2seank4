package com.example.cs478project2seank4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//    private ArrayList<String> nameList; //data: the names displayed
    private RVClickListener RVlistener; //listener defined in main activity
    private MenuItem.OnMenuItemClickListener OpenVideoListener;
    private MenuItem.OnMenuItemClickListener OpenMovieWikiListener;
    private MenuItem.OnMenuItemClickListener OpenDirectorWikiListener;
    private Context context;

    private ArrayList<String> movieTitles;
    private ArrayList<String> movieDirectors;
    private ArrayList<Integer> movieThumbnails;


    /* passing in the data and the listener defined in the main activity */
    public MyAdapter(ArrayList<String> mtList, ArrayList<String> mdList, ArrayList<Integer> mthmbnailList, RVClickListener listener, MenuItem.OnMenuItemClickListener menuListener1, MenuItem.OnMenuItemClickListener menuListener2, MenuItem.OnMenuItemClickListener menuListener3) {
        movieTitles = mtList;
        movieDirectors = mdList;
        movieThumbnails = mthmbnailList;

        this.RVlistener = listener;
        this.OpenVideoListener = menuListener1;
        this.OpenMovieWikiListener = menuListener2;
        this.OpenDirectorWikiListener = menuListener3;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.rv_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView, RVlistener, OpenVideoListener, OpenMovieWikiListener, OpenDirectorWikiListener);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(movieTitles.get(position));
        holder.director.setText(movieDirectors.get(position));
        holder.thumbnail.setImageResource(movieThumbnails.get(position));
        holder.pos = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return movieTitles.size();
    }




    /*
        This class creates a wrapper object around a view that contains the layout for
         an individual item in the list. It also implements the onClickListener so each ViewHolder in the list is clickable.
        It's onclick method will call the onClick method of the RVClickListener defined in
        the main activity.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

        public TextView title;
        public TextView director;
        public ImageView thumbnail;
        public int pos;
        private RVClickListener listener;
        private MenuItem.OnMenuItemClickListener openVListener;
        private MenuItem.OnMenuItemClickListener openWListener;
        private MenuItem.OnMenuItemClickListener openDListener;
        private View itemView;


        public ViewHolder(@NonNull View itemView, RVClickListener passedListener, MenuItem.OnMenuItemClickListener menuListener1, MenuItem.OnMenuItemClickListener menuListener2, MenuItem.OnMenuItemClickListener menuListener3) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_TextView);
            director = (TextView) itemView.findViewById(R.id.director_TextView);
            thumbnail = (ImageView) itemView.findViewById(R.id.mv_imageView);

            /* Need to figure out how to set a menu item for the fucking parent */


            this.itemView = itemView;
            itemView.setOnCreateContextMenuListener(this); //set context menu for each list item (long click)
            this.listener = passedListener;
            this.openVListener = menuListener1;
            this.openWListener = menuListener2;
            this.openDListener = menuListener3;

            /*
                don't forget to set the listener defined here to the view (list item) that was
                passed in to the constructor.
             */
            itemView.setOnClickListener(this); //set short click listener
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
            Log.i("ON_CLICK", "in the onclick in view holder");
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            //menu.setHeaderTitle("My context menu");

            //add menu items and set the listener for each
            Log.i("Create Menu", "Get Id: " + v.getId());
            menu.add(pos, v.getId(), 0, "View video clip").setOnMenuItemClickListener(openVListener);
            menu.add(pos, v.getId(), 1, "View wiki page").setOnMenuItemClickListener(openWListener);
            menu.add(pos, v.getId(), 2, "View director page").setOnMenuItemClickListener(openDListener);
        }
    }
}
