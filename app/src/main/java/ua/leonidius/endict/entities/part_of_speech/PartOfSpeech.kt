package ua.leonidius.endict.entities.part_of_speech

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PartsOfSpeech")
data class PartOfSpeech(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "part_of_speech_id") val id: Int,
    @ColumnInfo(name = "name") val name: String,

)