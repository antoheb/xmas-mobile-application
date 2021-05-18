package com.hebert.xmaslist.data;


import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hebert.xmaslist.model.User;


public class UserDA {

    //Connection to firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference firstUserRef = db.collection("User").document("First User");


    public void createUser(User user) {
        firstUserRef
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }

    public void retrieveUser(final UserDACallback callback) {

        firstUserRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            callback.getUserCallback(task.getResult().toObject(User.class));
                        }
                    }
                });
    }
}
