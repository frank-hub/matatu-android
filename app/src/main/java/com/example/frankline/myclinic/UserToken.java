package com.example.frankline.myclinic;

import com.google.gson.annotations.SerializedName;

public class UserToken {
    @SerializedName("access_token")
    public String authToken;
    @SerializedName("refresh_token")
    public String refreshToken;
}
