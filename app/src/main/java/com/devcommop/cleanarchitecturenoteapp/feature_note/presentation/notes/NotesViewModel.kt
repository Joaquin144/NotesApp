package com.devcommop.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteUseCases: NoteUseCases): ViewModel() {

    /*
    Rather than just keeping LiveData we will also have a state that tells UI what to show it. Our NotesState will contain :--
    noteOrder, list of notes, order section ko collapse/expand karna
     */
}