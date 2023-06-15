package com.example.wallpapercollect.api

object ApiConstants{
    const val BASE_URL = "https://wallpapercollectapi-production-c728.up.railway.app/"

}

object ApiEndPoints{

    // AUTH END POINT
    const val LOGIN_EMAIL_DEFAULT = "login-email-default"
    const val LOGIN_GOOGLE_SESSION = "login-google-session"
    const val LOGIN_FACEBOOK_SESSION = "login-facebook-session"
    const val REGISTER_EMAIL_DEFAULT = "register-email-default"
    const val REGISTER_GOOGLE_SESSION = "register-google-session"
    const val REGISTER_FACEBOOK_SESSION = "register-facebook-session"
    const val LOGOUT = "logout"


    // WALLPAPER PAGE
    const val WALLPAPER_COLLECTION = "wallpaper"
    const val UPLOAD_WALLPAPER = "wallpaper/upload"
    const val ALL_WALLPAPER = "wallpaper/images"

    // PROFILE INFO
    const val UPDATE_PROFILE = "wallpaper/profile/update_profile"
    const val VIEW_PROFILE = "wallpaper/profile"
    const val UPLOAD_PROFILE_PICTURE = "wallpaper/profile/upload_profile_picture"
    const val UPDATE_PROFILE_PICTURE = "wallpaper/profile/update_profile_picture"
    const val UPDATE_PROFILE_DESC = "wallpaper/profile/update_profile_desc"
    const val PHOTO_PROFILE = "wallpaper/photo_profile"

}