package ua.leonidius.endict.entities.word

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Words", indices = [
    Index(value = ["word"], unique = true)
])
data class Word (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "word_id", collate = ColumnInfo.NOCASE)
    val wordId: Int,

    val word: String

)
