package kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import kurmakaeva.anastasia.ricknmortycharacters.databinding.CharacterViewholderBinding

class CharacterListAdapter()
    : ListAdapter<CharacterListViewModel.CharacterData, CharacterViewHolder>(CharacterViewHolder.DiffCallback) {

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
//            val characterName = characterItem?.name ?: return@setOnClickListener
//            val characterStatus = characterItem.status
        }
    }
}