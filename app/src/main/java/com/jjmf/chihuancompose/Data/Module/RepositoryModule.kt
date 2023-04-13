package com.jjmf.chihuancompose.Data.Module

import com.jjmf.chihuancompose.Data.Repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun usuarioRepo(repo: UsuarioRepositoryImpl): UsuarioRepository

    @Binds
    abstract fun deudaRepo(repo: DeudaRepositoryImpl): DeudaRepository

    @Binds
    abstract fun historialRepo(repo: HistorialRepositoryImpl): HistorialRepository
}