package kurmakaeva.anastasia.ricknmortycharacters.repo

import kurmakaeva.anastasia.ricknmortycharacters.ui.CharacterViewModel

var testCharacter = CharacterViewModel.CharacterData(
    3240934,
    "Rick",
    "Alive",
    "Human",
    "Male",
    CharacterViewModel.OriginData("Earth"),
    CharacterViewModel.LastKnownLocationData("Saturn"))

class FakeTestRepository(): ICharacterRepository {
    var listOfCharacters = mutableListOf(testCharacter)

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getAllCharacters(page: Int): List<CharacterViewModel.CharacterData> {
        if (shouldReturnError) {
            return emptyList()
        } else {
            return listOfCharacters
        }
    }

    override fun getIndividualCharacter(index: Int): CharacterViewModel.CharacterData {
        return listOfCharacters[0]
    }
}