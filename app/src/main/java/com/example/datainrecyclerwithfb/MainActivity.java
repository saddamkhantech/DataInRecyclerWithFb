package com.example.datainrecyclerwithfb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView nameTextView,rollNoTextView;
    private Button sendButton;
    private RecyclerView recyclerView;
    private MyDataAdapter myDataAdapter;
    private  FirebaseDatabase  firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;
    private List<PojoClass> pojoClassesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intialize();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");
        sendButton.setOnClickListener(onClickListener);
        pojoClassesList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myDataAdapter=new MyDataAdapter(MainActivity.this,pojoClassesList);
        recyclerView.setAdapter(myDataAdapter);
        childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //  Toast.makeText(MainActivity.this,"I am child listener",Toast.LENGTH_LONG).show();
                PojoClass pojoClass=dataSnapshot.getValue(PojoClass.class);

                String name= pojoClass.getName();
                String roll= pojoClass.getRoll();
                Toast.makeText(MainActivity.this,"Name is"+ pojoClass.getName().toString()+"Roll no is"+pojoClass.getRoll(),Toast.LENGTH_LONG).show();
                pojoClassesList.add(pojoClass);
                pojoClass.setUid(dataSnapshot.getKey());
                myDataAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                PojoClass pojoClass=dataSnapshot.getValue(PojoClass.class);
                //pojoclass hold deleted item objec
                pojoClass.setUid(dataSnapshot.getKey());
                pojoClassesList.remove(pojoClass);
                myDataAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };





    databaseReference.addChildEventListener(childEventListener);

    }

   View.OnClickListener onClickListener=new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Toast.makeText(MainActivity.this,"I am working",Toast.LENGTH_LONG).show();
           String name=nameTextView.getText().toString();
           String roll=rollNoTextView.getText().toString();
           PojoClass pojoClass=new PojoClass(name,roll);
           String key=databaseReference.push().getKey();
           databaseReference.child(key).setValue(pojoClass);
       }
   };

    private void intialize()
    {
        nameTextView=findViewById(R.id.nameTextViewId);
        rollNoTextView=findViewById(R.id.rollNoTextViewId);
        sendButton=findViewById(R.id.sendButtonId);
        recyclerView=findViewById(R.id.recyclerViewId);
    }


}
