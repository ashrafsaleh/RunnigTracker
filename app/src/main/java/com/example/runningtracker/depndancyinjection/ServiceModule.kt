package com.example.runningtracker.depndancyinjection

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.runningtracker.R
import com.example.runningtracker.others.Constants
import com.example.runningtracker.ui.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun fusedLocationProviderClient(@ApplicationContext context: Context)=
        FusedLocationProviderClient(context)

    @ServiceScoped
    @Provides
    fun provideMainPendingIntent(@ApplicationContext context: Context)=
        PendingIntent.getActivity(
            context, 0, Intent(context, MainActivity::class.java).also {
                it.action = Constants.ACTION_SHOW_TRACKING_FRAGMENT
            }, PendingIntent.FLAG_UPDATE_CURRENT
        )

    @ServiceScoped
    @Provides
    fun provideNotificationsBuilder(@ApplicationContext context: Context,pendingIntent: PendingIntent)=
        NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
            .setContentTitle("Running Tracker")
            .setContentText("00:00:00")
            .setContentIntent(pendingIntent)

}