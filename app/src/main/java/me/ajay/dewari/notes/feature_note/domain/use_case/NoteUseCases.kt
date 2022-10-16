package me.ajay.dewari.notes.feature_note.domain.use_case


data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val getNote: GetNote
)
