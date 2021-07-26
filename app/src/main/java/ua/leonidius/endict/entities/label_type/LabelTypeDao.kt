package ua.leonidius.endict.entities.label_type

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface LabelTypeDao {

    @Insert
    suspend fun addLabelTypes(vararg l : LabelType)

}