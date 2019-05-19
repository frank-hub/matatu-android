package com.example.frankline.myclinic;

import com.google.gson.annotations.SerializedName;

public class UserToken {
    @SerializedName("success")
    public Token token;

    class Token {
        @SerializedName("token")
        public String authToken;
    }
}
