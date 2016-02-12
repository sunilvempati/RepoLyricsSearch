package com.sunil.test.musicsearchapp.Utilities;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedInput;

public class CleanGsonConverter extends GsonConverter {

    public CleanGsonConverter(Gson gson) {
        super(gson);
    }

    public CleanGsonConverter(Gson gson, String encoding) {
        super(gson, encoding);
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        String dirty = toString(body);
        //String clean = dirty.replaceAll("(^\\(|\\)$)", "");
        String clean = dirty.replace("song =", "");
                body = new JsonTypedInput(clean.getBytes(Charset.forName("UTF-8")));
        return super.fromBody(body, type);
    }
    private String toString(TypedInput body){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(body.in()));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
};