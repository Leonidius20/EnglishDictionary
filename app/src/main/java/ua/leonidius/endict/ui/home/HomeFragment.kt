package ua.leonidius.endict.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.GlobalScope
import ua.leonidius.endict.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val searchBox: EditText = root.findViewById(R.id.search_box)
        searchBox.addTextChangedListener {
            GlobalScope.run {
                homeViewModel.refreshSuggestions(it.toString())
            }
        }

        homeViewModel.suggestions.observe(viewLifecycleOwner) {
            textView.text = it.joinToString()
        }

        val button: Button = root.findViewById(R.id.submit_button)
        button.setOnClickListener {
            goToWordDefinition(searchBox.text.toString())
        }

        return root
    }

    private fun goToWordDefinition(word: String) {
        val action = HomeFragmentDirections.homeToWordFragment(word)
        findNavController().navigate(action)
    }

}