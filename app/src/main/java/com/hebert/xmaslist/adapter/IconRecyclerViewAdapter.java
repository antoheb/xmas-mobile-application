package com.hebert.xmaslist.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hebert.xmaslist.Gift.GiftDetailsActivity;
import com.hebert.xmaslist.R;
import com.hebert.xmaslist.Wish.AddWishActivity;
import com.hebert.xmaslist.model.Gift;
import com.hebert.xmaslist.util.UtilKey;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;


public class IconRecyclerViewAdapter extends RecyclerView.Adapter<IconRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> images;
    private Context context;

    public IconRecyclerViewAdapter(Context context, ArrayList<String> images) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public IconRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_icon_view, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final IconRecyclerViewAdapter.ViewHolder holder, final int position) {

        Glide.with(context)
                .asBitmap()
                .load(images.get(position))
                .into(holder.image);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
