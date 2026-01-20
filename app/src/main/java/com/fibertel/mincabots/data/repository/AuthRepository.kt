package com.fibertel.mincabots.data.repository

import com.fibertel.mincabots.core.security.PasswordHasher
import com.fibertel.mincabots.data.local.dao.UsuarioDao
import com.fibertel.mincabots.data.local.entity.UsuarioEntity

class AuthRepository(private val usuarioDao: UsuarioDao) {
    suspend fun login(dni: String, passwordPlain: String): UsuarioEntity? {
        val hash = PasswordHasher.sha256(passwordPlain)
        return usuarioDao.login(dni = dni, passwordHash = hash)
    }
}
