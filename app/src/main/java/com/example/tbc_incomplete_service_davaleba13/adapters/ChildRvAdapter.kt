package com.example.tbc_incomplete_service_davaleba13.adapters

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_incomplete_service_davaleba13.databinding.ChildRvChooserItemBinding
import com.example.tbc_incomplete_service_davaleba13.databinding.ChildRvInputItemBinding
import com.example.tbc_incomplete_service_davaleba13.models.Field

class ChildRvAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = listOf<Field>()
    private var fieldTexts = mutableMapOf<Int, String>()
    private var requiredFieldTexts = mutableMapOf<String, String>()

    fun setData(data: List<Field>) {
        this.data = data
    }

    companion object {
        const val INPUT = 1
        const val CHOOSER = 2
    }

    inner class ChildRvInputViewHolder(private val binding: ChildRvInputItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val field = data[adapterPosition]
            with(binding) {
                etField.id = field.id
                etField.hint = field.hint
                if (field.keyboard == Field.KEYBOARD.TEXT) {
                    etField.inputType = InputType.TYPE_CLASS_TEXT
                } else if (field.keyboard == Field.KEYBOARD.NUMBER) {
                    etField.inputType = InputType.TYPE_CLASS_NUMBER
                }
                fieldTexts[field.id] = ""
                etField.doOnTextChanged { text, _, _, _ ->
                    fieldTexts[field.id] = text.toString()
                }
                if (field.required) {
                    requiredFieldTexts[field.hint] = ""
                    etField.doOnTextChanged { text, _, _, _ ->
                        requiredFieldTexts[field.hint] = text.toString()
                    }
                }
            }
        }
    }

    inner class ChildRvChooserViewHolder(
        private val binding: ChildRvChooserItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val field = data[adapterPosition]
            with(binding) {
                radioGroupGender.setOnCheckedChangeListener { _, _ ->
                    if (maleRadioButton.isChecked) {
                        fieldTexts[field.id] = "Male"
                    } else if (femaleRadioButton.isChecked) {
                        fieldTexts[field.id] = "Female"
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == INPUT) {
            ChildRvInputViewHolder(
                ChildRvInputItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            ChildRvChooserViewHolder(
                ChildRvChooserItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].type == Field.TYPE.INPUT) {
            INPUT
        } else {
            CHOOSER
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChildRvInputViewHolder) {
            holder.bind()
        } else if (holder is ChildRvChooserViewHolder) {
            holder.bind()
        }
    }

    fun getRequiredFields(): Map<String, String> = requiredFieldTexts

    fun getAllFields(): Map<Int, String> = fieldTexts
}