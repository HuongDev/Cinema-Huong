package com.huong.appcinema

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by HuongPN on 10/15/2018.
 */
class SpinnerAdapter(private var listData : List<String>,
                     private var activity : Activity,
                     private var inflater: LayoutInflater) : BaseAdapter() {


    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View, p2: ViewGroup?): View {
        var view : View = p1
        view = inflater.inflate(R.layout.spinner_item, null)
        val tvGenre : TextView = view.findViewById(R.id.tvGenre)
        tvGenre.setText(listData.get(p0))
        return view
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return listData.size
    }



}