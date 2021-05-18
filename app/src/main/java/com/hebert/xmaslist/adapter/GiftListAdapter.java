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
import com.hebert.xmaslist.Gift.GiftDetailsActivity;
import com.hebert.xmaslist.R;
import com.hebert.xmaslist.model.Gift;
import com.hebert.xmaslist.util.UtilKey;

public class GiftListAdapter extends ListAdapter<Gift, GiftListAdapter.GiftViewHolder> {

    Context context;

    public GiftListAdapter(@NonNull Context context, DiffUtil.ItemCallback<Gift> diffCallback) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public GiftListAdapter.GiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_gift_view, parent, false);
        return new GiftListAdapter.GiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiftListAdapter.GiftViewHolder holder, int position) {
        Gift gift = getItem(position);
        holder.bind(gift);
    }

    class GiftViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView price;
        TextView personAssign;
        RelativeLayout parentLayout;

        public GiftViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_icon);
            name = itemView.findViewById(R.id.gift_name);
            price = itemView.findViewById(R.id.product_cost);
            personAssign = itemView.findViewById(R.id.person_assign_holder);
            parentLayout = itemView.findViewById(R.id.parent_layout_gift);

            parentLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    Gift gift = (Gift) getItem(position);
                    Gson gson = new Gson();
                    String giftAsString = gson.toJson(gift);

                    Intent intent = new Intent(context, GiftDetailsActivity.class);
                    intent.putExtra("giftAsString", giftAsString);
                    intent.putExtra("position", position);
                    ((Activity)context).startActivityForResult(intent, UtilKey.REQUEST_CODE_DETAILS);
                }
            });
        }

        public void bind(Gift gift) {

            name.setText(gift.getName() + " - ");
            price.setText(gift.getCost().toString());
            personAssign.setText(gift.getPersonAssign());

            Glide.with(context)
                    .asBitmap()
                    .load(gift.getIcon())
                    .into(image);
        }
    }
}
