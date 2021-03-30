package kurmakaeva.anastasia.ricknmortycharacters

import kurmakaeva.anastasia.ricknmortycharacters.service.RickAndMortyApiService
import kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment.CharacterListViewModel

class CharacterListRepository(private val rickAndMortyApiService: RickAndMortyApiService) {
    suspend fun getAllCharacters(): List<CharacterListViewModel.CharacterData> {
        val response = rickAndMortyApiService.getCharacters()
        val listOfCharacters = response.results

        return listOfCharacters.map {
            CharacterListViewModel.CharacterData(
                it.id,
                it.name,
                it.status,
                it.species,
                it.gender,
                CharacterListViewModel.OriginData(it.origin.name),
                CharacterListViewModel.LastKnownLocationData(it.location.name),
                it.image
            )
        }
    }
}