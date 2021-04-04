package kurmakaeva.anastasia.ricknmortycharacters.repo

import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel

interface ICharacterRepository {
    suspend fun getAllCharacters(page: Int): List<CharacterViewModel.CharacterData>
    fun getIndividualCharacter(index: Int): CharacterViewModel.CharacterData
}