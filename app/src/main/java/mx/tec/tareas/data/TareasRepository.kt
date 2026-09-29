package mx.tec.tareas.data

import mx.tec.tareas.domain.Tarea

/** El enchufe: quien lo usa no sabe de dónde vienen las tareas. */
interface TareasRepository {
    suspend fun obtenerTareas(): List<Tarea>
}

/** CFE: la fuente de verdad, que le pregunta a la API. */
class TareasRepositoryReal(
    private val api: ApiRemota
) : TareasRepository {
    override suspend fun obtenerTareas() = api.descargarTareas()
}

/** La planta de emergencia: una tarea fija, al instante y sin red. */
class TareasRepositoryFalso : TareasRepository {
    override suspend fun obtenerTareas() = listOf(Tarea("Tarea de prueba", "Pruebas", "Nunca"))
}