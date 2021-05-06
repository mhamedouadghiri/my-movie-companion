package com.mhamed.mymoviecompanion.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mhamed.mymoviecompanion.databinding.ItemCastBinding;
import com.mhamed.mymoviecompanion.model.Cast;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    private final List<Cast> data;

    public CastAdapter(List<Cast> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new CastViewHolder(ItemCastBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        holder.bindTo(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class CastViewHolder extends RecyclerView.ViewHolder {
        private final ItemCastBinding binding;

        public CastViewHolder(@NonNull ItemCastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(Cast cast) {
            binding.setCast(cast);
        }
    }
}
