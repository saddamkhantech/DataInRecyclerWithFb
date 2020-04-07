package com.example.datainrecyclerwithfb;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class Utils {

    public static Task<Void> removeUser( String userid)
    {
        Task<Void> task=FirebaseDatabase.getInstance().getReference("Users").child(userid).removeValue();
        return task;
    }
}
