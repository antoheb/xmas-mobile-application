package com.hebert.xmaslist.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hebert.xmaslist.model.Wish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WishViewModel extends ViewModel {

    private MutableLiveData<List<Wish>> mutableLiveData;

    public LiveData<List<Wish>> getWishList() {

        if(mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();

        return this.mutableLiveData;
    }

    public void initWishList(List<Wish> list) {
        if(mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();

        mutableLiveData.setValue(list);
    }

    public void addWish(Wish wish) {
        if(mutableLiveData.getValue() != null) {
            List<Wish> wishList = new ArrayList<>(mutableLiveData.getValue());
            wishList.add(wish);
            mutableLiveData.setValue(wishList);
        }
    }

    public void deleteWish(int position) {
        if(mutableLiveData.getValue() != null) {
            List<Wish> wishList = new ArrayList<>(mutableLiveData.getValue());
            wishList.remove(position);
            mutableLiveData.setValue(wishList);
        }
    }

    public void updateWish(Wish wish, int position) {
        if(mutableLiveData.getValue() != null) {
            List<Wish> wishList = new ArrayList<>(mutableLiveData.getValue());
            wishList.remove(position);
            wishList.add(position, wish);
            mutableLiveData.setValue(wishList);
        }
    }



}
