package me.ajay.dewari.notes.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.ajay.dewari.notes.feature_note.domain.model.Note
import me.ajay.dewari.notes.feature_note.domain.use_case.NoteUseCases
import me.ajay.dewari.notes.feature_note.domain.util.NoteOrder
import javax.inject.Inject

@HiltViewModel
class NotesViewModel constructor(@Inject val noteUseCases: NoteUseCases) : ViewModel() {

    val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    var lastDeletedNote: Note? = null
    var getNotesJob: Job? = null

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (event.noteOrder::class == state.value.noteOrder::class
                    && event.noteOrder.orderType == state.value.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    lastDeletedNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(lastDeletedNote ?: return@launch)
                    lastDeletedNote = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

}