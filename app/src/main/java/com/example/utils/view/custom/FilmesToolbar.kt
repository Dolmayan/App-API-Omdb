package com.example.utils.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.lista_de_filmes_oficial.R

open class FilmesToolbar: FrameLayout {

    private val toolbarTitle by lazy { view.findViewById<TextView>(R.id.txt_toolbar_title) }
    private val backImage by lazy { view.findViewById<ImageView>(R.id.img_back) }

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs:AttributeSet) : super(context, attrs){
        init()
    }

    constructor(context: Context, attrs:AttributeSet, defStyleAttrs:Int) : super(context, attrs, defStyleAttrs){
        init()
    }

    private lateinit var view:View

    open fun getLayout() = R.layout.toolbar_filmes

    private fun init(){
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(getLayout(),null)
        addView(view)
    }

    fun setTitle(title:String){
        toolbarTitle.text = title
    }

    fun setBackPress(show:Boolean){
        if (show) backImage.visibility = View.VISIBLE
        else backImage.visibility = View.GONE

        backImage.setOnClickListener {

        }
    }
}