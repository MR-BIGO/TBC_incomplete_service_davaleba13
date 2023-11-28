package com.example.tbc_incomplete_service_davaleba13.adapters

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_incomplete_service_davaleba13.databinding.ChildRvFieldItemBinding
import com.example.tbc_incomplete_service_davaleba13.models.Field

class ChildRvAdapter : RecyclerView.Adapter<ChildRvAdapter.ChildRvViewHolder>() {

    private var data = listOf<Field>()

    var editTextContent = mutableMapOf<String, String>()

    fun setData(data: List<Field>) {
        this.data = data
        for (i in data){
            if(i.required){
                editTextContent[i.hint] = ""
            }
        }
    }

    inner class ChildRvViewHolder(private val binding: ChildRvFieldItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            with(binding) {
                etField.id = data[adapterPosition].id
                etField.hint = data[adapterPosition].hint
                data[adapterPosition].keyboard.let {
                    if (it == "text") {
                        etField.inputType = InputType.TYPE_CLASS_TEXT
                    } else if (it == "number") {
                        etField.inputType = InputType.TYPE_CLASS_NUMBER
                    }
                }
                if (data[adapterPosition].required) {
                    etField.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                        override fun afterTextChanged(p0: Editable?) {
                            editTextContent[data[adapterPosition].hint] = p0.toString()
                        }
                    })
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildRvViewHolder {
        return ChildRvViewHolder(
            ChildRvFieldItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ChildRvViewHolder, position: Int) {
        holder.bind()
    }
}