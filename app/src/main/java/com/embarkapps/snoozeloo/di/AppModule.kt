package com.embarkapps.snoozeloo.di

import android.content.Context
import androidx.room.Room
import com.embarkapps.snoozeloo.alarms.data.db.AlarmDao
import com.embarkapps.snoozeloo.alarms.data.db.AlarmDatabase
import com.embarkapps.snoozeloo.core.domain.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideAlarmDao(database: AlarmDatabase): AlarmDao = database.alarmDao()


    @Provides
    @Singleton
    fun provideAlarmDatabase(@ApplicationContext context: Context): AlarmDatabase =
        Room.databaseBuilder(
            context,
            AlarmDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
}