package com.example.datainrecyclerwithfb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.zip.Inflater;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyDataHolder> {
    public static final String USER_KEY ="userkey";
    private Context context;
   private List<PojoClass> pojoClassesList;


    public MyDataAdapter(Context context,List<PojoClass> pojoClassesList) {
        this.context = context;
        this.pojoClassesList = pojoClassesList;

    }

    @NonNull
    @Override
    public MyDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=LayoutInflater.from(context).inflate(R.layout.recyclerviewitemlayout,parent,false);
        return new MyDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDataHolder holder, int position) {
        final PojoClass pojoClass=pojoClassesList.get(position);
        holder.name.setText(pojoClass.getName());
        holder.roll.setText(pojoClass.getRoll());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=pojoClass.getUid();
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra(USER_KEY,uid);
                context.startActivity(intent);
            }
        });

        holder.name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String userid=pojoClass.getUid();

                Task<Void> task=Utils.removeUser(userid);
                task.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context,"User Removed From DataBase",Toast.LENGTH_LONG).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return pojoClassesList.size();
    }

    public class MyDataHolder extends RecyclerView.ViewHolder {
        TextView name;
            TextView roll;


        public MyDataHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameid);
            roll=itemView.findViewById(R.id.rollid);
        }
    }
}
