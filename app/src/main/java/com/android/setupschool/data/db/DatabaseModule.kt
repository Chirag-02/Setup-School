package com.android.setupschool.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDb(@ApplicationContext context: Context): SchoolDatabase {
        return Room.databaseBuilder(context, SchoolDatabase::class.java, "db.sqlite").build()
    }

}