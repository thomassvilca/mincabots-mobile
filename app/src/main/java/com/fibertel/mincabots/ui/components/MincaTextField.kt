package com.fibertel.mincabots.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.platform.LocalDensity

@Composable
fun MincaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    isError: Boolean = false,
    supportingText: String? = null,

    // password mode (opcional)
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordToggle: (() -> Unit)? = null,

    // UX micro-feedback (opcional)
    limitExceeded: Boolean = false,
) {
    val cs = MaterialTheme.colorScheme
    val ty = MaterialTheme.typography

    // --- Shake (rebote sutil) ---
    val shake = Animatable(0f)
    val density = LocalDensity.current

    LaunchedEffect(limitExceeded) {
        if (limitExceeded) {
            // 3 "golpecitos" suaves: derecha-izquierda-centro
            shake.snapTo(0f)
            shake.animateTo(1f, tween(60))
            shake.animateTo(-1f, tween(90))
            shake.animateTo(0f, tween(90))
        }
    }

    val shakeDp: Dp = with(density) { (shake.value * 4f).dp } // amplitud ~4dp

    // Si se excede el límite, forzamos borde rojo incluso sin isError
    val showLimitError = limitExceeded && !isError
    val unfocusedBorder = if (showLimitError) cs.error else cs.outline
    val focusedBorder = if (showLimitError) cs.error else cs.primary

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .offset(x = shakeDp),
        shape = RoundedCornerShape(10.dp),
        singleLine = singleLine,
        textStyle = ty.bodyLarge.copy(color = cs.onSurfaceVariant),
        isError = isError || showLimitError,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = cs.surface,
            focusedContainerColor = cs.surface,
            unfocusedBorderColor = unfocusedBorder,
            focusedBorderColor = focusedBorder,
            cursorColor = if (isError || showLimitError) cs.error else cs.primary,
            focusedTextColor = cs.onSurfaceVariant,
            unfocusedTextColor = cs.onSurfaceVariant,
            focusedLabelColor = if (isError || showLimitError) cs.error else cs.primary,
            unfocusedLabelColor = cs.onSurfaceVariant,
            errorBorderColor = cs.error,
            errorCursorColor = cs.error,
            errorLabelColor = cs.error
        ),
        label = { Text(label) },
        placeholder = { Text(placeholder, color = cs.outline) },
        leadingIcon = leadingIcon?.let {
            { Icon(imageVector = it, contentDescription = null, tint = cs.onSurfaceVariant) }
        },
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { onPasswordToggle?.invoke() }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Mostrar contraseña",
                        tint = cs.onSurfaceVariant
                    )
                }
            }
        } else null,
        visualTransformation = if (isPassword && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        supportingText = supportingText?.let {
            { Text(text = it, style = ty.labelMedium) }
        }
    )
}
