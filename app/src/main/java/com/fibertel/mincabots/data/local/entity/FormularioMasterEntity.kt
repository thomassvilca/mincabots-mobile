package com.fibertel.mincabots.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "formularios_master")
data class FormularioMasterEntity(
    @PrimaryKey
    val idFormulario: String,          // id_formulario
    val idUsuarioCreador: String,      // FK Usuario
    val tipoFormulario: String,        // ATS | IPERC | PETS
    val fechaCreacion: Long,
    val estado: String,                // BORRADOR | ENVIADO | VALIDADO
    val proyectoArea: String?,
    val gpsLatitud: Double?,
    val gpsLongitud: Double?
)
