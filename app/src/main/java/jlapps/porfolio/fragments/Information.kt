package jlapps.porfolio.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.fromHtml
import androidx.core.view.marginStart
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import jlapps.porfolio.R
import kotlinx.android.synthetic.main.fragment_information.*

class Information : Fragment(R.layout.fragment_information) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(arguments?.containsKey("header") == true){
            (view.context as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (view.context as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

            txt_title.text = arguments?.getString("header")

            if(arguments?.containsKey("titles") == true) {
                val titles = arguments?.get("titles") as ArrayList<String>

                if(arguments?.containsKey("subtitles") == true) {
                    val subtitles = arguments?.get("subtitles") as ArrayList<String>

                    setupLayouts(view, titles, subtitles)
                }
            }

        }
    }
    private fun setupLayouts(view: View, titles:ArrayList<String>, subtitles: ArrayList<String> = ArrayList()){
        val linearLayout = LinearLayout(view.context)
        linearLayout.orientation = LinearLayout.VERTICAL

        addDataToLayouts(view,linearLayout,titles, subtitles)

        var constraintLayout = constraint_information

        linearLayout.id = View.generateViewId()
        constraintLayout.addView(linearLayout)

        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.connect(linearLayout.id, ConstraintSet.TOP,txt_title.id, ConstraintSet.BOTTOM,50)
        constraintSet.connect(linearLayout.id, ConstraintSet.START,ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.applyTo(constraintLayout)
    }

    private fun addDataToLayouts(view: View, linearLayout:LinearLayout, titles:ArrayList<String>, subtitles:ArrayList<String>){
        var lastIndex = 0
        for(title in titles)
        {
            val txtView = TextView(view.context)
            setHeaderText(view,title,txtView)

            linearLayout.addView(txtView)
            val param = txtView.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(20,10,10,10)

            if(subtitles.isNotEmpty()) {
                for (index in lastIndex until subtitles.size) {
                    lastIndex = index
                    if (subtitles[index] == "***") {
                        lastIndex++
                        break
                    }
                    setSubHeaderText(view,linearLayout,subtitles[index])
                }
            }
        }
    }
    private fun setHeaderText(view:View, title:String, txtView:TextView){
        txtView.text = title
        txtView.textSize = 20.0f
        txtView.setTypeface(null, Typeface.BOLD)
        txtView.setTextColor(ContextCompat.getColor(view.context,R.color.white))
        txtView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun setSubHeaderText(view:View, linearLayout: LinearLayout,title:String){

        val txtSubView = TextView(view.context)
        if(title.contains("-*link*-")) {
            txtSubView.movementMethod = LinkMovementMethod.getInstance()
            txtSubView.isClickable = true
            txtSubView.autoLinkMask = 1
            txtSubView.text = fromHtml(title.substring(8),FROM_HTML_MODE_COMPACT)
        }else
            txtSubView.text = title
        txtSubView.setTextColor(ContextCompat.getColor(view.context, R.color.white))
        linearLayout.addView(txtSubView)
        val params = txtSubView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(60, 30, 30, 15)
    }
}