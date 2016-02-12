package com.sunil.test.musicsearchapp.Utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit.mime.TypedInput;


public class JsonTypedInput implements TypedInput{

    private final byte[] mStringBytes;

    JsonTypedInput(byte[] stringBytes) {
        this.mStringBytes = stringBytes;
    }


    @Override
    public String mimeType() {
        return "application/json; charset=UTF-8";
    }



    @Override
    public long length() {
        return mStringBytes.length;
    }

    @Override
    public InputStream in() throws IOException {
        return new ByteArrayInputStream(mStringBytes);
    }
}