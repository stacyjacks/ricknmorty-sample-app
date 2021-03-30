package kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kurmakaeva.anastasia.ricknmortycharacters.databinding.CharacterViewholderBinding

class CharacterViewHolder(private val context: Context, private val binding: CharacterViewholderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(characterItem: CharacterListViewModel.CharacterData) {
        binding.apply {
            characterName.text = characterItem.name
            characterStatus.text = characterItem.status

            Glide.with(context)
                .load(characterItem.image)
                .into(characterThumbnail)
        }
    }

    object DiffCallback: DiffUtil.ItemCallback<CharacterListViewModel.CharacterData>() {
        override fun areItemsTheSame(
            oldItem: CharacterListViewModel.CharacterData,
            newItem: CharacterListViewModel.CharacterData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterListViewModel.CharacterData,
            newItem: CharacterListViewModel.CharacterData): Boolean {
            return oldItem.id == newItem.id
        }
    }
}