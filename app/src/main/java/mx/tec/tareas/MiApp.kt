package mx.tec.tareas

import android.app.Application
import mx.tec.tareas.data.ApiRemota
import mx.tec.tareas.data.TareasRepository
import mx.tec.tareas.data.TareasRepositoryReal

/** Quién construye a quién, en un solo lugar. Como el de las prácticas 5 a 8. */
class AppContainer {
    private val api = ApiRemota()
    val tareasRepository: TareasRepository = TareasRepositoryReal(api)
}

/** Vive tanto como el proceso. Declarada en el manifiesto con `android:name`. */
class MiApp : Application() {
    val container = AppContainer()
}