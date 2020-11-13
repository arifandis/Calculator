package com.cahstudio.calculator.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cahstudio.calculator.R
import com.cahstudio.calculator.model.Input
import kotlinx.android.synthetic.main.item_input.view.*

class InputAdapter(val context: Context, val inputList: List<Input>, val add: (Input) -> Unit
                   ,val delete: (Input) -> Unit):
    RecyclerView.Adapter<InputAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_input, parent, false))
    }

    override fun getItemCount(): Int = inputList.size

    override fun onBindViewHolder(holder: InputAdapter.ViewHolder, position: Int) {
        val input = inputList[position]

        holder.cb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (holder.etInput.text.isEmpty()){
                holder.cb.isChecked = false
                Toast.makeText(context, "Berikan input nilai terlebih dahulu", Toast.LENGTH_SHORT).show()
            }else{
                input.input = holder.etInput.text.toString().toFloat()
                input.isCheck = isChecked

                inputList[position].input = input.input
                inputList[position].isCheck = input.isCheck

                if (isChecked){
                    add(input)
                }else{
                    delete(input)
                }
            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val etInput = view.iteminput_et
        val cb = view.iteminput_cb
    }
}