package ua.leonidius.endict.ui.word

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.leonidius.endict.R

class WordFragment : Fragment() {

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

        val wordTextField: TextView = root.findViewById(R.id.word_text_view)
        val definitionsList: RecyclerView = root.findViewById(R.id.definitions_list)

        wordTextField.text = args.word

        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        viewModel.wordObject.observe(viewLifecycleOwner) {
            Toast.makeText(context, it!!.word, Toast.LENGTH_SHORT).show()
            viewModel.loadDefinitions(it.wordId)
        }

        viewModel.wordExists.observe(viewLifecycleOwner) {
            if (!it)
                Toast.makeText(context, "No such word", Toast.LENGTH_SHORT).show()
        }

        viewModel.wordDefinitions.observe(viewLifecycleOwner) {
            val adapter = DefinitionAdapter(it, context!!)
            definitionsList.adapter = adapter
            definitionsList.layoutManager = LinearLayoutManager(context)

            // textField.text = textField.text as String + "" + it.map { definition -> definition.definitionText }.joinToString()
        }

        viewModel.loadWord(args.word)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}