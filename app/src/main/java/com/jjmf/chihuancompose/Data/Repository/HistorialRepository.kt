package com.jjmf.chihuancompose.Data.Repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.chihuancompose.Data.Module.FirebaseModule
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface HistorialRepository {
    suspend fun getListFlow(idDeuda: String): Flow<List<Historial>>
    suspend fun insert(hist: Historial)
    suspend fun delete(idDeuda: String)
    suspend fun getList(idDeuda:String) : List<Historial>
}

class HistorialRepositoryImpl @Inject constructor(
    @FirebaseModule.HistorialCollection private val fb: CollectionReference,
) : HistorialRepository {

    override suspend fun getListFlow(idDeuda: String): Flow<List<Historial>> = callbackFlow {
        val listado = fb.addSnapshotListener { value, error ->
            val list = mutableListOf<Historial>()
            value!!.documents.forEach {
                val product = it.toObject(Historial::class.java)
                if (product!!.idDeuda == idDeuda) {
                    list.add(product)
                }
            }
            trySend(list).isSuccess
        }

        awaitClose { listado.remove() }
    }

    override suspend fun insert(hist: Historial) {
        fb.add(hist)
    }

    override suspend fun delete(idDeuda: String) {
        fb.whereEqualTo("idDeuda", idDeuda).get().await().forEach {
            fb.document(it.id).delete()
        }
    }

    override suspend fun getList(idDeuda: String) : List<Historial>{
        val list = mutableListOf<Historial>()
        fb.whereEqualTo("idDeuda", idDeuda).get().await().documents.forEach {
            val objeto = it.toObject(Historial::class.java)!!
            objeto.id = it.id
            list.add(objeto)
        }
        return list
    }

}