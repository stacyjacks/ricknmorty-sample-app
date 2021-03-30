package kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.ricknmortycharacters.CharacterListRepository
import kurmakaeva.anastasia.ricknmortycharacters.service.RickAndMortyApiService

class CharacterListViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CharacterListRepository(RickAndMortyApiService.instance)

    private val _listOfCharacters = MutableLiveData<List<CharacterData>>()
    val listOfCharacters: LiveData<List<CharacterData>>
        get() = _listOfCharacters

    fun getAllCharacters() {
        viewModelScope.launch {
            _listOfCharacters.value = repository.getAllCharacters()
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