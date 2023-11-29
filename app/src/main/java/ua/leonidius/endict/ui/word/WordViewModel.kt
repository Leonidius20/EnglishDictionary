package ua.leonidius.endict.ui.word

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.leonidius.endict.MainActivity
import ua.leonidius.endict.entities.definition.Definition
import ua.leonidius.endict.entities.definition.DefinitionWithDetails
import ua.leonidius.endict.entities.word.Word

class WordViewModel : ViewModel() {

    val wordObject = MutableLiveData<Word>()
    val wordExists = MutableLiveData<Boolean>()
    val wordDefinitions = MutableLiveData<Array<DefinitionWithDetails>>()

    fun loadWord(word: String) {
        viewModelScope.launch {
            val wordObj = MainActivity.instance.db.wordDao().find(word)
            if (wordObj == null) {
                wordExists.postValue(false)
            } else {
                wordObject.postValue(wordObj)
            }
        }


    }

    fun loadDefinitions(wordId: Int) {
        viewModelScope.launch {
            wordDefinitions.postValue(MainActivity.instance.db.definitionDao().getDefinitionsForWord(wordId))
        }
    }

}