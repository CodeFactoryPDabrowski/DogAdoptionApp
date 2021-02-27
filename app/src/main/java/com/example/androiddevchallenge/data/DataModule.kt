package com.example.androiddevchallenge.data

import android.content.Context
import com.example.androiddevchallenge.data.source.DogDatabase
import com.example.androiddevchallenge.data.source.DogRoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): DogDatabase {
        return DogDatabase.getInstance(context)
    }


    @Singleton
    @Provides
    fun provideDao(database: DogDatabase): DogRoomDao = database.dogsDao()


}