package com.cahstudio.calculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahstudio.calculator.R
import com.cahstudio.calculator.model.Input
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mInputList = mutableListOf<Input>()
    private var mCheckedInputList = mutableListOf<Input>()

    private lateinit var mAdapter: InputAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    fun initialize(){
        mInputList.add(Input())
        mInputList.add(Input())
        mInputList.add(Input())

        val layoutManager = LinearLayoutManager(this)
        mAdapter = InputAdapter(this, mInputList, {input -> addCheckedInput(input) }
            , {input -> deleteCheckedInput(input) })
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = mAdapter

        plus.setOnClickListener(this)
        minus.setOnClickListener(this)
        times.setOnClickListener(this)
        divide.setOnClickListener(this)
    }

    fun addCheckedInput(input: Input){
        mCheckedInputList.add(input)
        mAdapter.notifyDataSetChanged()
    }

    fun deleteCheckedInput(input: Input){
        mCheckedInputList.remove(input)
        mAdapter.notifyDataSetChanged()
    }

    fun getResult(id: Int?){
        if (mCheckedInputList.isEmpty() || mCheckedInputList.size == 1){
            Toast.makeText(this, "Minimal input yang di checklist adalah 2", Toast.LENGTH_SHORT).show()
        }else{
            var result = 0f
            var index = 0
            when(id){
                R.id.plus -> {
                    mCheckedInputList.forEach {
                        if (index == 0){
                            result = it.input
                        }else{
                            result += it.input
                        }
                        index++
                    }
                }
                R.id.minus -> {
                    mCheckedInputList.forEach {
                        if (index == 0){
                            result = it.input
                        }else{
                            result -= it.input
                        }
                        index++
                    }
                }
                R.id.times -> {
                    mCheckedInputList.forEach {
                        if (index == 0){
                            result = it.input
                        }else{
                            result *= it.input
                        }
                        index++
                    }
                }
                R.id.divide -> {
                    mCheckedInputList.forEach {
                        if (index == 0){
                            result = it.input
                        }else{
                            result /= it.input
                        }
                        index++
                    }
                }
            }

            tvResult.text = result.toString()
        }
    }

    override fun onClick(v: View?) {
        getResult(v?.id)
    }
}