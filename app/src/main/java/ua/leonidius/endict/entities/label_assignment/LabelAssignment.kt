package ua.leonidius.endict.entities.label_assignment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import ua.leonidius.endict.entities.definition.Definition
import ua.leonidius.endict.entities.label_type.LabelType

/**
 * Defines a relationship between a definition and labels assigned to it
 */
@Entity(
    tableName = "Labels", primaryKeys = ["definition_id", "label_type_id"],
    foreignKeys = [
        ForeignKey(entity = Definition::class, parentColumns = ["definition_id"], childColumns = ["definition_id"]),
        ForeignKey(entity = LabelType::class, parentColumns = ["label_id"], childColumns = ["label_type_id"])
    ]
)
data class LabelAssignment(
    @ColumnInfo(name = "definition_id") val definitionId: Int,
    @ColumnInfo(name = "label_type_id") val labelId: Int,
)
