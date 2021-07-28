package ua.leonidius.endict.ui.word

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.leonidius.endict.R
import ua.leonidius.endict.entities.definition.Definition
import ua.leonidius.endict.entities.definition.DefinitionWithDetails

class DefinitionAdapter(private val definitions: Array<DefinitionWithDetails>) : RecyclerView.Adapter<DefinitionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val definitionTextView: TextView = itemView.findViewById(R.id.definition_text)
        val partOfSpeechTextView: TextView = itemView.findViewById(R.id.part_of_speech_text)
        val usageExamplesTextView: TextView = itemView.findViewById(R.id.usage_examples)

    }

    override fun getItemCount() = definitions.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.definition_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val definition = definitions[position]
        holder.definitionTextView.text = definition.definition.definitionText
        holder.partOfSpeechTextView.text = definition.partOfSpeech.name
        if (definition.usageExamples.isNotEmpty()) {
            holder.usageExamplesTextView.text = definition.usageExamples
                .joinToString("\n") { "\"" + it.exampleSentence + "\"" }
        } else {
            holder.usageExamplesTextView.visibility = View.GONE
            // TODO: fix this because it doesn't work
        }
    }

}