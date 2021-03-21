package com.example.loginpage.database;

import androidx.room.TypeConverter;

import java.util.UUID;

public class Converter {

    @TypeConverter
    public UUID conStrToUUID(String str){
        return UUID.fromString(str);
    }

    @TypeConverter
    public String conUUIDToStr(UUID uuid){
        return uuid.toString();
    }

}
