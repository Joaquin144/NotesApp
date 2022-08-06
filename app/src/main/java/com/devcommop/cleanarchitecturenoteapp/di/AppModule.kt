package com.devcommop.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.devcommop.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.devcommop.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.use_case.AddNoteUseCase
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNotesUseCase
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)//E! --> why not class.java
object AppModule {

    @Provides
    @Singleton
    //We need context to have RoomDb so to get context we use ApplicationContext. To get ApplicationContext we pass Application in constructor of this fucntion. This Application is automatically provided by Hilt. This app varibale is provided automatically by Hilt and don't confuse that activity/fragment is providing it.
    fun provideNoteDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app.applicationContext,
            NoteDatabase::class.java,//E! --> how to know what to use: X::class , X::class.java or X() ?
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    //rather than injecting all UseCases one by one, We wrap all those use cases into a data class and then inject this data class into all our ViewModels.
    fun providesNoteUseCase(repository: NoteRepository): NoteUseCases{
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository = repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository = repository),
            addNoteUseCase = AddNoteUseCase(repository = repository)
        )
    }

}