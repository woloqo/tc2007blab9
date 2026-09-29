package mx.tec.tareas.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.tec.tareas.data.TareasRepository
import mx.tec.tareas.data.TareasRepositoryReal

/**
 * Hilt no puede adivinar qué implementación va en una interfaz. Este módulo se
 * lo dice: "cuando alguien pida TareasRepository, dale TareasRepositoryReal".
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositorioModule {

    @Binds
    abstract fun bindTareasRepo(
        impl: TareasRepositoryReal
    ): TareasRepository
}