import android.app.appsearch.SearchResults
import com.example.myapplication.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers
import retrofit2.http.Query
import java.net.URLEncoder

interface ApiService {
    @Headers("Authorization: Bearer 77931ca9-a9ea-4c9d-8a0e-e7f2fedaefbb")
    @GET("v2/cards")
    fun search(@Query("q") q: String,
               @Query("page") page:Int,
               @Query("pageSize") pageSize:Int): Call<SearchResults>
}

class PokemonSource(){
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.pokemontcg.io/") // Base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    fun search(query :String, page: Int){
        val call = apiService.search(query, page, 20)
        call.enqueue(object : Callback<SearchResults> {
            override fun onResponse(call: Call<SearchResults>, response: Response<SearchResults>) {
                //ngak bisa dapat successful query soalnya character ":" diganti ke "%3A"
                if (response.isSuccessful && response.body() != null) {
                    val results = response.body()

                }
            }

            override fun onFailure(call: Call<SearchResults>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

}