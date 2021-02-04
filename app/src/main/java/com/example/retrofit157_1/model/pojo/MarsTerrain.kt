package com.example.retrofit157_1.model.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// (POJO)
@Entity(tableName = "mars_terrain_table")
data class MarsTerrain(
                        @PrimaryKey
                        @SerializedName("id")
                        val id: String,
                        @SerializedName("price")
                        val price: Long,
                        @SerializedName("type")
                        val type: String,
                        @SerializedName("img_src")
                        val srcImg: String)
