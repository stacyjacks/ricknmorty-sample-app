package kurmakaeva.anastasia.ricknmortycharacters.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.android.material.snackbar.Snackbar
import kurmakaeva.anastasia.ricknmortycharacters.repo.CharacterListRepository
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel
import java.lang.Exception

class ListPagingSource(
    private val repository: CharacterListRepository, private val showSnackbar: (String?) -> Unit)
    : PagingSource<Int, CharacterViewModel.CharacterData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterViewModel.CharacterData> {
        return try {
            val page = params.key ?: 1
            val response = repository.getAllCharacters(page)

            showSnackbar(null)

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            showSnackbar(e.localizedMessage)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterViewModel.CharacterData>): Int? {
        return state.anchorPosition
    }
}