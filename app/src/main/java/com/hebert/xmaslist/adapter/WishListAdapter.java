package com.hebert.xmaslist.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hebert.xmaslist.R;
import com.hebert.xmaslist.Wish.WishDetailsActivity;
import com.hebert.xmaslist.model.Wish;
import com.hebert.xmaslist.util.UtilKey;

public class WishListAdapter extends ListAdapter<Wish, WishListAdapter.WishViewHolder> {

    Context context;

    public WishListAdapter(@NonNull Context context, DiffUtil.ItemCallback<Wish> diffCallback) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public WishListAdapter.WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wish_view, parent, false);
        return new WishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishViewHolder holder, int position) {
        Wish wish = getItem(position);
        holder.bind(wish);
    }

    class WishViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        RelativeLayout parentLayout;

        public WishViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_icon);
            name = itemView.findViewById(R.id.wish_name);
            parentLayout = itemView.findViewById(R.id.parent_layout_wish);

            parentLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    Wish wish = (Wish) getItem(position);
                    Gson gson = new Gson();
                    String wishAsString = gson.toJson(wish);

                    Intent intent = new Intent(context, WishDetailsActivity.class);
                    intent.putExtra("wishAsString", wishAsString);
                    intent.putExtra("position", position);
                    ((Activity)context).startActivityForResult(intent, UtilKey.REQUEST_CODE_DETAILS);
                }
            });
        }

        public void bind(Wish wish) {

            name.setText(wish.getName());
            Glide.with(context)
                    .asBitmap()
                    .load(wish.getIcon())
                    .into(image);
        }
    }
}


