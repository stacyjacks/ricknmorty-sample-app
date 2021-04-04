package kurmakaeva.anastasia.ricknmortycharacters.service

import kurmakaeva.anastasia.ricknmortycharacters.model.RickAndMortyApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): RickAndMortyApiResponse
}