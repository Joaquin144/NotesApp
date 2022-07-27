package com.devcommop.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.model.Note

/**
 * Jo humne dao banaya hai uska implementation humko iske thorugh access hoga jab RoomDb bana dega
 */
@Database(
    entities = [Note::class],//Here we declare all our tables that we have || E! --> Why not class.java ?
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao//Baki kaam RoomDB apne aap kar dega. RoomDB banane walon ko mera dil se pranam _/\_

    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}