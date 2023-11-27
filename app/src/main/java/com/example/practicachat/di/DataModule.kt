package com.example.practicachat.di

import android.content.Context
import com.example.practicachat.data.database.DatabaseServiceImpl
import com.example.practicachat.data.network.api.FirebaseChatService
import com.example.practicachat.domain.DatabaseService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideDatabaseReference() = Firebase.database.reference

    @Singleton
    @Provides
    fun provideFirebaseService(reference: DatabaseReference) = FirebaseChatService(reference)

    @Singleton
    @Provides
    fun provideDataStoreService(@ApplicationContext context: Context) : DatabaseService{
        return DatabaseServiceImpl(context)
    }

}