package kurmakaeva.anastasia.ricknmortycharacters.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kurmakaeva.anastasia.ricknmortycharacters.repo.CharacterListRepository
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel
import java.lang.Exception

class ListPagingSource(private val repository: CharacterListRepository)
    : PagingSource<Int, CharacterViewModel.CharacterData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterViewModel.CharacterData> {
        return try {
            val page = params.key ?: 1
            val response = repository.getAllCharacters(page)


            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterViewModel.CharacterData>): Int? {
        TODO("Not yet implemented")
    }
}