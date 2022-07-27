package com.devcommop.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteUseCases: NoteUseCases): ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeletedNote: Note? = null
    private var getNotesJob: Job? = null //purane wale flow/corotuine ko isme store karenge phir jab naya flow ayega tab isko cancel kar dnege

    init {
        getNotes(noteOrder = NoteOrder.Date(OrderType.Descending))
    }

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
                //Why are we using class --> Because to check if 2 events[classes] are equal == won't works it just checks for reference/address equality. So we suffix ::class to check if event ki classes are same or not.
                //Meanwhile orderType ko == se check kar sakte hain kyuki woh object hai aur object ko == contents check ote hain in kotlin
                if(state.value.noteOrder::class == event.noteOrder::class && state.value.noteOrder.orderType == event.noteOrder.orderType){
                    return //Do nothing beacuse same user has selected current events again only
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotesUseCase(noteOrder).onEach { notes ->
            _state.value = state.value.copy(
                notes = notes,
                noteOrder = noteOrder
            )
        }
            .launchIn(viewModelScope)
    }

}