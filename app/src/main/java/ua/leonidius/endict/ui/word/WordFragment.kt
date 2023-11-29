package ua.leonidius.endict.ui.word

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.leonidius.endict.R


class WordFragment : Fragment(), TextToSpeech.OnInitListener {

    private val args: WordFragmentArgs by navArgs()

    companion object {
        fun newInstance() = WordFragment()
    }

    private lateinit var viewModel: WordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.word_fragment, container, false)

        initTts()

        val definitionsList: RecyclerView = root.findViewById(R.id.definitions_list)

        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        viewModel.wordObject.observe(viewLifecycleOwner) {
            viewModel.loadDefinitions(it.wordId)
        }

        viewModel.wordExists.observe(viewLifecycleOwner) {
            if (!it)
                Toast.makeText(context, "No such word", Toast.LENGTH_SHORT).show()
        }

        viewModel.wordDefinitions.observe(viewLifecycleOwner) {
            val adapter = DefinitionAdapter(it, context!!, viewModel.wordObject.value!!.word)
            definitionsList.adapter = adapter
            definitionsList.layoutManager = LinearLayoutManager(context)
        }

        viewModel.loadWord(args.word)

        return root
    }

    private fun initTts() {

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                doSomeOperations()
            }
        }

        val intent = Intent()
        resultLauncher.launch(intent)
    }

    override fun onInit(status: Int) {
        TODO("Not yet implemented")
    }


}