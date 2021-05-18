package com.hebert.xmaslist.data;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.hebert.xmaslist.model.Gift;
import com.hebert.xmaslist.model.GiftList;

import java.util.ArrayList;
import java.util.List;

public class ListDA {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference listRef = db.collection("List");

    public void getList(final ListDACallback callback) {
        final ArrayList<GiftList> list = new ArrayList<>();

        listRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(GiftList giftList: task.getResult().toObjects(GiftList.class)) {
                            list.add(giftList);
                        }
                        callback.getList(list);
                    }
                });
    }

    public void addNewList(final GiftList giftList) {
        listRef.add(giftList)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        giftList.setId(documentReference.getId());
                        documentReference.set(giftList);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}
