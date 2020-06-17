package com.zhouchen.datalayer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sample(
        @field:PrimaryKey
        val id: Int,
        val title: String,
        val dateTime:String,
        val ingress:String,
        val image:String,
        val created:Double,
        val changed:Double
)