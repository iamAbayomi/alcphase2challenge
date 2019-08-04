package com.appdot.io.travelmantics_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {
    ArrayList<TravelDeals> deals;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;

    public DealAdapter(){
        FirebaseUtil.openFileReference("traveldeals");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        deals = FirebaseUtil.mDeals;

        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //    TextView tvDeals = findViewById(R.id.tvDeals);
                TravelDeals td = dataSnapshot.getValue(TravelDeals.class);
                td.setId(dataSnapshot.getKey());
                deals.add(td);
                notifyItemInserted(deals.size() -1);
                //  tvDeals.setText(tvDeals.getText() + "\n" + td.getTitle());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);

    }

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.iv_row, parent, false);
        return new DealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {
        TravelDeals deal = deals.get(position);
        holder.bind(deal);
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public class DealViewHolder extends RecyclerView.ViewHolder{
            TextView tvTitle;
            TextView tvDescription;
            TextView tvPrice;
            ImageView imageDeal;

            public DealViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvDescription = itemView.findViewById(R.id.tvDescription);
                tvPrice = itemView.findViewById(R.id.tvPrice);
                imageDeal = itemView.findViewById(R.id.imageDeal);

            }

            public void bind(TravelDeals deals){
                tvTitle.setText(deals.getTitle());
                tvDescription.setText(deals.getDescription());
                tvPrice.setText(deals.getPrice());
            }
        }


}
