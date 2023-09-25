package com.example.bestapptesttask.data.network.dto


import com.google.gson.annotations.SerializedName

data class SingleMovieResponseDTO(
    @SerializedName("ageRating")
    val ageRating: Int,
    @SerializedName("alternativeName")
    val alternativeName: String,
    @SerializedName("audience")
    val audience: List<Audience>,
    @SerializedName("backdrop")
    val backdrop: Backdrop,
    @SerializedName("budget")
    val budget: Budget,
    @SerializedName("countries")
    val countries: List<Country>,
    @SerializedName("deletedAt")
    val deletedAt: Any,
    @SerializedName("description")
    val description: String,
    @SerializedName("distributors")
    val distributors: Distributors,
    @SerializedName("enName")
    val enName: Any,
    @SerializedName("externalId")
    val externalId: ExternalId,
    @SerializedName("facts")
    val facts: List<Fact>,
    @SerializedName("fees")
    val fees: Fees,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: Images,
    @SerializedName("imagesInfo")
    val imagesInfo: ImagesInfo,
    @SerializedName("isSeries")
    val isSeries: Boolean,
    @SerializedName("lists")
    val lists: List<Any>,
    @SerializedName("logo")
    val logo: Logo,
    @SerializedName("movieLength")
    val movieLength: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("names")
    val names: List<Name>,
    @SerializedName("persons")
    val personDTOS: List<PersonDTO>,
    @SerializedName("poster")
    val poster: Poster,
    @SerializedName("premiere")
    val premiere: Premiere,
    @SerializedName("productionCompanies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("ratingMpaa")
    val ratingMpaa: String,
    @SerializedName("seasonsInfo")
    val seasonsInfo: List<Any>,
    @SerializedName("sequelsAndPrequels")
    val sequelsAndPrequels: List<Any>,
    @SerializedName("seriesLength")
    val seriesLength: Any,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("similarMovies")
    val similarMovies: List<SimilarMovie>,
    @SerializedName("slogan")
    val slogan: String,
    @SerializedName("spokenLanguages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: Any,
    @SerializedName("technology")
    val technology: Technology,
    @SerializedName("ticketsOnSale")
    val ticketsOnSale: Boolean,
    @SerializedName("top10")
    val top10: Any,
    @SerializedName("top250")
    val top250: Int,
    @SerializedName("totalSeriesLength")
    val totalSeriesLength: Any,
    @SerializedName("type")
    val type: String,
    @SerializedName("typeNumber")
    val typeNumber: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("videos")
    val videos: Videos,
    @SerializedName("votes")
    val votes: Votes,
    @SerializedName("watchability")
    val watchability: Watchability,
    @SerializedName("year")
    val year: Int
)