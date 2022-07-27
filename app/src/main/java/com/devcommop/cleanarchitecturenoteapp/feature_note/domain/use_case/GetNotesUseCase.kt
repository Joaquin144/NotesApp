package com.devcommop.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A use case should basically have one public function. We may name that function as execeute.
 *
 * And we can also use invoke function which helps us calling the class itself as a function. Both uphold clean architecture principles.
 *
 * In this class we are using invoke
 */
class GetNotesUseCase(private val repository: NoteRepository) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>>{
        return repository.getNotes().map { notes ->
            when(noteOrder.orderType){
                is OrderType.Ascending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> {
                            notes.sortedBy { note -> note.title.lowercase() }
                        }
                        is NoteOrder.Date -> {
                            notes.sortedBy { note -> note.timeStamp }
                        }
                        is NoteOrder.Color -> {
                            notes.sortedBy { note -> note.color }
                        }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> {
                            notes.sortedByDescending { note -> note.title.lowercase() }
                        }
                        is NoteOrder.Date -> {
                            notes.sortedByDescending { note -> note.timeStamp }
                        }
                        is NoteOrder.Color -> {
                            notes.sortedByDescending { note -> note.color }
                        }
                    }
                }
            }
        }
    }
}