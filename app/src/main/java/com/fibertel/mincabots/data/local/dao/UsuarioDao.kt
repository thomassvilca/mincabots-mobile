package com.fibertel.mincabots.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fibertel.mincabots.data.local.entity.UsuarioEntity

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: UsuarioEntity)

    @Query("""
        SELECT * FROM usuarios
        WHERE dni = :dni AND passwordHash = :passwordHash AND estado = 1
        LIMIT 1
    """)
    suspend fun login(dni: String, passwordHash: String): UsuarioEntity?

    @Query("""
        SELECT * FROM usuarios
        WHERE idUsuario = :idUsuario
        LIMIT 1
    """)
    suspend fun getById(idUsuario: String): UsuarioEntity?
}
