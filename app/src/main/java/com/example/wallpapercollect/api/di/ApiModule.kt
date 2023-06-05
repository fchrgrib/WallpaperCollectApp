package com.example.wallpapercollect.api.di

import android.util.Log
import android.webkit.CookieManager
import com.example.wallpapercollect.api.ApiConstants
import com.example.wallpapercollect.api.WallpaperCollectAPI
import com.example.wallpapercollect.api.interceptors.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): WallpaperCollectAPI {
        return builder
            .build()
            .create(WallpaperCollectAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        var cookieManager: CookieManager
        val timeOutMillis = 20000

        val builder = OkHttpClient.Builder()
            .followRedirects(false)
            .addInterceptor(loggingInterceptor)
            .cookieJar(object : CookieJar {

                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {

                    cookieManager = CookieManager.getInstance()
                    for (cookie in cookies) {
                        cookieManager.setCookie(url.toString(), cookie.toString())
                        Log.i(
                            "ok",
                            "saveFromResponse :  Cookie url : $url$cookie"
                        )
                    }
                }

                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    cookieManager = CookieManager.getInstance()

                    val cookies: ArrayList<Cookie> = ArrayList()
                    if (cookieManager.getCookie(url.toString()) != null) {
                        val splitCookies =
                            cookieManager.getCookie(url.toString()).split("[,;]".toRegex())
                                .dropLastWhile { it.isEmpty() }.toTypedArray()
                        for (i in splitCookies.indices) {
                            cookies.add(Cookie.parse(url, splitCookies[i].trim { it <= ' ' })!!)
                            Log.i(
                                "ok",
                                "loadForRequest :Cookie.add ::  " + Cookie.parse(
                                    url,
                                    splitCookies[i].trim { it <= ' ' })!!
                            )
                        }
                    }
                    return cookies
                }
            })
            .followSslRedirects(false)
            .cache(null)
            .addInterceptor { chain ->
                val request: Request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }

            .connectTimeout(timeOutMillis.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(timeOutMillis.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(timeOutMillis.toLong(), TimeUnit.MILLISECONDS)


        return builder.build()

    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(loggingInterceptor)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor

    }
}