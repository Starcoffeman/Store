package org.example.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataTimeMapper {

    public LocalDateTime parseToNormDate(LocalDateTime dateTime){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime q = LocalDateTime.parse(dateTime.toString(), formatter);
        return q;
    }

}
