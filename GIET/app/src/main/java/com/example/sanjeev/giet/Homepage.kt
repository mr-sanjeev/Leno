package com.example.sanjeev.giet
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.widget.NestedScrollView
import android.view.*
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.layout_main.*
import kotlinx.android.synthetic.main.layout_secondary.*
import kotlinx.android.synthetic.main.user_rows.*
import org.json.JSONArray
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder
import java.sql.Time
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class Homepage : AppCompatActivity()
{
    lateinit var gestureDetector: GestureDetector
    var user_name_is:String=""
    var peoplelist= arrayListOf<People>()
    var senders_list= arrayListOf<Sender>()
    val notifications_list= arrayListOf<notify>()
    var subject_is:String=""
    var timing_is:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        gestureDetector= GestureDetector(this,MyGestures(applicationContext))
        val name: String = intent.getStringExtra("user_first_name")
        val seme: String = intent.getStringExtra("user_sem")
        val col_id: String = intent.getStringExtra("user_collage_id")
        tv_home.text = "Welcome " + name
        tv_login_details1.text = "Semester: $seme"
        tv_login_details2.text="Collage Id: $col_id"
        user_name_is = name

        btn_view_schedule.setOnClickListener {
            val i = Intent(applicationContext, Schedule::class.java)
            i.putExtra("user_id_detail", col_id)
            startActivity(i)
        }
        btn_load_current_schedule.setOnClickListener {
            btn_load_current_schedule.setBackgroundResource(R.drawable.preloadercube)
            backgroundTask0().execute(col_id)
            backgroundTask1().execute(col_id)
            backgroundTask2().execute(col_id)
            backgroundTask3().execute(col_id)
            backgroundTask4().execute(col_id)
            backgroundTask5().execute(col_id)
            val date=SimpleDateFormat("dd-MM-yyyy").format(Date())
            backgroundTask_delreq().execute(date)
        }

        btn_mon_1.setOnClickListener {
            subject_is = btn_mon_1.text.toString()
            timing_is = "9:10am to 10:10am"
            backgroundTask_NoneDetails().execute("m1")
        }
        btn_mon_2.setOnClickListener {
            subject_is = btn_mon_2.text.toString()
            timing_is = "10:10am to 11:10am"
            backgroundTask_NoneDetails().execute("m2")
        }
        btn_mon_3.setOnClickListener {
            subject_is = btn_mon_3.text.toString()
            timing_is = "11:30am to 12:30pm"
            backgroundTask_NoneDetails().execute("m3")
        }
        btn_mon_4.setOnClickListener {
            subject_is = btn_mon_4.text.toString()
            timing_is = "12:30am to 1:30pm"
            backgroundTask_NoneDetails().execute("m4")
        }

        btn_tue_1.setOnClickListener {
            subject_is = btn_tue_1.text.toString()
            timing_is = "9:10am to 10:10am"
            backgroundTask_NoneDetails().execute("t1")
        }
        btn_tue_2.setOnClickListener {
            subject_is = btn_tue_2.text.toString()
            timing_is = "10:10am to 11:10am"
            backgroundTask_NoneDetails().execute("t2")
        }
        btn_tue_3.setOnClickListener {
            subject_is = btn_tue_3.text.toString()
            timing_is = "11:30am to 12:30pm"
            backgroundTask_NoneDetails().execute("t3")
        }
        btn_tue_4.setOnClickListener {
            subject_is = btn_tue_4.text.toString()
            timing_is = "12:30am to 1:30pm"
            backgroundTask_NoneDetails().execute("t4")
        }

        btn_wed_1.setOnClickListener {
            subject_is = btn_wed_1.text.toString()
            timing_is = "9:10am to 10:10am"
            backgroundTask_NoneDetails().execute("w1")
        }
        btn_wed_2.setOnClickListener {
            subject_is = btn_wed_2.text.toString()
            timing_is = "10:10am to 11:10am"
            backgroundTask_NoneDetails().execute("w2")
        }
        btn_wed_3.setOnClickListener {
            subject_is = btn_wed_3.text.toString()
            timing_is = "11:30am to 12:30pm"
            backgroundTask_NoneDetails().execute("w3")
        }
        btn_wed_4.setOnClickListener {
            subject_is = btn_wed_4.text.toString()
            timing_is = "12:30am to 1:30pm"
            backgroundTask_NoneDetails().execute("w4")
        }

        btn_thu_1.setOnClickListener {
            subject_is = btn_thu_1.text.toString()
            timing_is = "9:10am to 10:10am"
            backgroundTask_NoneDetails().execute("th1")
        }
        btn_thu_2.setOnClickListener {
            subject_is = btn_thu_2.text.toString()
            timing_is = "10:10am to 11:10am"
            backgroundTask_NoneDetails().execute("th2")
        }
        btn_thu_3.setOnClickListener {
            subject_is = btn_thu_3.text.toString()
            timing_is = "11:30am to 12:30pm"
            backgroundTask_NoneDetails().execute("th3")
        }
        btn_thu_4.setOnClickListener {
            subject_is = btn_thu_4.text.toString()
            timing_is = "12:30am to 1:30pm"
            backgroundTask_NoneDetails().execute("th4")
        }

        btn_fri_1.setOnClickListener {
            subject_is = btn_fri_1.text.toString()
            timing_is = "9:10am to 10:10am"
            backgroundTask_NoneDetails().execute("f1")
        }
        btn_fri_2.setOnClickListener {
            subject_is = btn_fri_2.text.toString()
            timing_is = "10:10am to 11:10am"
            backgroundTask_NoneDetails().execute("f2")
        }
        btn_fri_3.setOnClickListener {
            subject_is = btn_fri_3.text.toString()
            timing_is = "11:30am to 12:30pm"
            backgroundTask_NoneDetails().execute("f3")
        }
        btn_fri_4.setOnClickListener {
            subject_is = btn_fri_4.text.toString()
            timing_is = "12:30am to 1:30pm"
            backgroundTask_NoneDetails().execute("f4")
        }

        btn_sat_1.setOnClickListener {
            subject_is = btn_sat_1.text.toString()
            timing_is = "9:10am to 10:10am"
            backgroundTask_NoneDetails().execute("s1")
        }
        btn_sat_2.setOnClickListener {
            subject_is = btn_sat_2.text.toString()
            timing_is = "10:10am to 11:10am"
            backgroundTask_NoneDetails().execute("s2")
        }
        btn_sat_3.setOnClickListener {
            subject_is = btn_sat_3.text.toString()
            timing_is = "11:30am to 12:30pm"
            backgroundTask_NoneDetails().execute("s3")
        }
        btn_sat_4.setOnClickListener {
            subject_is = btn_sat_4.text.toString()
            timing_is = "12:30am to 1:30pm"
            backgroundTask_NoneDetails().execute("s4")
        }

        //tab view...with view pager
       val adapter = myViewPagerAdapter(supportFragmentManager)
        adapter.addfragment(FragmentOne(), "See Requests")
        adapter.addfragment(FragmentTwo(), "Notification")
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)

       /* btn_load_req_and_notification.setOnClickListener {
            btn_load_req_and_notification.setBackgroundResource(R.drawable.loader)
            backgroundTask_see_requests().execute(user_name_is)
            backgroundTask_see_notification().execute(user_name_is)
        }*/

        /*btn_logout.setOnClickListener {
            finish()
            val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)
            val editor = mypref.edit()
            editor.clear()
            editor.apply()
            Toast.makeText(applicationContext, "You have logged out successfully", Toast.LENGTH_LONG).show()
            val i = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }*/

        //view_pager double touch scroll fix......
     /*  view_pager.setOnTouchListener { v, event ->
            val dt=30
            var downx:Float= 0.0F
            var downy:Float=0.0F
            when(event.action){

                MotionEvent.ACTION_DOWN->
                {
                    downx=event.rawX
                    downy=event.rawY
                }
                MotionEvent.ACTION_MOVE->
                {
                    var dx=Math.abs(event.rawX-downx)
                    var dy=Math.abs(event.rawY-downy)
                    if(dy>dx && dy>dt)
                    {
                        view_pager.parent.requestDisallowInterceptTouchEvent(true)

                    }
                    else if(dx>dy && dx>dt){
                        view_pager.parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
                MotionEvent.ACTION_UP->{
                    view_pager.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            return@setOnTouchListener false
        }*/


    }//oncreate



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
when(item?.itemId)
{
    R.id.action_logout->
    {
        finish()
        val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val editor = mypref.edit()
        editor.clear()
        editor.apply()
        Toast.makeText(applicationContext, "You have logged out successfully", Toast.LENGTH_LONG).show()
        val i = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }
    R.id.action_load_req_and_not->
    {
        backgroundTask_see_requests().execute(user_name_is)
        backgroundTask_see_notification().execute(user_name_is)
    }
}
        return true
    }

    private fun pop_up() {
        val builder= AlertDialog.Builder(this)
        val view=layoutInflater.inflate(R.layout.user_rows,null)
        val adapter= CustomAdapter(applicationContext,peoplelist)
        val lv:ListView=view.findViewById(R.id.lv_user_details)
        lv?.adapter=adapter
        builder.setView(view)
        builder.setCancelable(false)
        val dialog=builder.create()
        dialog.show()
        dialog.btn_close_user_rows.setOnClickListener { dialog.dismiss() }
    }

    private inner class backgroundTask0 :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/fetch_monday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val col_uid=params[0]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
               val post_data:String= URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(col_uid,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br=BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                ins.close()
                httpURLConnection.disconnect()
                return data
            }catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }

        override fun onPostExecute(result: String?) {
             val ja=JSONArray(result)
             val jo=ja.getJSONObject(0)
             val message=jo.getString("message")
             if(message=="Schedule loaded") {
                 btn_mon_1.text = jo.getString("mon-1")
                 btn_mon_2.text = jo.getString("mon-2")
                 btn_mon_3.text = jo.getString("mon-3")
                 btn_mon_4.text = jo.getString("mon-4")
                 Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
             }
             else
             {
                 Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
             }
        }
    }

    private inner class backgroundTask1 :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/fetch_tueday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val col_uid=params[0]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(col_uid,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br=BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                ins.close()
                httpURLConnection.disconnect()
                return data
            }catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }

        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
            if(message=="Schedule loaded") {
                btn_tue_1.text = jo.getString("tue-1")
                btn_tue_2.text = jo.getString("tue-2")
                btn_tue_3.text = jo.getString("tue-3")
                btn_tue_4.text = jo.getString("tue-4")
               // Toast.makeText(applicationContext,"Tuesday: $message",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
            }
        }
    }

    private inner class backgroundTask2 :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/fetch_wednesday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val col_uid=params[0]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(col_uid,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br=BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                ins.close()
                httpURLConnection.disconnect()
                return data
            }catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }

        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
            if(message=="Schedule loaded") {
                btn_wed_1.text = jo.getString("wed-1")
                btn_wed_2.text = jo.getString("wed-2")
                btn_wed_3.text = jo.getString("wed-3")
                btn_wed_4.text = jo.getString("wed-4")
              //  Toast.makeText(applicationContext,"Wednesday: $message",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
            }
        }
    }

    private inner class backgroundTask3 :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/fetch_thursday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val col_uid=params[0]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(col_uid,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br=BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                ins.close()
                httpURLConnection.disconnect()
                return data
            }catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
            if(message=="Schedule loaded") {
                btn_thu_1.text = jo.getString("thu-1")
                btn_thu_2.text = jo.getString("thu-2")
                btn_thu_3.text = jo.getString("thu-3")
                btn_thu_4.text = jo.getString("thu-4")
               // Toast.makeText(applicationContext,"Thursday: $message",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
            }
        }
    }

    private inner class backgroundTask4 :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/fetch_friday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val col_uid=params[0]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(col_uid,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br=BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                ins.close()
                httpURLConnection.disconnect()
                return data
            }catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
            if(message=="Schedule loaded") {
                btn_fri_1.text = jo.getString("fri-1")
                btn_fri_2.text = jo.getString("fri-2")
                btn_fri_3.text = jo.getString("fri-3")
                btn_fri_4.text = jo.getString("fri-4")
              //  Toast.makeText(applicationContext,"Friday: $message",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
            }
        }
    }

    private inner class backgroundTask5 :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/fetch_saturday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val col_uid=params[0]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(col_uid,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br=BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                ins.close()
                httpURLConnection.disconnect()
                return data
            }catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
            if(message=="Schedule loaded") {
                btn_sat_1.text = jo.getString("sat-1")
                btn_sat_2.text = jo.getString("sat-2")
                btn_sat_3.text = jo.getString("sat-3")
                btn_sat_4.text = jo.getString("sat-4")
                btn_load_current_schedule.setBackgroundResource(R.drawable.ic_refresh_black_24dp)
               // Toast.makeText(applicationContext,"Saturday: $message",Toast.LENGTH_LONG).show()
                btn_load_current_schedule.setBackgroundResource(R.drawable.ic_refresh_black_24dp)
            }
            else
            {
                Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
            }
        }
    }


    //final user details who are free to take classes....
    private inner class backgroundTask_NoneDetails :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/none_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val btnid=params[0]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("btnid","UTF-8")+"="+URLEncoder.encode(btnid,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br=BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                ins.close()
                httpURLConnection.disconnect()
                return data
            }catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {

            peoplelist.clear()
            var c:Int=0
            val ja=JSONArray(result)
            val len=ja.length()-1
            for(j in 0..len)
           {
                val jo = ja.getJSONObject(j)
                val message = jo.getString("message")
                if (message == "None-Data-fetched-success") {
                    val name: String = jo.getString("fn")
                    val mob_num:String=jo.getString("mn")
                    val user_id:String=jo.getString("None-Details-Id")
                    peoplelist.add(People(name,mob_num,user_name_is,subject_is,timing_is))
                } else {
                   Toast.makeText(applicationContext, "$message", Toast.LENGTH_LONG).show()
                    break
               }
           }
            pop_up()
        }
    }


    //see requests...
    private inner class backgroundTask_see_requests :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/see_request.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val first_name=params[0]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("fn","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br=BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                ins.close()
                httpURLConnection.disconnect()
                return data
            }catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            senders_list.clear()
            var sender_list:String=""
            val ja=JSONArray(result)
            val len:Int=ja.length()-1
            for(j in 0..len)
            {
                val jo=ja.getJSONObject(j)
                val message=jo.getString("message")
                if(message=="request recieved")
                {
                    val req_from=jo.getString("req-from")
                    val req_sms=jo.getString("req-sms")
                    senders_list.add(Sender(req_sms,req_from))

                }
                else
                {
                    Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
                    break
                }
            }

            val adapter= SenderCustomAdapterr(applicationContext,senders_list)
            val lv:ListView=findViewById(R.id.lv_see_requests)
            lv?.adapter=adapter

        }
    }

    class myViewPagerAdapter (manager: FragmentManager): FragmentPagerAdapter(manager)
    {
        private val fragmentList :MutableList<Fragment> = arrayListOf()
        private val titleList :MutableList<String> = arrayListOf()

        override fun getItem(p0: Int): Fragment {
            return fragmentList[p0]
        }
        override fun getCount(): Int {
            return fragmentList.size
        }


        fun addfragment(fragment: Fragment, title:String)
        {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }




    private inner class backgroundTask_see_notification :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/load_notifications.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val first_name=params[0]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("fn","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br=BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                ins.close()
                httpURLConnection.disconnect()
                return data
            }catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            notifications_list.clear()
            val ja=JSONArray(result)
            val len:Int=ja.length()-1
            for(j in 0..len)
            {
                val jo=ja.getJSONObject(j)
                val message=jo.getString("message")
                if(message=="Notifications loaded")
                {
                    val req_sms=jo.getString("req-sms")
                    val status:String=jo.getString("status")
                    notifications_list.add(notify(req_sms,status))
                }
                else
                {
                    Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
                    break
                }
            }

            //adapter....for load notificatuons....
            val adapter= Notification_CustomAdapter(applicationContext,notifications_list)
            val lv:ListView=findViewById(R.id.lv_notifications)
            lv?.adapter=adapter
        }
    }


//for deleting oold requests....

    private inner class backgroundTask_delreq :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/deletereq.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val date_is=params[0]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date_is,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br=BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                ins.close()
                httpURLConnection.disconnect()
                return data
            }catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {

            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
            //Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()

        }
    }






}

