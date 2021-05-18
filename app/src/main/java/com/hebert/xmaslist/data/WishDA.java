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
import com.hebert.xmaslist.model.Wish;
import java.util.ArrayList;

public class WishDA {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference wishRef = db.collection("Wish");

    public void getWish(String category, final WishDACallback callback) {
        final ArrayList<Wish> wishes = new ArrayList<>();

        wishRef.whereEqualTo("category", category).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null) {
                            for(Wish wish: task.getResult().toObjects(Wish.class)) {
                                wishes.add(wish);
                            }
                            callback.getWish(wishes);
                        }
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {

                    }
                });
    }

    public void addNewWish(final Wish wish) {
        wishRef.add(wish)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        wish.setId(documentReference.getId());
                        documentReference.set(wish);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void updateWish(Wish wish) {
        wishRef.document(wish.getId()).set(wish);
    }

    public void deleteWish(Wish wish) {
        wishRef.document(wish.getId()).delete();
    }

}
