package com.fibertel.mincabots.data.local.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fibertel.mincabots.core.security.PasswordHasher
import com.fibertel.mincabots.data.local.entity.UsuarioEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseProvider {

    @Volatile private var INSTANCE: MincabotsDatabase? = null

    fun get(context: Context): MincabotsDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MincabotsDatabase::class.java,
                "mincabots_db"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        CoroutineScope(Dispatchers.IO).launch {
                            val database = INSTANCE ?: return@launch
                            val dao = database.usuarioDao()

                            dao.insert(
                                UsuarioEntity(
                                    idUsuario = "U001",
                                    dni = "77777777",
                                    passwordHash = PasswordHasher.sha256("123456"),
                                    nombres = "Técnico Demo",
                                    rol = "TECNICO",
                                    estado = true
                                )
                            )

                            dao.insert(
                                UsuarioEntity(
                                    idUsuario = "U002",
                                    dni = "88888888",
                                    passwordHash = PasswordHasher.sha256("123456"),
                                    nombres = "Supervisor Demo",
                                    rol = "SUPERVISOR",
                                    estado = true
                                )
                            )
                        }
                    }
                })
                .build()

            INSTANCE = instance
            instance
        }
    }
}
