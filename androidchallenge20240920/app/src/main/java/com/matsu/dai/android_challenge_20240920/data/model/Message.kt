package com.matsu.dai.android_challenge_20240920.data.repo.model

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message_id") val messageId: String?,
    @SerializedName("account") val account: UserData?,
    @SerializedName("body") val body: String?,
    @SerializedName("send_time") val sendTime: Long?,
    @SerializedName("update_time") val updateTime: Long?
) {

}
