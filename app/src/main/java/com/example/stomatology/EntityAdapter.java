package com.example.stomatology;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.zip.Inflater;

public class EntityAdapter extends RecyclerView.Adapter<EntityAdapter.ViewHolder> implements Filterable {
    private LayoutInflater inflater;
    private List<Entity> entities;
    private List<Entity> entitiesFiltered;
    private MainActivity mainActivity;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    entitiesFiltered = entities;
                } else {
                    List<Entity> filteredList = new ArrayList<>();
                    for (Entity row : entities) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().startsWith(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    entitiesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = entitiesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                entitiesFiltered = (ArrayList<Entity>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Log.d("RRR",position+"");
                    mainActivity.loadFirst(entitiesFiltered.get(position));
                }
            });
        }
    }

    public EntityAdapter(Context c,  List<Entity> entities, MainActivity mainActivity) {
        this.entities = entities;
        this.entitiesFiltered = entities;
        this.inflater = LayoutInflater.from(c);
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entity entity = entitiesFiltered.get(position);
        holder.tv_item.setText(entity.getName());
    }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public int getItemCount() {
        return entitiesFiltered.size();
    }



    public void sort () {
        entitiesFiltered.sort(new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {
                return e1.getName().toLowerCase().compareTo(e2.getName().toLowerCase());
            }
        });
    }
}