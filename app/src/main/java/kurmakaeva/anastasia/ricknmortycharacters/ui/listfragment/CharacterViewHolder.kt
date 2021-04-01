package kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kurmakaeva.anastasia.ricknmortycharacters.databinding.CharacterViewholderBinding
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel

class CharacterViewHolder(private val context: Context, private val binding: CharacterViewholderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(characterItem: CharacterViewModel.CharacterData) {
        binding.apply {
            characterName.text = characterItem.name
            characterStatus.text = characterItem.status

            Glide.with(context)
                .load(characterItem.image)
                .into(characterThumbnail)
        }
    }

    object DiffCallback: DiffUtil.ItemCallback<CharacterViewModel.CharacterData>() {
        override fun areItemsTheSame(
            oldItem: CharacterViewModel.CharacterData,
            newItem: CharacterViewModel.CharacterData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterViewModel.CharacterData,
            newItem: CharacterViewModel.CharacterData): Boolean {
            return oldItem.id == newItem.id
        }
    }
}