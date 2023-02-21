package com.jjmf.chihuancompose.Data.Repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.chihuancompose.Application.BaseApp
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.Data.Module.FirebaseModule
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

interface DiarioRepository {
    suspend fun getList(idUsuario:String) : Flow<List<Diario>>
    suspend fun insert(diario: Diario)
}

class DiarioRepositoryImpl @Inject constructor(
    @FirebaseModule.DiarioCollection private val fb: CollectionReference,
) : DiarioRepository {
    override suspend fun getList(idUsuario: String): Flow<List<Diario>>  = callbackFlow{
        val listado = fb.whereEqualTo("idUsuario", idUsuario).addSnapshotListener { sna, _ ->
            val lista = mutableListOf<Diario>()
            for (i in sna!!.documents){
                val product = i.toObject(Diario::class.java)
                product!!.id = i.id
                lista.add(product)
            }
            trySend(lista).isSuccess
        }

        awaitClose { listado.remove() }
    }

    override suspend fun insert(diario: Diario) {
        fb.add(diario)
    }

}