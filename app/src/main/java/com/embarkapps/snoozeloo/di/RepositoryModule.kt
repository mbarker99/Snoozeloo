package com.embarkapps.snoozeloo.di

import com.embarkapps.snoozeloo.alarms.data.repository.AlarmRepositoryImpl
import com.embarkapps.snoozeloo.alarms.domain.repository.AlarmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAlarmRepository(impl: AlarmRepositoryImpl): AlarmRepository
}