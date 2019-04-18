package com.pachouri.classicalmusicplayer.infrastructure.api.response.albums

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ankit Pachouri on 18/04/19.
 */
class Item {

    @SerializedName("album_group")
    @Expose
    private var albumGroup: String? = null
    @SerializedName("album_type")
    @Expose
    private var albumType: String? = null
    @SerializedName("artists")
    @Expose
    private var artists: List<Artist>? = null
    @SerializedName("available_markets")
    @Expose
    private var availableMarkets: List<String>? = null
    @SerializedName("external_urls")
    @Expose
    private var externalUrls: ExternalUrls? = null
    @SerializedName("href")
    @Expose
    private var href: String? = null
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("images")
    @Expose
    private var images: List<Image>? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("release_date")
    @Expose
    private var releaseDate: String? = null
    @SerializedName("release_date_precision")
    @Expose
    private var releaseDatePrecision: String? = null
    @SerializedName("total_tracks")
    @Expose
    private var totalTracks: Int? = null
    @SerializedName("type")
    @Expose
    private var type: String? = null
    @SerializedName("uri")
    @Expose
    private var uri: String? = null

    fun getAlbumGroup(): String? {
        return albumGroup
    }

    fun getAlbumType(): String? {
        return albumType
    }

    fun getArtists(): List<Artist>? {
        return artists
    }

    fun getAvailableMarkets(): List<String>? {
        return availableMarkets
    }

    fun getExternalUrls(): ExternalUrls? {
        return externalUrls
    }

    fun getHref(): String? {
        return href
    }

    fun getId(): String? {
        return id
    }

    fun getImages(): List<Image>? {
        return images
    }

    fun getName(): String? {
        return name
    }

    fun getReleaseDate(): String? {
        return releaseDate
    }

    fun getReleaseDatePrecision(): String? {
        return releaseDatePrecision
    }

    fun getTotalTracks(): Int? {
        return totalTracks
    }

    fun getType(): String? {
        return type
    }

    fun getUri(): String? {
        return uri
    }
}