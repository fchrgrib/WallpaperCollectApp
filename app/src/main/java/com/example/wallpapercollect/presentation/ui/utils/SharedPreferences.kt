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