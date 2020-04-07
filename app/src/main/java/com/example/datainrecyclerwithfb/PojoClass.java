package com.example.datainrecyclerwithfb;

import androidx.annotation.Nullable;

public class PojoClass {
    String name;
    String roll;
    String uid;

    public PojoClass(String name, String roll) {
        this.name = name;
        this.roll = roll;
    }

    public PojoClass() {
    }

    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof PojoClass)
        {
            PojoClass pojoClass=(PojoClass) obj;
            return this.uid.equals(pojoClass.getUid());
        }
        else {
            return false;
        }
    }
}
