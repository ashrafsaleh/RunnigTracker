package com.example.runningtracker.depndancyinjection

import android.content.Context
import androidx.room.Room
import com.example.runningtracker.db.RunningDataBase
import com.example.runningtracker.others.Constants.RUNNING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn (ApplicationComponent::class)
object AppModule {


    @Provides
    fun provideRunningDatabase( @ApplicationContext app:Context)=Room.databaseBuilder(
        app,
        RunningDataBase::class.java,
        RUNNING_DATABASE_NAME
    ).build()

    @Provides
    fun provideRunDao(db: RunningDataBase)=db.getRunDao()
}