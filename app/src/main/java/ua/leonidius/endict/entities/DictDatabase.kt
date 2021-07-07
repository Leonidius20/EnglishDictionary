package ua.leonidius.endict.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.leonidius.endict.entities.definition.Definition
import ua.leonidius.endict.entities.definition.DefinitionDao
import ua.leonidius.endict.entities.part_of_speech.PartOfSpeech
import ua.leonidius.endict.entities.word.Word
import ua.leonidius.endict.entities.word.WordDao

@Database(entities = [Word::class, Definition::class, PartOfSpeech::class], version = 1)
abstract class DictDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    abstract fun definitionDao(): DefinitionDao

}
