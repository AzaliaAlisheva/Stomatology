package com.example.stomatology;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class EntityAdapter extends RecyclerView.Adapter<EntityAdapter.EntityHolder>{
    private List<Entity> entities = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public EntityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new EntityHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EntityHolder holder, int position) {
        Entity currentEntity = entities.get(position);
        holder.textViewName.setText(currentEntity.getName());
//        holder.textViewPhone.setText(currentEntity.getPhone());
//        holder.textViewAge.setText(currentEntity.getAge());
//        holder.textViewAddress.setText(currentEntity.getAddress());
//        holder.textViewDiagnostics.setText(currentEntity.getDiagnostics());
//        holder.textViewDate.setText(currentEntity.getDate());
//        holder.textViewTime.setText(currentEntity.getTime());
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    public Entity getEntityAt(int position) {
        return entities.get(position);
    }

    class EntityHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
//        private TextView textViewPhone;
//        private TextView textViewAge;
//        private TextView textViewAddress;
//        private TextView textViewDiagnostics;
//        private TextView textViewDate;
//        private TextView textViewTime;

        public EntityHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(entities.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Entity entity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
