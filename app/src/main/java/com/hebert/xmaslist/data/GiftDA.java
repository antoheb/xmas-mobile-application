package com.hebert.xmaslist.data;

import android.util.Log;

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
import java.util.ArrayList;


public class GiftDA {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference giftRef = db.collection("Gift");
    private static final String TAG = "GiftDA";

    public void getGift(String id, final GiftDACallback callback) {
        final ArrayList<Gift> gifts = new ArrayList<>();

        giftRef.whereEqualTo("listId", id).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null) {
                            for(Gift gift: task.getResult().toObjects(Gift.class)) {
                                gifts.add(gift);
                            }
                            callback.getGift(gifts);
                        }
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {

                    }
                });
    }

    public void addNewGift(final Gift gift) {
        giftRef.add(gift)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        gift.setId(documentReference.getId());
                        documentReference.set(gift);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }


    public void updateGift(Gift gift) {
        giftRef.document(gift.getId()).set(gift);
    }

    public void deleteGift(Gift gift) {
        giftRef.document(gift.getId()).delete();
    }
}
