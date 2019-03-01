package com.example.sanjeev.giet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView

class Notification_CustomAdapter(var ctx:Context,var item:List<notify>):
ArrayAdapter<notify>(ctx,R.layout.load_notification_details,item){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(R.layout.load_notification_details, null)
        val notification_textview=view.findViewById<TextView>(R.id.tv_load_noti)
        val status_button=view.findViewById<Button>(R.id.btn_load_noti)
        val notify:notify=item[position]
        notification_textview.text=notify.req_sms
        status_button.text=notify.flag
        return view
    }

}

