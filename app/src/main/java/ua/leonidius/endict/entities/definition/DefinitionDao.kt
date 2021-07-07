package ua.leonidius.endict.entities.definition

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DefinitionDao {

    @Query("SELECT * FROM Definitions WHERE word_id = :wordId")
    suspend fun getDefinitionsForWord(wordId: Int): Array<Definition>

}