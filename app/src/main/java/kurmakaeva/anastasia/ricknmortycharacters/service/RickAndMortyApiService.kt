package kurmakaeva.anastasia.ricknmortycharacters.service

import kurmakaeva.anastasia.ricknmortycharacters.model.RickAndMortyApiResponse
import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(): RickAndMortyApiResponse

    companion object {
        val instance: RickAndMortyApiService by lazy {
            retrofit.create(RickAndMortyApiService::class.java)
        }
    }
}