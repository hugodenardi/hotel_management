package com.example.hotel.HotelManagement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatarData {
    public Date formatar(String data){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formato.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
