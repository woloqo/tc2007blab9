package mx.tec.tareas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import mx.tec.tareas.domain.Tarea
import mx.tec.tareas.ui.components.TarjetaTarea
import mx.tec.tareas.ui.state.TareasViewModel
import mx.tec.tareas.ui.theme.TareasTema
import mx.tec.tareas.ui.theme.TareasTheme

/** Con estado: le pide a Hilt el ViewModel ya armado, con todo lo que necesita. */
@Composable
fun PantallaTareas(vm: TareasViewModel = hiltViewModel()) {
    ListaTareas(
        tareas = vm.tareas,
        cargando = vm.cargando,
        onRecargar = { vm.cargar() }
    )
}

/** Sin estado: dibuja lo que recibe. No sabe que existe el ViewModel. */
@Composable
fun ListaTareas(
    tareas: List<Tarea>,
    cargando: Boolean,
    onRecargar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val espaciado = TareasTema.espaciado

    Scaffold(
        modifier = modifier,
        topBar = { Encabezado(pendientes = tareas.size, cargando = cargando, onRecargar = onRecargar) }
    ) { padding ->
        if (cargando) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding),
                contentPadding = PaddingValues(horizontal = espaciado.lg, vertical = espaciado.sm),
                verticalArrangement = Arrangement.spacedBy(espaciado.md)
            ) {
                items(tareas) { tarea -> TarjetaTarea(tarea) }
            }
        }
    }
}

@Composable
private fun Encabezado(pendientes: Int, cargando: Boolean, onRecargar: () -> Unit) {
    val espaciado = TareasTema.espaciado

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(start = espaciado.lg, end = espaciado.lg, top = espaciado.xl, bottom = espaciado.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text("Mis tareas", style = MaterialTheme.typography.headlineLarge)
            Text(
                text = when {
                    cargando -> "Cargando…"
                    pendientes == 1 -> "1 pendiente"
                    else -> "$pendientes pendientes"
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        FilledTonalIconButton(onClick = onRecargar, enabled = !cargando) {
            Icon(Icons.Default.Refresh, contentDescription = "Recargar")
        }
    }
}

// La preview usa ListaTareas, no PantallaTareas: así no necesita ViewModel.
@Preview(showBackground = true, name = "Claro")
@Composable
private fun ListaTareasPreview() {
    TareasTheme(oscuro = false) {
        ListaTareas(
            tareas = listOf(
                Tarea("Entregar la práctica 8", "Móviles", "Hoy"),
                Tarea("Estudiar para el parcial", "Cálculo", "Jueves")
            ),
            cargando = false,
            onRecargar = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1512, name = "Oscuro")
@Composable
private fun ListaTareasOscuroPreview() {
    TareasTheme(oscuro = true) {
        ListaTareas(
            tareas = listOf(Tarea("Tarea de prueba", "Pruebas", "Nunca")),
            cargando = false,
            onRecargar = {}
        )
    }
}
