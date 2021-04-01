package kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kurmakaeva.anastasia.ricknmortycharacters.R
import kurmakaeva.anastasia.ricknmortycharacters.databinding.FragmentCharacterListBinding
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
        binding.listOfCharactersRV.adapter = adapter

        sharedViewModel.getAllCharacters()
        sharedViewModel.listOfCharacters.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun selectedCharacter(position: Int) {
        sharedViewModel.getSingleCharacter(position)
        val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(position)
        this.findNavController().navigate(action)
    }
}