package com.eihror.coroutines.model

import com.google.gson.annotations.SerializedName

data class Post (

        @SerializedName("id")
        val id: Int? = null,

        @SerializedName("title")
        val title: String? = null,

        @SerializedName("body")
        val body: String? = null
)