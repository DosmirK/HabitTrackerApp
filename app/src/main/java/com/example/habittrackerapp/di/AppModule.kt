package com.example.habittrackerapp.di

import android.content.Context
import androidx.room.Room
import com.example.habittrackerapp.data.db.HabitDatabase
import com.example.habittrackerapp.data.db.dao.HabitDao
import com.example.habittrackerapp.data.repositories.HabitRepositoryImpl
import com.example.habittrackerapp.domain.repositories.HabitRepository
import com.example.habittrackerapp.domain.usecase.HabitUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRoomDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, HabitDatabase::class.java, "habit"
    ).build()

    @Provides
    fun provideHabitDao(habitDatabase: HabitDatabase) =
        habitDatabase.getHabitDao()

    @Provides
    fun provideHabitRepository(habitDao: HabitDao): HabitRepository =
        HabitRepositoryImpl(habitDao)

    @Provides
    fun provideHabitUesCase(habitRepository: HabitRepository) =
        HabitUseCase(habitRepository)
}