package kurmakaeva.anastasia.ricknmortycharacters

import android.app.Application
import android.content.Context
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho
import kurmakaeva.anastasia.ricknmortycharacters.repo.CharacterRepository
import kurmakaeva.anastasia.ricknmortycharacters.repo.ICharacterRepository
import kurmakaeva.anastasia.ricknmortycharacters.service.RickAndMortyApiService
import kurmakaeva.anastasia.ricknmortycharacters.service.retrofit
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        val networkModule = module {
            single {
                retrofit.create(RickAndMortyApiService::class.java)
            }
        }

        val repositoryModule = module {
            viewModel {
                CharacterViewModel(get() as ICharacterRepository)
            }

            single { CharacterRepository(get()) as ICharacterRepository }
        }

        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule))
        }
    }

    companion object {
        var context: Context? = null
            private set
    }
}