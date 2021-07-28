package ua.leonidius.endict.ui.word

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.leonidius.endict.MainActivity
import ua.leonidius.endict.R
import ua.leonidius.endict.entities.definition.Definition
import ua.leonidius.endict.entities.definition.DefinitionWithDetails

class DefinitionAdapter(private val definitions: Array<DefinitionWithDetails>,
                        val context: Context) : RecyclerView.Adapter<DefinitionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val definitionTextView: TextView = itemView.findViewById(R.id.definition_text)
        val partOfSpeechTextView: TextView = itemView.findViewById(R.id.part_of_speech_text)
        val usageExamplesTextView: TextView = itemView.findViewById(R.id.usage_examples)
        val labelsContainer: LinearLayout = itemView.findViewById(R.id.labels_container)

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

        holder.labelsContainer.removeAllViews()
        definition.labels.forEach {
            val view = TextView(ContextThemeWrapper(context, R.style.WordLabel))
            view.text = it.labelText
            holder.labelsContainer.addView(view)
        }
    }

}