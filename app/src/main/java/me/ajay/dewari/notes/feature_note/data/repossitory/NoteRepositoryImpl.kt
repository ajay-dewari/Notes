package me.ajay.dewari.notes.feature_note.data.repossitory

import kotlinx.coroutines.flow.Flow
import me.ajay.dewari.notes.feature_note.data.data_source.NoteDao
import me.ajay.dewari.notes.feature_note.domain.model.Note
import me.ajay.dewari.notes.feature_note.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}