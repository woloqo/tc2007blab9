package mx.tec.tareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import mx.tec.tareas.ui.screens.PantallaTareas
import mx.tec.tareas.ui.theme.TareasTheme

/** Pieza 2: esta Activity puede recibir dependencias de Hilt. */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TareasTheme { PantallaTareas() }
        }
    }
}