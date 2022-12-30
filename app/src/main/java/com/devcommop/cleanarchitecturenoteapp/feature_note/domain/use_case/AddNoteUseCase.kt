package com.devcommop.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import kotlin.Throws

class AddNoteUseCase(private val repository: NoteRepository) {

    /**
     * Sabse pehli baat toh ye ki agar tum bina title/content ko isEmpty() check kiye note insert kar rahe ho toh tum chutiya ho
     *
     * Ab doosri baat ye ki, empty check karna is our business logic. So of course it should lie in use case and not viewmodel. viewmodel mei kiya toh bhi tum chutiya ho
     */
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){//Ab viemodel ko batao ki title blank bheja hai user ne; par billi ke gale me ghnati kon bandhe 🐈‍🛎️ . Toh ab uske liye bhot se tareeke hain. Jo hum use karnege woh -> ek exception class bana lenge
            throw InvalidNoteException("The title of note can't be empty.")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The content of note can't be empty.")
        }
        repository.insertNote(note = note)
    }

}