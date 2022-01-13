package com.example.runningtracker.depndancyinjection

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.runningtracker.db.RunningDataBase
import com.example.runningtracker.others.Constants.RUNNING_DATABASE_NAME
import com.example.runningtracker.others.Constants.SHARED_PREFERENCES_NAME
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

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context)=
        context.getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref : SharedPreferences) = sharedPref.getString("KEY_NAME","")?:""


    @Singleton
    @Provides
    fun provideWeight(sharedPref : SharedPreferences) = sharedPref.getFloat("KEY_WEIGHT",80f)


    @Singleton
    @Provides
    fun provideFirstTime(sharedPref : SharedPreferences) = sharedPref.getBoolean("KEY_FIRST_TIME_TOGGLE",true)
}