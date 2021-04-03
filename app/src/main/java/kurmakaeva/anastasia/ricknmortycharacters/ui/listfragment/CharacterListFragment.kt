package kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import kurmakaeva.anastasia.ricknmortycharacters.R
import kurmakaeva.anastasia.ricknmortycharacters.databinding.FragmentCharacterListBinding
import kurmakaeva.anastasia.ricknmortycharacters.paging.LoadStateAdapter
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel

class CharacterListFragment: Fragment(), SelectableCharacter {

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var adapter: CharacterListAdapter
    private val sharedViewModel: CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_character_list, container,false)

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CharacterListAdapter(this)
        binding.listOfCharactersRV.adapter = adapter.withLoadStateFooter(footer = LoadStateAdapter {
            adapter.retry()
        })

        checkLoadingState()

        sharedViewModel.listOfCharacters.observe(viewLifecycleOwner, Observer {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })

        sharedViewModel.showSnackbar.observe(viewLifecycleOwner, Observer {
            it?.let {
                showSnackbarOnError(it)
            }
        })
    }

    override fun selectedCharacter(position: Int) {
        sharedViewModel.getSingleCharacter(position)
        val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(position)
        this.findNavController().navigate(action)
    }

    private fun showSnackbarOnError(desc: String) {
        Snackbar
            .make(requireActivity().findViewById(android.R.id.content),
                desc,
                Snackbar.LENGTH_SHORT)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            .show()
    }

    private fun checkLoadingState() {
        adapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
            }
            val errorState = when {
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                else -> null
            }
            errorState?.let {
                showSnackbarOnError(getString(R.string.loading_error_message))
            }
        }
    }
}