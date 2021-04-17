package com.example.stomatology;

import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "patients_table")
public class Entity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    private String age;
    private String address;
    private String diagnostics;
    private String date;
    private String time;

    public Entity(String name, String phone, String age, String address, String diagnostics, String date, String time) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.address = address;
        this.diagnostics = diagnostics;
        this.date = date;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getDiagnostics() {
        return diagnostics;
    }

    public String getTime() {
        return time;
    }
}
