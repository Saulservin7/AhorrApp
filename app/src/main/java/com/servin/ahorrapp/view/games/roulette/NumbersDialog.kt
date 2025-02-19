package com.servin.ahorrapp.view.games.roulette

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.servin.ahorrapp.viewmodel.RouletteViewModel

@Composable
fun NumberGridDialog(
    initial: Int,
    final: Int,
    usedNumbers: String,  // Recibir los números usados
    onDismiss: () -> Unit
) {
    val numberRange = remember(initial, final) {
        (initial..final).toList()
    }

    // Convertir el string a lista de números
    val usedNumbersList = remember(usedNumbers) {
        if (usedNumbers.isEmpty()) {
            emptyList()
        } else {
            // Paso 1: Eliminar comas al inicio/final
            val trimmedNumbers = usedNumbers.trim { it == ',' }
            // Paso 2: Dividir y filtrar
            trimmedNumbers.split(",")
                .filter { it.isNotBlank() }
                .mapNotNull { it.toIntOrNull() }
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Tablero numérico",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 64.dp),
                    modifier = Modifier.heightIn(max = 500.dp)
                ) {
                    items(numberRange) { number ->
                        NumberCell(
                            number = number,
                            isUsed = usedNumbersList.contains(number)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NumberCell(number: Int, isUsed: Boolean) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .background(
                color = if (isUsed) MaterialTheme.colorScheme.secondaryContainer
                else MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.titleMedium,
            color = if (isUsed) MaterialTheme.colorScheme.onSecondaryContainer
            else MaterialTheme.colorScheme.onPrimaryContainer,
            textDecoration = if (isUsed) TextDecoration.LineThrough
            else TextDecoration.None
        )
    }
}