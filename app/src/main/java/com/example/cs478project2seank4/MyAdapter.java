package com.example.cs478project2seank4;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> nameList; //data: the names displayed
    private RVClickListener RVlistener; //listener defined in main activity
    private Context context;

    /*
    passing in the data and the listener defined in the main activity
     */
    public MyAdapter(ArrayList<String> theList, RVClickListener listener){
        nameList = theList;
        this.RVlistener = listener;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.rv_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView, RVlistener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(nameList.get(position));

    }

    @Override
    public int getItemCount() {
        return nameList.size();
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
        public ImageView image;
        private RVClickListener listener;
        private View itemView;


        public ViewHolder(@NonNull View itemView, RVClickListener passedListener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_TextView);
            director = (TextView) itemView.findViewById(R.id.director_TextView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            /*
                TODO:   Change name to title, add director TextView
                        Make sure that title is bigger than director, director should be italicized
             */
            this.itemView = itemView;
            itemView.setOnCreateContextMenuListener(this); //set context menu for each list item (long click)
            this.listener = passedListener;


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

            //inflate menu from xml

            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu );
            menu.getItem(0).setOnMenuItemClickListener(onMenu);
            menu.getItem(1).setOnMenuItemClickListener(onMenu);
            menu.getItem(2).setOnMenuItemClickListener(onMenu);
            menu.getItem(3).setOnMenuItemClickListener(onMenu);

          /*  //create menu in code

            menu.setHeaderTitle("My context menu");

            //add menu items and set the listener for each
            menu.add(0,v.getId(),0,"option 1").setOnMenuItemClickListener(onMenu);
            menu.add(0,v.getId(),0,"option 2").setOnMenuItemClickListener(onMenu);
            menu.add(0,v.getId(),0,"option 3").setOnMenuItemClickListener(onMenu);
            */

        }

        /*
            listener for menu items clicked
         */
        private final MenuItem.OnMenuItemClickListener onMenu = new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                Log.i("ON_CLICK", title.getText() + " adapter pos: " + getAdapterPosition());
                return true;
            }
        };



    }
}
