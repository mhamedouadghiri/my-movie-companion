package com.mhamed.mymoviecompanion.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTypeAdapter extends TypeAdapter<Date> {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        try {
            return DATE_FORMAT.parse(in.nextString());
        } catch (ParseException e) {
            return null;
        }
    }
}
