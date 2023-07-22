package com.example.planets.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "planets")
public class Planets implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int ID = 0;

    @ColumnInfo(name = "name")
    String name = "";

    @ColumnInfo(name = "size")
    String size = "";

    @ColumnInfo(name = "description")
    String description = "";

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
