package com.jjmf.chihuancompose.Core

import com.jjmf.chihuancompose.Data.Model.Paises
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object PaisesApi{

    fun retro() : PaisesInterface{
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PaisesInterface::class.java)
    }

    interface PaisesInterface{

        @GET("all")
        suspend fun getAll() : Response<Paises>

    }
}