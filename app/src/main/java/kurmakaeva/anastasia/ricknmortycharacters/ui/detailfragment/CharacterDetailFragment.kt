package kurmakaeva.anastasia.ricknmortycharacters.ui.detailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kurmakaeva.anastasia.ricknmortycharacters.R
import kurmakaeva.anastasia.ricknmortycharacters.databinding.FragmentCharacterDetailBinding
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharacterDetailFragment: Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val sharedViewModel by sharedViewModel<CharacterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCharacterDetailBinding.inflate(inflater, container,false)

        val args by navArgs<CharacterDetailFragmentArgs>()

        binding.lifecycleOwner = this

        viewModelSetup(args.position)

        return binding.root
    }

    private fun viewModelSetup(arguments: Int) {
        sharedViewModel.getSingleCharacter(arguments)
        sharedViewModel.singleCharacter.observe(viewLifecycleOwner, Observer {
            binding.apply {
                viewModel = sharedViewModel

                when (it.gender) {
                    "Male" -> characterGender.setImageResource(R.drawable.gender_male)
                    "Female" -> characterGender.setImageResource(R.drawable.gender_female)
                    else -> characterGender.setImageResource(R.drawable.gender_unknown)
                }

                when (it.status) {
                    "Alive" -> characterStatus.setImageResource(R.drawable.alive_status)
                    "Dead" -> characterStatus.setImageResource(R.drawable.dead_status)
                    else -> characterStatus.setImageResource(R.drawable.unknown_status)
                }

                Glide.with(requireContext())
                    .load(it.image)
                    .into(characterPicture)
            }
        })

    }
}