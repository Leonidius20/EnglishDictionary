package ua.leonidius.endict.entities.definition

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DefinitionDao {

    @Transaction
    @Query("SELECT * FROM Definitions WHERE word_id = :wordId")
    suspend fun getDefinitionsForWord(wordId: Int): Array<DefinitionWithDetails>

}