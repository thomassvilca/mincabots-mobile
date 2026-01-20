package com.fibertel.mincabots.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fibertel.mincabots.data.local.entity.FormularioMasterEntity

@Dao
interface FormularioMasterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(formulario: FormularioMasterEntity)

    @Query("""
        SELECT * FROM formularios_master
        WHERE idUsuarioCreador = :userId
        ORDER BY fechaCreacion DESC
    """)
    suspend fun getByUsuario(userId: String): List<FormularioMasterEntity>
}