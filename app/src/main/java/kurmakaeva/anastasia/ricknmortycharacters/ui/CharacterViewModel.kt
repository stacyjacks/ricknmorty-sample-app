package kurmakaeva.anastasia.ricknmortycharacters.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.ricknmortycharacters.paging.ListPagingSource
import kurmakaeva.anastasia.ricknmortycharacters.repo.ICharacterRepository

class CharacterViewModel(private val repository: ICharacterRepository): ViewModel() {

    private fun getCharacters() = Pager(PagingConfig(pageSize = 20, prefetchDistance = 1)) {
        ListPagingSource(repository) {
            emitSnackbarEvent(it)
        }
    }.liveData

    val listOfCharacters = getCharacters().cachedIn(viewModelScope)

    private val _singleCharacter = MutableLiveData<CharacterData>()
    val singleCharacter: LiveData<CharacterData>
        get() = _singleCharacter


    fun getSingleCharacter(position: Int) {
        viewModelScope.launch {
            try {
                _singleCharacter.value = repository.getIndividualCharacter(position)
            } catch (e: Exception) {
                emitSnackbarEvent(e.localizedMessage)
            }
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

    private val _showSnackbar = MutableLiveData<String?>()
    val showSnackbar: LiveData<String?>
        get() = _showSnackbar

    private fun emitSnackbarEvent(errorMessage: String?) {
        _showSnackbar.value = errorMessage
    }
}