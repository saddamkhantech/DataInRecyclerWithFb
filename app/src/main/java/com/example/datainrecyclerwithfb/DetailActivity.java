package com.example.datainrecyclerwithfb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    private TextView detailTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailTextView=findViewById(R.id.detailTextViewId);
        String uid=getIntent().getStringExtra(MyDataAdapter.USER_KEY);
        //we get a key
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PojoClass pojoClass=dataSnapshot.getValue(PojoClass.class);
                detailTextView.setText(pojoClass.getName()+"  "+pojoClass.getRoll());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
