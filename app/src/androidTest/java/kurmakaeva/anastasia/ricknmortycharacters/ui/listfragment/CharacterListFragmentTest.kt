package kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment

import android.app.Application
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import kurmakaeva.anastasia.ricknmortycharacters.DataBindingIdlingResource
import kurmakaeva.anastasia.ricknmortycharacters.EspressoIdlingResource
import kurmakaeva.anastasia.ricknmortycharacters.R
import kurmakaeva.anastasia.ricknmortycharacters.monitorFragment
import kurmakaeva.anastasia.ricknmortycharacters.repo.CharacterRepository
import kurmakaeva.anastasia.ricknmortycharacters.repo.ICharacterRepository
import kurmakaeva.anastasia.ricknmortycharacters.service.RickAndMortyApiService
import kurmakaeva.anastasia.ricknmortycharacters.service.retrofit
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mockito
import org.mockito.Mockito.verify

class CharacterListFragmentTest: AutoCloseKoinTest() {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: ICharacterRepository
    private lateinit var appContext: Application
    private lateinit var characterViewModel: CharacterViewModel

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun init() {
        stopKoin()
        appContext = getApplicationContext()

        val networkModule = module {
            single {
                retrofit.create(RickAndMortyApiService::class.java)
            }
        }

        val repositoryModule = module {
            viewModel {
                CharacterViewModel(get() as ICharacterRepository)
            }

            single { CharacterRepository(get()) as ICharacterRepository } // swap by fake repo
        }

        startKoin {
            modules(listOf(networkModule, repositoryModule))
        }

        repository = get() // Use FakeAndroidTestRepository if Internet is not available for the real repo
        characterViewModel = CharacterViewModel(repository)
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun navigateToDetailScreenWithInternet() {
        // Given the home screen of Rick&Morty list of characters
        val fragmentScenario =
            launchFragmentInContainer<CharacterListFragment>(Bundle(), R.style.Theme_RicknMortyCharacters)
        dataBindingIdlingResource.monitorFragment(fragmentScenario)

        val navController = Mockito.mock(NavController::class.java)
        fragmentScenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // When a click is performed on the first item
        Thread.sleep(5000)
        onView(withId(R.id.listOfCharactersRV))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    ViewMatchers.hasDescendant(withText("Rick Sanchez")), click()))

        // Then the navigation takes the user to the detail screen
        verify(navController)
            .navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(0))
    }
}