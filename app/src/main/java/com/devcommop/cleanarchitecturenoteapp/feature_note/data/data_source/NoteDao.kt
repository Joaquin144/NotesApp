package com.devcommop.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.room.*
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?//E! Can we do Flow<Note> here ? if it takes 2 sconds for room to find that particular note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

}