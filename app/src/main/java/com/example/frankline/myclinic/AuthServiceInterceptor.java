package com.example.frankline.myclinic;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by eric on 2/16/18.
 */

public class AuthServiceInterceptor implements Interceptor {
    private static final String MIDDLEWARE = "AUTH";
    private Context ctx;

    AuthServiceInterceptor(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(request.header(MIDDLEWARE) != null){
            request = request.newBuilder()
                    .headers(request.headers())
                    .header("Authorization","Bearer ".concat(Cache.getAuthToken(ctx)))
                    .build();
        }
        return chain.proceed(request);
    }
}
