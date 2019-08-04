package com.appdot.io.travelmantics_app;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<TravelDeals> mDeals;

    private FirebaseUtil(){ };

    public static void openFileReference(String ref){
        if(firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDeals = new ArrayList<>();
        }

         mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }
}
