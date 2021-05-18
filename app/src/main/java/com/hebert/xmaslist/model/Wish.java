package com.hebert.xmaslist.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Wish {
    private String id;
    private String name;
    private String icon;
    private String category;

    public Wish(String id, String name, String icon, String category) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.category = category;
    }

    public Wish() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public static DiffUtil.ItemCallback<Wish> itemCallback = new DiffUtil.ItemCallback<Wish>() {

        @Override
        public boolean areItemsTheSame(@NonNull Wish oldItem, @NonNull Wish newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Wish oldItem, @NonNull Wish newItem) {
            return false;
        }

    };
}
