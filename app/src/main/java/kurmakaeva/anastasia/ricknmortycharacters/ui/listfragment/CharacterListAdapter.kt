package kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kurmakaeva.anastasia.ricknmortycharacters.databinding.CharacterViewholderBinding
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel

interface SelectableCharacter {
    fun selectedCharacter(position: Int)
}

class CharacterListAdapter(private val selectableCharacter: SelectableCharacter)
    : ListAdapter<CharacterViewModel.CharacterData, CharacterViewHolder>(CharacterViewHolder.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterViewholderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return CharacterViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val characterItem = getItem(position)

        if (characterItem != null) {
            holder.bind(characterItem)
        }

        holder.itemView.setOnClickListener {
            selectableCharacter.selectedCharacter(position)
        }
    }
}