package com.jjmf.chihuancompose.Data.Repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.chihuancompose.Data.Module.FirebaseModule
import com.jjmf.chihuancompose.Data.Model.Deuda
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface DeudaRepository {
    suspend fun getList():Flow<List<Deuda>>
    suspend fun delete(id: String)
    suspend fun update(deuda: Deuda)
    suspend fun insert(deuda: Deuda) : String
}

class DeudaRepositoryImpl @Inject constructor(
    @FirebaseModule.DeudaCollection private val fb:CollectionReference
): DeudaRepository {

    override suspend fun getList(): Flow<List<Deuda>> = callbackFlow {
        val listado = fb.addSnapshotListener { sna, _ ->
            val lista = mutableListOf<Deuda>()
            for (i in sna!!.documents){
                val product = i.toObject(Deuda::class.java)
                product!!.id = i.id
                lista.add(product)
            }
            trySend(lista).isSuccess
        }

        awaitClose { listado.remove() }
    }

    override suspend fun delete(id: String) {
        fb.document(id).delete()
    }

    override suspend fun update(deuda: Deuda) {
        fb.document(deuda.id!!).set(deuda)
    }

    override suspend fun insert(deuda: Deuda) : String {
        var id = ""
        fb.add(deuda).addOnSuccessListener {
            id = it.id
        }.await()
        return id
    }

}