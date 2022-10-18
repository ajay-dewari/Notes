package me.ajay.dewari.notes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.ajay.dewari.notes.ui.theme.BabyBlue
import me.ajay.dewari.notes.ui.theme.LightGreen
import me.ajay.dewari.notes.ui.theme.RedOrange
import me.ajay.dewari.notes.ui.theme.RedPink
import me.ajay.dewari.notes.ui.theme.Violet

@Entity
data class Note(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)