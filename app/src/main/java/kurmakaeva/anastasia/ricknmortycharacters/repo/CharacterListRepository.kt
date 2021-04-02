package kurmakaeva.anastasia.ricknmortycharacters.repo

import kurmakaeva.anastasia.ricknmortycharacters.model.RickAndMortyCharacter
import kurmakaeva.anastasia.ricknmortycharacters.service.RickAndMortyApiService
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel

class CharacterListRepository(private val rickAndMortyApiService: RickAndMortyApiService) {
    private var cachedCharacters: MutableList<RickAndMortyCharacter> = mutableListOf()

    suspend fun getAllCharacters(page: Int): List<CharacterViewModel.CharacterData> {
        val listResponse = rickAndMortyApiService.getCharacters(page)
        val listOfCharacters = listResponse.results
        cachedCharacters.plusAssign(listOfCharacters)

        return listOfCharacters.map {
            CharacterViewModel.CharacterData(
                it.id,
                it.name,
                it.status,
                it.species,
                it.gender,
                CharacterViewModel.OriginData(it.origin.name),
                CharacterViewModel.LastKnownLocationData(it.location.name),
                it.image
            )
        }
    }

    fun getIndividualCharacter(index: Int): CharacterViewModel.CharacterData {
       return cachedCharacters.get(index).let {
           CharacterViewModel.CharacterData(
               it.id,
               it.name,
               it.status,
               it.species,
               it.gender,
               CharacterViewModel.OriginData(it.origin.name),
               CharacterViewModel.LastKnownLocationData(it.location.name),
               it.image
           )
       }
    }
}