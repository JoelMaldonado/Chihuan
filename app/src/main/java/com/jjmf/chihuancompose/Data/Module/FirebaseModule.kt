package com.jjmf.chihuancompose.Data.Module

import com.google.firebase.firestore.FirebaseFirestore
import com.jjmf.chihuancompose.Util.FB_DEUDAS
import com.jjmf.chihuancompose.Util.FB_DIARIO
import com.jjmf.chihuancompose.Util.FB_HISTORIAL
import com.jjmf.chihuancompose.Util.FB_USUARIOS
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

    @DiarioCollection
    @Provides
    @Singleton
    fun provideDiario() = provideFirebase().collection(FB_DIARIO)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UsuarioCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DeudaCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HistorialCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DiarioCollection

}