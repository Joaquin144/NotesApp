package com.devcommop.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteUseCases: NoteUseCases): ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeletedNote: Note? = null

    /*
    Rather than just keeping LiveData we will also have a state that tells UI what to show it. Our NotesState will contain :--
    noteOrder, list of notes, order section ko collapse/expand karna
     */

    /**
     * Trigger this funciton from the UI to send events to it.
     *
     * In traditional MVVM, UI had to detect Clicks etc. and had to call appropriate functions of ViewModel and for getting data we used 2 LiveData objects. But in Clean Architecture pattern UI only has to detect Clicks etc. and sends appropriate events to viewmodel. Then VM catches those events and calls appropriate functions and for getting data we use 2 State Objects.
     */
    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {

            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {

                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

}