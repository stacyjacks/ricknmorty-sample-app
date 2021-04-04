package kurmakaeva.anastasia.ricknmortycharacters.model

data class RickAndMortyApiResponse(
    val info: DataInfo,
    val results: List<RickAndMortyCharacter>
)

data class DataInfo(
    val count: Int,
    val pages: Int,
    val next: String
)

data class RickAndMortyCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Origin,
    val location: LastKnownLocation,
    val image: String,
)

data class Origin(
    val name: String
)

data class LastKnownLocation(
    val name: String
)