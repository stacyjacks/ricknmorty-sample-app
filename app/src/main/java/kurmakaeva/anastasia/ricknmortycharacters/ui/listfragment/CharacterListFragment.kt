package kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kurmakaeva.anastasia.ricknmortycharacters.R
import kurmakaeva.anastasia.ricknmortycharacters.databinding.FragmentCharacterListBinding

class CharacterListFragment: Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var adapter: CharacterListAdapter
    private lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_character_list, container, false)

        viewModel = ViewModelProvider(this).get(CharacterListViewModel::class.java)

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CharacterListAdapter()
        binding.listOfCharactersRV.adapter = adapter

        viewModel.getAllCharacters()
        viewModel.listOfCharacters.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}