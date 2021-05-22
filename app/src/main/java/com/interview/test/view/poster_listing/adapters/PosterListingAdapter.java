package com.interview.test.view.poster_listing.adapters;

import android.graphics.Color;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.interview.test.databinding.ListItemPosterBinding;
import com.interview.test.model.Poster;

import java.util.List;

public class PosterListingAdapter extends RecyclerView.Adapter <PosterListingAdapter.PosterViewHolder> {

    private List<Poster> posterList;
    private String searchText;

    public PosterListingAdapter(List<Poster> posterList) {
        this.posterList = posterList;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemPosterBinding binding = ListItemPosterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PosterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.binding.setPoster(posterList.get(position));
        holder.binding.setSearchText(searchText);
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    class PosterViewHolder extends RecyclerView.ViewHolder {
        ListItemPosterBinding binding;
        public PosterViewHolder(@NonNull ListItemPosterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
