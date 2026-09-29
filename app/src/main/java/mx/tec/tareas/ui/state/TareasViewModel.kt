package mx.tec.tareas.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.tec.tareas.data.TareasRepository
import mx.tec.tareas.domain.Tarea

class TareasViewModel(
    private val repo: TareasRepository
) : ViewModel() {

    var tareas by mutableStateOf<List<Tarea>>(emptyList())
        private set

    var cargando by mutableStateOf(false)
        private set

    init { cargar() }

    fun cargar() = viewModelScope.launch {
        cargando = true
        tareas = repo.obtenerTareas()
        cargando = false
    }
}