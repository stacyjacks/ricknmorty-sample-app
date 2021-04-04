package kurmakaeva.anastasia.ricknmortycharacters.repo

import kurmakaeva.anastasia.ricknmortycharacters.model.RickAndMortyCharacter
import kurmakaeva.anastasia.ricknmortycharacters.service.RickAndMortyApiService
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel

class CharacterRepository(private val rickAndMortyApiService: RickAndMortyApiService): ICharacterRepository {

    private var cachedCharacters: MutableList<RickAndMortyCharacter> = mutableListOf()

    override suspend fun getAllCharacters(page: Int): List<CharacterViewModel.CharacterData> {
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

    override fun getIndividualCharacter(index: Int): CharacterViewModel.CharacterData {
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