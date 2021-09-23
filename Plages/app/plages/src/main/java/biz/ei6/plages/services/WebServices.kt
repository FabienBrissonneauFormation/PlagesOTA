package biz.ei6.plages.services

import android.util.Log
import biz.ei6.plages.Plage
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


public interface PlageRestService {

    @GET("plages")
    suspend fun  listPlages() : ArrayList<Plage>

    @POST("plages")
    suspend fun  addPlage(@Body p : Plage ) : Plage
}

internal class ArrayListAdapter {
    @ToJson
    fun toJson(data : ArrayList<Plage>): List<Plage> = data

    @FromJson
    fun fromJson(data: List<Plage>):  ArrayList<Plage> = ArrayList<Plage> (data)

}


fun accesRetrofit(): PlageRestService {

    val moshi = Moshi.Builder()
        .add(ArrayListAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("http://192.168.1.109:8082")
        .build()

    Log.d("URL ", retrofit.baseUrl().toString())
    return retrofit.create(PlageRestService::class.java)
}

class RetrofitPlageDataSource (val synchroService : PlageRestService)  {
     suspend fun add(p: Plage) {
        synchroService.addPlage(p)
    }

     suspend fun readAll(): ArrayList<Plage> {
        return synchroService.listPlages()
    }

     suspend fun remove(anniv: Plage) {

    }
}

