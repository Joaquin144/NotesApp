package com.devcommop.cleanarchitecturenoteapp.feature_note.domain.use_case

/**
 * As our app grows so does our total number of UseCases.
 *
 * We don't want all our ViwModels to have dozens of UseCases. So we wrap all the use cases into one single class and inject that class into all our ViewModels. Hence boilerplate code is reduced.
 */
data class NoteUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
)
