package com.matsu.dai.android_challenge_20240920.data.repo.model

import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("room_id") val roomId: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("sticky") val sticky: Boolean,
    @SerializedName("unread_num") val unreadNum: Int,
    @SerializedName("mention_num") val mentionNum: Int,
    @SerializedName("mytask_num") val mytaskNum: Int,
    @SerializedName("message_num") val messageNum: Int,
    @SerializedName("file_num") val fileNum: Int,
    @SerializedName("task_num") val taskNum: Int,
    @SerializedName("icon_path") val iconPath: String?,
    @SerializedName("last_update_time") val lastUpdateTime: Long
) {

}
