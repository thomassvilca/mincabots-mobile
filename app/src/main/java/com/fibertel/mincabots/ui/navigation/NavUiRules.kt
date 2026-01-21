package com.fibertel.mincabots.ui.navigation

enum class TopBarType { HOME, APP }

object NavUiRules {

    fun topBarType(route: String): TopBarType {
        return when (route) {
            Routes.HOME_TECNICO, Routes.HOME_SUPERVISOR -> TopBarType.HOME
            else -> TopBarType.APP
        }
    }

    fun showBottomBar(route: String): Boolean {
        return when (route) {
            Routes.HOME_TECNICO,
            Routes.HOME_SUPERVISOR,
            Routes.FORMS_LIST,
            Routes.HISTORY,
            Routes.VALIDATE_INBOX -> true
            else -> false
        }
    }

    fun titleFor(route: String): String {
        return when (route) {
            Routes.FORMS_LIST -> "Mis Formularios"
            Routes.ATS_GENERAL -> "ATS"
            Routes.IPERC_GENERAL -> "IPERC"
            Routes.PETS_GENERAL -> "PETS"
            Routes.PROFILE -> "Perfil"
            Routes.HISTORY -> "Historial"
            Routes.VALIDATE_INBOX -> "Validar"
            else -> ""
        }
    }
}
