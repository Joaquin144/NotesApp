package com.devcommop.cleanarchitecturenoteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devcommop.cleanarchitecturenoteapp.feature_note.presentation.ui.theme.*

/**
 * E! [check] => Should domain layer contain annotations ? Beacuse clean architecture pricnciple gets violated that domain is only abstraction and data layer is its implementation
 */
@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null,//what is null id? What happens if we allow/don't allow auto-generate
){
    companion object{
        val noteColors = listOf(RedOrange, LightGreen, LightBlue, Violet, BabyBlue, RedPink)
    }
}

/**
 * Shaandaar tareeka khud ki exception banane ka. Truly OP 😁
 */
class InvalidNoteException(message: String): Exception()//(message = message)
