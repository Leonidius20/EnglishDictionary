package ua.leonidius.endict.entities.definition

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ua.leonidius.endict.entities.label_assignment.LabelAssignment
import ua.leonidius.endict.entities.label_type.LabelType
import ua.leonidius.endict.entities.part_of_speech.PartOfSpeech
import ua.leonidius.endict.entities.synonymic_relation.SynonymicRelation
import ua.leonidius.endict.entities.usage_example.UsageExample
import ua.leonidius.endict.entities.word.Word

data class DefinitionWithDetails(
    @Embedded val definition: Definition,

    @Relation(parentColumn = "part_of_speech_id", entityColumn = "part_of_speech_id")
    val partOfSpeech: PartOfSpeech,

    @Relation(parentColumn = "definition_id", entityColumn = "definition_id")
    val usageExamples: List<UsageExample>,

    @Relation(parentColumn = "definition_id", entityColumn = "label_id",
        associateBy = Junction(LabelAssignment::class, parentColumn = "definition_id", entityColumn = "label_type_id"))
    val labels: List<LabelType>,

    @Relation(parentColumn = "definition_id", entityColumn = "word_id",
        associateBy = Junction(SynonymicRelation::class))
    val synonyms: List<Word>,

)
