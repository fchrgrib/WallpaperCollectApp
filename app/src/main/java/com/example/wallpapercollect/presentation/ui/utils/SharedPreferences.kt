package com.example.wallpapercollect.presentation.ui.utils

import android.content.Context

fun isFirstTimeUser(context : Context): Boolean {
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    return sharedPrefs.getBoolean("isFirstTimeUser", true)
}
fun markUserAsNotFirstTime(context : Context) {
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    sharedPrefs.edit().putBoolean("isFirstTimeUser", false).apply()
}
fun isFirstTimeUserToProfile(context: Context): Boolean{
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    return sharedPrefs.getBoolean("isFirsTimeUserToProfile", true)
}
fun manipulateActivityUserToProfile(context : Context, boolean: Boolean) {
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    sharedPrefs.edit().putBoolean("isFirsTimeUserToProfile", boolean).apply()
}
fun isFirstTimeUserToWallpaper(context: Context):Boolean{
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    return sharedPrefs.getBoolean("isFirstTimeUserToWallpaper", true)
}
fun manipulateActivityUserToWallpaper(context : Context, boolean: Boolean) {
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    sharedPrefs.edit().putBoolean("isFirstTimeUserToWallpaper", boolean).apply()
}
fun userNameSharedPreference(context: Context):String{
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    return sharedPrefs.getString("userNameProfile","")?:""
}
fun manipulateUserNameSharedPreference(context: Context, userName:String){
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    sharedPrefs.edit().putString("userNameProfile",userName).apply()
}
fun phoneNumberSharedPreference(context: Context):String{
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    return sharedPrefs.getString("phoneNumberProfile","")?:""
}
fun manipulatePhoneNumberSharedPreference(context: Context, userName:String){
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    sharedPrefs.edit().putString("phoneNumberProfile",userName).apply()
}
fun emailSharedPreference(context: Context):String{
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    return sharedPrefs.getString("emailProfile","")?:""
}
fun manipulateEmailSharedPreference(context: Context, userName:String){
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    sharedPrefs.edit().putString("emailProfile",userName).apply()
}
fun photoProfileSharedPreference(context: Context):String{
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    return sharedPrefs.getString("photoProfile","")?:""
}
fun manipulatePhotoProfileSharedPreference(context: Context, userName:String){
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    sharedPrefs.edit().putString("photoProfile",userName).apply()
}