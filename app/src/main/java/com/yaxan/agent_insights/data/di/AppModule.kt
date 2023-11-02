package com.yaxan.agent_insights.data.di

import android.content.Context
import android.content.SharedPreferences
import com.yaxan.agent_insights.common.Constants.SHARED_PREFS
import com.yaxan.agent_insights.data.repository.RepositoryImpl
import com.yaxan.agent_insights.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    companion object {

        @Provides
        @Singleton
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        }

        @Provides
        @Singleton
        fun providesRepository(sharedPreferences: SharedPreferences): Repository {
          return RepositoryImpl(sharedPreferences)
        }

    }
}