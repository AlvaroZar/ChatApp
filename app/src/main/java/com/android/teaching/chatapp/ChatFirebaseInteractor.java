package com.android.teaching.chatapp;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFirebaseInteractor {

    private ArrayList<Bandas> bandas = new ArrayList<>();

    public void getBandas(final ChatInteractorCallback callback){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("games");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bandas = new ArrayList<>();
                for (DataSnapshot nodoBandas : dataSnapshot.getChildren()){
                    Bandas model = nodoBandas.getValue(Bandas.class);
                    bandas.add(model);
                }
                callback.onChatAvailable();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Bandas> getBandas() {

        return bandas;
    }
    public Bandas getBandasWithId(int id){
        for (Bandas bandas:bandas){
            if (bandas.getId() == id){
                return bandas;
            }
        }
        return null;
    }

}
