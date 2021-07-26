package ua.leonidius.endict.entities.usage_example

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ua.leonidius.endict.entities.definition.Definition

@Entity(tableName = "UsageExamples", foreignKeys = [
    ForeignKey(entity = Definition::class,
        parentColumns = ["definition_id"], childColumns = ["definition_id"])
])
data class UsageExample(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "usage_example_id") val id: Int,
    @ColumnInfo(name = "definition_id") val definitionId: Int,
    @ColumnInfo(name = "example_sentence") val exampleSentence: String

)
