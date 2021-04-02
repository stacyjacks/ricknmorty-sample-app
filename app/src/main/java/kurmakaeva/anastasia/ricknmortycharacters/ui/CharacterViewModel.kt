package kurmakaeva.anastasia.ricknmortycharacters.ui

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.ricknmortycharacters.repo.CharacterListRepository
import kurmakaeva.anastasia.ricknmortycharacters.service.RickAndMortyApiService

class CharacterViewModel(): ViewModel() {

    private val repository = CharacterListRepository(RickAndMortyApiService.instance)

    private val _listOfCharacters = MutableLiveData<List<CharacterData>>()
    val listOfCharacters: LiveData<List<CharacterData>>
        get() = _listOfCharacters

    private val _singleCharacter = MutableLiveData<CharacterData>()
    val singleCharacter: LiveData<CharacterData>
        get() = _singleCharacter

    fun getAllCharacters() {
        viewModelScope.launch {
            _listOfCharacters.value = repository.getAllCharacters()
        }
    }

    fun getSingleCharacter(position: Int) {
        viewModelScope.launch {
            _singleCharacter.value = repository.getIndividualCharacter(position)
        }
    }

    data class CharacterData(
        val id: Int = 0,
        val name: String = "",
        val status: String = "",
        val species: String = "",
        val gender: String = "",
        val origin: OriginData,
        val locationData: LastKnownLocationData,
        val image: String = ""
    )

    data class LastKnownLocationData(
        val name: String = ""
    )

    data class OriginData(
        val name: String = ""
    )
}