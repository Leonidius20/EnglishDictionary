package ua.leonidius.endict.entities.word

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Words")
data class Word (

    @PrimaryKey @ColumnInfo(name = "word_id") val wordId: Int?,
    val word: String

)
