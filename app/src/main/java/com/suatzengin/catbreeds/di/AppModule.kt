package com.suatzengin.catbreeds.di

import android.content.Context
import androidx.room.Room
import com.suatzengin.catbreeds.common.Constants
import com.suatzengin.catbreeds.data.local.FavoritesDao
import com.suatzengin.catbreeds.data.local.FavoritesDatabase
import com.suatzengin.catbreeds.data.remote.CatBreedsApi
import com.suatzengin.catbreeds.data.repository.CatBreedsRepositoryImpl
import com.suatzengin.catbreeds.domain.repository.CatBreedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatBreedsApi(client: OkHttpClient): CatBreedsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CatBreedsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                addInterceptor(interceptor)
            }.build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(Constants.HEADER_KEY, Constants.API_KEY)
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideCatBreedsRepository(api: CatBreedsApi, dao: FavoritesDao): CatBreedsRepository {
        return CatBreedsRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FavoritesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FavoritesDatabase::class.java,
            "favorites_cat_breeds_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: FavoritesDatabase): FavoritesDao {
        return db.favoritesDao()
    }

}