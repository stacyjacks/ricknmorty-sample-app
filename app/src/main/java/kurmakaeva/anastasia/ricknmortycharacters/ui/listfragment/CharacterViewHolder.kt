package kurmakaeva.anastasia.ricknmortycharacters.ui.listfragment

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kurmakaeva.anastasia.ricknmortycharacters.R
import kurmakaeva.anastasia.ricknmortycharacters.databinding.CharacterViewholderBinding
import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel

class CharacterViewHolder(private val context: Context, private val binding: CharacterViewholderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(characterItem: CharacterViewModel.CharacterData) {
        binding.apply {
            characterName.text = characterItem.name
            characterStatus.text = characterItem.status

            when (characterItem.status) {
                "Alive" -> characterStatusInList.setImageResource(R.drawable.alive_status)
                "Dead" -> characterStatusInList.setImageResource(R.drawable.dead_status)
                else -> characterStatusInList.setImageResource(R.drawable.unknown_status)
            }

            Glide.with(context)
                .load(characterItem.image)
                .circleCrop()
                .into(characterThumbnail)

            characterThumbnail.contentDescription = characterItem.name
            characterStatusInList.contentDescription = characterItem.status
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