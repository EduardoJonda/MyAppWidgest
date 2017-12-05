package com.android.jonda.seichi.c15.myappwidgest;

import java.util.List;

import models.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lucho on 04/12/2017.
 */

public interface ApiService {

    String API_BASE_URL = "https://usuarios-api-honda-ichiro.c9users.io/";

    @GET("api/v1/usuarios")
    Call<List<Usuario>> getUsuarios();

}
