package kurmakaeva.anastasia.ricknmortycharacters.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kurmakaeva.anastasia.ricknmortycharacters.MainCoroutineRule
import kurmakaeva.anastasia.ricknmortycharacters.getOrAwaitValue
import kurmakaeva.anastasia.ricknmortycharacters.repo.FakeTestRepository
import kurmakaeva.anastasia.ricknmortycharacters.repo.ICharacterRepository
import kurmakaeva.anastasia.ricknmortycharacters.repo.testCharacter
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class CharacterViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var characterRepository: ICharacterRepository


    @Before
    fun setupViewModel() {
        stopKoin()

        characterRepository = FakeTestRepository()
        characterViewModel = CharacterViewModel(characterRepository)
    }

    @Test
    fun getSingleCharacter_loadingDataOk() = runBlockingTest {
        // Given a ViewModel with a list of characters

        // When visiting an individual character data
        characterViewModel.getSingleCharacter(0)

        // Then
        val character = characterViewModel.singleCharacter.getOrAwaitValue()

        assertSame(character, testCharacter)
    }
}