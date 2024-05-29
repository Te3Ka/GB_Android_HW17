package ru.te3ka.homework17.model

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    val photos: List<Photo>
)

data class Photo(
    val id: Int,
    val sol: Int,
    val camera: Camera,
    @SerializedName("img_src") val imgSrc: String,
    @SerializedName("earth_date") val earthDate: String,
    val rover: Rover
)

data class Camera(
    val id: Int,
    val name: String,
    @SerializedName("rover_id") val roverId: Int,
    @SerializedName("full_name") val fullName: String
)

data class Rover(
    val id: Int,
    val name: String,
    @SerializedName("landing_date") val landingDate: String,
    @SerializedName("launch_date") val launchDate: String,
    val status: String,
    @SerializedName("max_sol") val maxSol: Int,
    @SerializedName("max_date") val maxDate: String,
    @SerializedName("total_photos") val totalPhotos: Int,
    val cameras: List<Camera>
)
