package ua.leonidius.endict.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.leonidius.endict.entities.word.Word
import ua.leonidius.endict.entities.word.WordDao

@Database(entities = [Word::class], version = 1)
abstract class DictDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

}
