package ua.leonidius.endict.entities.definition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import ua.leonidius.endict.entities.part_of_speech.PartOfSpeech
import ua.leonidius.endict.entities.word.Word

@Entity(tableName = "Definitions", foreignKeys = [
    ForeignKey(entity = Word::class,
        parentColumns = arrayOf("word_id"),
        childColumns = arrayOf("word_id"),
        onDelete = CASCADE, onUpdate = CASCADE),
    ForeignKey(entity = PartOfSpeech::class,
        parentColumns = arrayOf("part_of_speech_id"),
        childColumns = arrayOf("part_of_speech_id"))
])
data class Definition (

    @PrimaryKey @ColumnInfo(name = "definition_id") val definitionId: Int?,
    @ColumnInfo(name = "part_of_speech_id") val partOfSpeechId: Int?,
    @ColumnInfo(name = "word_id") val wordId: Int,
    @ColumnInfo(name = "definition_text") val definitionText: String,
    @ColumnInfo(name = "image_url") val imageUrl: String?,

    )