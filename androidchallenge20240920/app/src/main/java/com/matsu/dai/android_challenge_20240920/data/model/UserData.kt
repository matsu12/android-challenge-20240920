package com.matsu.dai.android_challenge_20240920.data.repo.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("account_id")
    var accountId: Long,
    @SerializedName("room_id")
    val roomId: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("chatwork_id")
    val chatworkId: String,
    @SerializedName("organization_id")
    val organizationId: Long,
    @SerializedName("organization_name")
    val organizationName: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("introduction")
    val introduction: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("tel_organization")
    val telOrganization: String,
    @SerializedName("tel_extension")
    val telExtension: String,
    @SerializedName("tel_mobile")
    val telMobile: String,
    @SerializedName("skype")
    val skype: String,
    @SerializedName("facebook")
    val facebook: String,
    @SerializedName("twitter")
    val twitter: String,
    @SerializedName("avatar_image_url")
    val avatarImageUrl: String,
    @SerializedName("login_mail")
    val loginMail: String
) {

}