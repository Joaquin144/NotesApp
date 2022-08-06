package com.devcommop.cleanarchitecturenoteapp.feature_note.presentation.notes

import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.devcommop.cleanarchitecturenoteapp.feature_note.domain.util.OrderType

/**
 * Why we need a State class' object inside VM ? --> We need it to describe the UI. if UI state was simple we could have had just 1-2 varibales in VM. But here UI state is complex it has 6 states making 6 variables for them is hectic so we just have an object that wraps all those 6 variables.
 *
 * Don't confuse with: State is not lifecycle aware or process death
 */
data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
