package com.hebert.xmaslist.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.hebert.xmaslist.model.Gift;
import java.util.ArrayList;
import java.util.List;

public class GiftViewModel extends ViewModel {

    private MutableLiveData<List<Gift>> mutableLiveData;

    public LiveData<List<Gift>> getGiftList() {

        if(mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();

        return this.mutableLiveData;
    }

    public void initGiftList(List<Gift> list) {
        if(mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();

        mutableLiveData.setValue(list);
    }

    public void addGift(Gift gift) {
        if(mutableLiveData.getValue() != null) {
            List<Gift> giftList = new ArrayList<>(mutableLiveData.getValue());
            giftList.add(gift);
            mutableLiveData.setValue(giftList);
        }
    }

    public void deleteGift(int position) {
        if(mutableLiveData.getValue() != null) {
            List<Gift> giftList = new ArrayList<>(mutableLiveData.getValue());
            giftList.remove(position);
            mutableLiveData.setValue(giftList);
        }
    }

    public void updateGift(Gift gift, int position) {
        if(mutableLiveData.getValue() != null) {
            List<Gift> giftList = new ArrayList<>(mutableLiveData.getValue());
            giftList.remove(position);
            giftList.add(position, gift);
            mutableLiveData.setValue(giftList);
        }
    }
}
