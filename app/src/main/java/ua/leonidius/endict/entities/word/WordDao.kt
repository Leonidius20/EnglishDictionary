package ua.leonidius.endict.entities.word

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WordDao {

    @Query("SELECT * FROM Words WHERE word_id = :id")
    suspend fun findById(id: Int): Word

    @Query("SELECT * FROM Words WHERE word = :word COLLATE NOCASE")
    suspend fun find(word: String): Word?

    @Query("SELECT word FROM Words WHERE word LIKE :searchTerm || '%' ORDER BY word ASC LIMIT 5")
    suspend fun getSearchSuggestions(searchTerm: String): Array<String>

}
