package mx.tec.tareas

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
/** Vive tanto como el proceso. Declarada en el manifiesto con `android:name`. */
class MiApp : Application() {
}