package com.zhouchen.network
import com.google.gson.annotations.SerializedName
import com.zhouchen.datalayer.model.Sample

data class SampleResponse(
        @SerializedName("status_code") var statusCode: String,
        @SerializedName("content") var content: List<Sample>
)

