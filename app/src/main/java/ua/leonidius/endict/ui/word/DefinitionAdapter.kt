package ua.leonidius.endict.ui.word

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.leonidius.endict.MainActivity
import ua.leonidius.endict.R
import ua.leonidius.endict.entities.definition.Definition
import ua.leonidius.endict.entities.definition.DefinitionWithDetails

class DefinitionAdapter(
    private val definitions: Array<DefinitionWithDetails>,
    val context: Context,
    val word: String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_TYPE_HEADER = 0
        private const val ITEM_TYPE_DEFINITION = 1
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val definitionTextView: TextView = itemView.findViewById(R.id.definition_text)
        val partOfSpeechTextView: TextView = itemView.findViewById(R.id.part_of_speech_text)
        val usageExamplesTextView: TextView = itemView.findViewById(R.id.usage_examples)
        val labelsContainer: LinearLayout = itemView.findViewById(R.id.labels_container)
        val synonymsTextView: TextView = itemView.findViewById(R.id.synonyms)

    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val wordTextView: TextView = itemView.findViewById(R.id.word_text_view)
        val ttsButton: ImageButton = itemView.findViewById(R.id.tts_button)

    }

    override fun getItemCount() = definitions.size + 1 // + header

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ITEM_TYPE_HEADER else ITEM_TYPE_DEFINITION
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                if (viewType == ITEM_TYPE_HEADER) R.layout.word_card
                else R.layout.definition_card,
                parent, false)
        return if (viewType == ITEM_TYPE_HEADER) HeaderViewHolder(view) else ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            val holder = viewHolder as HeaderViewHolder
            holder.wordTextView.text = word
            holder.ttsButton.setOnClickListener {

            }
        } else {
            val holder = viewHolder as ViewHolder
            val definition = definitions[position - 1]
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

            if (definition.synonyms.isNotEmpty()) {
                holder.synonymsTextView.text = String.format(
                    context.resources.getString(R.string.def_card_synonyms),
                    definition.synonyms.joinToString(", ") { it.word })
            }
        }
    }

}