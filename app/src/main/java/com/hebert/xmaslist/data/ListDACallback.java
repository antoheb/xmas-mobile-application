package com.hebert.xmaslist.data;

import com.hebert.xmaslist.model.Gift;
import com.hebert.xmaslist.model.GiftList;

import java.util.List;

public interface ListDACallback {

    void getList(List<GiftList> list);
}
