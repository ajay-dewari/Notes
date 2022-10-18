package me.ajay.dewari.notes.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.ajay.dewari.notes.feature_note.domain.use_case.NoteUseCases
import javax.inject.Inject

@HiltViewModel
class NotesViewModel constructor(@Inject val noteUseCases: NoteUseCases) : ViewModel() {

}