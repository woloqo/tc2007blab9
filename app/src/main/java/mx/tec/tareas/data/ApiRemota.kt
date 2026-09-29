package mx.tec.tareas.data

import kotlinx.coroutines.delay
import mx.tec.tareas.domain.Tarea
import javax.inject.Inject

/**
 * Un servidor de mentira: tarda 1.5 s y siempre responde lo mismo.
 * En una app real aquí iría Retrofit, como en la Práctica 4. Hoy no hace falta
 * internet: el tema es quién construye a quién, no la red.
 */
class ApiRemota @Inject constructor() {

    suspend fun descargarTareas(): List<Tarea> {
        delay(1500)
        return listOf(
            Tarea("Entregar la práctica 8", "Móviles", "Hoy"),
            Tarea("Estudiar para el parcial", "Cálculo", "Jueves"),
            Tarea("Revisar el pull request del equipo", "Reto", "Viernes"),
            Tarea("Leer el capítulo 3", "Ética", "Lunes")
        )
    }
}