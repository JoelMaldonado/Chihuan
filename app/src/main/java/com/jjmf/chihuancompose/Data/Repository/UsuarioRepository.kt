package com.jjmf.chihuancompose.Data.Repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.Data.Module.FirebaseModule
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface UsuarioRepository {

    suspend fun getList(): Flow<List<Usuario>>
    suspend fun getListUsuarios(): List<Usuario>
    suspend fun insert(usuario: Usuario)
}

class UsuarioRepositoryImpl @Inject constructor(
    @FirebaseModule.UsuarioCollection private val fb: CollectionReference,
) : UsuarioRepository {
    override suspend fun getList(): Flow<List<Usuario>> = callbackFlow {
        val listado = fb.addSnapshotListener { sna, _ ->
            val lista = mutableListOf<Usuario>()
            for (i in sna!!.documents) {
                val product = i.toObject(Usuario::class.java)
                product!!.id = i.id
                lista.add(product)
            }
            trySend(lista).isSuccess
        }

        awaitClose { listado.remove() }
    }

    override suspend fun getListUsuarios(): List<Usuario> {
        val listado = mutableListOf<Usuario>()
        fb.get().await().documents.forEach {
            val ob = it.toObject(Usuario::class.java)!!
            ob.id = it.id
            listado.add(ob)
        }
        return listado
    }

    override suspend fun insert(usuario: Usuario) {
        fb.add(usuario).addOnSuccessListener {
            prefs.saveID(it.id)
        }
    }

}