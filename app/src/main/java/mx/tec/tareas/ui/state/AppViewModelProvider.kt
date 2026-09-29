package mx.tec.tareas.ui.state

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import mx.tec.tareas.MiApp

/** Cómo se construye cada ViewModel: con las piezas que le da el contenedor. */
object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer { TareasViewModel(miApp().container.tareasRepository) }
    }
}

/** El atajo para llegar al contenedor desde dentro de un initializer. */
private fun CreationExtras.miApp(): MiApp =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MiApp