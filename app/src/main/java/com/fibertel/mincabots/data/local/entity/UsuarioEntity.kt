package com.fibertel.mincabots.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey
    val idUsuario: String,      // id_usuario
    val dni: String,
    val passwordHash: String,
    val nombres: String,
    val rol: String,            // TECNICO | SUPERVISOR
    val estado: Boolean
)
