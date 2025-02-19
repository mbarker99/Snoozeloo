package com.embarkapps.snoozeloo.di

import android.content.Context
import androidx.room.Room
import com.embarkapps.snoozeloo.alarms.data.alarmscheduler.AlarmSchedulerImpl
import com.embarkapps.snoozeloo.alarms.data.db.AlarmDao
import com.embarkapps.snoozeloo.alarms.data.db.AlarmDatabase
import com.embarkapps.snoozeloo.alarms.domain.alarmscheduler.AlarmScheduler
import com.embarkapps.snoozeloo.core.data.navigation.NavigatorImpl
import com.embarkapps.snoozeloo.core.domain.Constants
import com.embarkapps.snoozeloo.core.domain.navigation.Navigator
import com.embarkapps.snoozeloo.core.presentation.navigation.Destination
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

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = NavigatorImpl(
        startDestination = Destination.AlarmsGraph
    )

    @Provides
    @Singleton
    fun providesAlarmScheduler(@ApplicationContext context: Context): AlarmScheduler =
        AlarmSchedulerImpl(
            context
        )
}