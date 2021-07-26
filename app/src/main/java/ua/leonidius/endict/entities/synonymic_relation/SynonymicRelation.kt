package ua.leonidius.endict.entities.synonymic_relation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import ua.leonidius.endict.entities.definition.Definition
import ua.leonidius.endict.entities.word.Word

/**
 * Defines a synonymic word to a definition
 */
@Entity(
    primaryKeys = ["definition_id", "word_id"], tableName = "Synonyms",
    foreignKeys = [
        ForeignKey(entity = Definition::class, parentColumns = ["definition_id"], childColumns = ["definition_id"]),
        ForeignKey(entity = Word::class, parentColumns = ["word_id"], childColumns = ["word_id"])
    ]
)
data class SynonymicRelation(
    @ColumnInfo(name = "definition_id") val definitionId: Int,
    @ColumnInfo(name = "word_id") val wordId: Int,
)
