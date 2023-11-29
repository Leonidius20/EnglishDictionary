package ua.leonidius.endict.entities.label_type

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LabelTypes")
data class LabelType(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "label_id") val labelId: Int,
    @ColumnInfo(name = "label_text") val labelText: String,
) {
    constructor(label: String) : this(0, label)
}
