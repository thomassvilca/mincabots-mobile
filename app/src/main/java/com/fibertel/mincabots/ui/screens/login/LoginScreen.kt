package com.fibertel.mincabots.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fibertel.mincabots.ui.components.MincaErrorBanner
import com.fibertel.mincabots.ui.components.MincaPrimaryButton
import com.fibertel.mincabots.ui.components.MincaTextField
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onDniChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val cs = MaterialTheme.colorScheme
    val ty = MaterialTheme.typography

    var passwordVisible by remember { mutableStateOf(false) }

    var dniLimitExceeded by remember { mutableStateOf(false) }

    LaunchedEffect(dniLimitExceeded) {
        if (dniLimitExceeded) {
            delay(200)
            dniLimitExceeded = false
        }
    }

    val dniFiltered = uiState.dni.filter(Char::isDigit).take(8)
    val canSubmit = dniFiltered.length == 8 && uiState.password.isNotBlank() && !uiState.isLoading

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(cs.background)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(12.dp),
                color = cs.primary.copy(alpha = 0.10f)
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = cs.primary,
                    modifier = Modifier.padding(12.dp)
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "MINCABOTS",
                style = ty.headlineMedium,
                color = cs.onBackground
            )

            Text(
                text = "Ingreso de personal",
                style = ty.bodyLarge,
                color = cs.onSurfaceVariant
            )

            Spacer(Modifier.height(32.dp))

            MincaTextField(
                value = uiState.dni,
                onValueChange = { raw ->
                    val digits = raw.filter(Char::isDigit)

                    if (digits.length > 8) {
                        dniLimitExceeded = true
                        onDniChange(digits.take(8))
                    } else {
                        dniLimitExceeded = false
                        onDniChange(digits)
                    }
                },
                label = "DNI",
                placeholder = "8 dígitos",
                leadingIcon = Icons.Outlined.Person,
                limitExceeded = dniLimitExceeded,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            Spacer(Modifier.height(16.dp))

            MincaTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                label = "Contraseña",
                placeholder = "••••••",
                leadingIcon = Icons.Outlined.Lock,
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordToggle = { passwordVisible = !passwordVisible },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        if (canSubmit) onLogin()
                    }
                )
            )

            uiState.error?.let {
                Spacer(Modifier.height(16.dp))
                MincaErrorBanner(it)
            }

            Spacer(Modifier.height(24.dp))

            MincaPrimaryButton(
                text = "Ingresar",
                isLoading = uiState.isLoading,
                enabled = canSubmit,
                onClick = {
                    focusManager.clearFocus()
                    if (canSubmit) onLogin()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Modo Offline",
                style = ty.labelMedium,
                color = cs.onSurfaceVariant.copy(alpha = 0.8f)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "v1.0.0 (Build 2405)",
                style = ty.labelSmall,
                color = cs.onSurfaceVariant.copy(alpha = 0.5f)
            )
        }
    }
}
