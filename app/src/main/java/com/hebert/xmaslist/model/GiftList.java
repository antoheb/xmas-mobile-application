package com.hebert.xmaslist.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class GiftList {

    private String name;
    private String id;

    public GiftList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
