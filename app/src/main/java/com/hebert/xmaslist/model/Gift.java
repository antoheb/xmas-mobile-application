package com.hebert.xmaslist.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Gift {
    private String id;
    private String name;
    private String icon;
    private String personAssign;
    private String store;
    private Double cost;
    private String listId;

    public Gift(String id, String name, String icon, String personAssign, String store, Double cost, String listId) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.personAssign = personAssign;
        this.store = store;
        this.cost = cost;
        this.listId = listId;
    }

    public Gift() {
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

    public String getPersonAssign() {
        return personAssign;
    }

    public void setPersonAssign(String personAssign) {
        this.personAssign = personAssign;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public static DiffUtil.ItemCallback<Gift> itemCallback = new DiffUtil.ItemCallback<Gift>() {

        @Override
        public boolean areItemsTheSame(@NonNull Gift oldItem, @NonNull Gift newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Gift oldItem, @NonNull Gift newItem) {
            return false;
        }
    };
}
