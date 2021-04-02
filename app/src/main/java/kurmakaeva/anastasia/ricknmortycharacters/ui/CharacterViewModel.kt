package kurmakaeva.anastasia.ricknmortycharacters.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.ricknmortycharacters.paging.ListPagingSource
import kurmakaeva.anastasia.ricknmortycharacters.repo.CharacterListRepository
import kurmakaeva.anastasia.ricknmortycharacters.service.RickAndMortyApiService

class CharacterViewModel(): ViewModel() {

    private val repository = CharacterListRepository(RickAndMortyApiService.instance)

    private fun getCharacters() = Pager(PagingConfig(pageSize = 20, prefetchDistance = 1)) {
        ListPagingSource(repository)
    }.liveData

    val listOfCharacters = getCharacters().cachedIn(viewModelScope)

    private val _singleCharacter = MutableLiveData<CharacterData>()
    val singleCharacter: LiveData<CharacterData>
        get() = _singleCharacter


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