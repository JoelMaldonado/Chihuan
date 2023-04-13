package com.jjmf.chihuancompose.Data.Module

import com.google.firebase.firestore.FirebaseFirestore
import com.jjmf.chihuancompose.Util.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebase() = FirebaseFirestore.getInstance()

    @UsuarioCollection
    @Provides
    @Singleton
    fun provideUsuario() = provideFirebase().collection(FB_USUARIOS)

    @DeudaCollection
    @Provides
    @Singleton
    fun provideDeuda() = provideFirebase().collection(FB_DEUDAS)

    @HistorialCollection
    @Provides
    @Singleton
    fun provideHistorial() = provideFirebase().collection(FB_HISTORIAL)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UsuarioCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DeudaCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HistorialCollection

}