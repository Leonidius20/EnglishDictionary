package ua.leonidius.endict.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.leonidius.endict.MainActivity
import ua.leonidius.endict.entities.word.Word

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val suggestions = MutableLiveData<Array<String>>().apply {
        value = emptyArray()
    }

    fun refreshSuggestions(searchTerm: String) {
        viewModelScope.launch(Dispatchers.IO) {
            suggestions.postValue(MainActivity.instance.db.wordDao().getSearchSuggestions(searchTerm))
        }
    }

}