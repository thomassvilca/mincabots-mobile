package com.fibertel.mincabots.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fibertel.mincabots.data.local.dao.FormularioMasterDao
import com.fibertel.mincabots.data.local.dao.UsuarioDao
import com.fibertel.mincabots.data.local.entity.FormularioMasterEntity
import com.fibertel.mincabots.data.local.entity.UsuarioEntity

@Database(
    entities = [
        UsuarioEntity::class,
        FormularioMasterEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MincabotsDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun formularioMasterDao(): FormularioMasterDao
}