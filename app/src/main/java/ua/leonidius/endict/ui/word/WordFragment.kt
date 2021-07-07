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
import ua.leonidius.endict.R

class WordFragment : Fragment() {

    val args: WordFragmentArgs by navArgs()

    companion object {
        fun newInstance() = WordFragment()
    }

    private lateinit var viewModel: WordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.word_fragment, container, false)

        val textField: TextView = root.findViewById(R.id.temptext)
        textField.text = args.word

        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        viewModel.wordObject.observe(viewLifecycleOwner) {
            Toast.makeText(context, it!!.word, Toast.LENGTH_SHORT).show()
            viewModel.loadDefinitions(it.wordId!!)
        }

        viewModel.wordExists.observe(viewLifecycleOwner) {
            if (!it)
                Toast.makeText(context, "No such word", Toast.LENGTH_SHORT).show()
        }

        viewModel.wordDefinitions.observe(viewLifecycleOwner) {
            textField.text = textField.text as String + "" + it.map { definition -> definition.definitionText }.joinToString()
        }

        viewModel.loadWord(args.word)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}