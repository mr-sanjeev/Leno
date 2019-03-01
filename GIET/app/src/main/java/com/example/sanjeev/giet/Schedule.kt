package com.example.sanjeev.giet
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.activity_schedule.*
import org.json.JSONArray
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class Schedule : AppCompatActivity() {

    var mon1:String=""
    var mon2:String=""
    var mon3:String=""
    var mon4:String=""
    var tue1:String=""
    var tue2:String=""
    var tue3:String=""
    var tue4:String=""
    var wed1:String=""
    var wed2:String=""
    var wed3:String=""
    var wed4:String=""
    var thu1:String=""
    var thu2:String=""
    var thu3:String=""
    var thu4:String=""
    var fri1:String=""
    var fri2:String=""
    var fri3:String=""
    var fri4:String=""
    var sat1:String=""
    var sat2:String=""
    var sat3:String=""
    var sat4:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        schedule_preloader.setBackgroundResource(0)

        val options= arrayOf("NONE","IOT","DSP","SC","MEMS","ES","NT","AEC")
        sp_mon_1.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_mon_2.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_mon_3.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_mon_4.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        sp_tue_1.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_tue_2.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_tue_3.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_tue_4.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        sp_wed_1.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_wed_2.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_wed_3.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_wed_4.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        sp_thu_1.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_thu_2.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_thu_3.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_thu_4.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        sp_fri_1.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_fri_2.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_fri_3.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_fri_4.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        sp_sat_1.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_sat_2.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_sat_3.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        sp_sat_4.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        sp_mon_1.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
              mon1=options.get(position)
            }
        }
        sp_mon_2.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 mon2=options.get(position)
            }
        }
        sp_mon_3.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mon3=options.get(position)
            }
        }
        sp_mon_4.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mon4=options.get(position)
            }
        }

        sp_tue_1.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tue1=options.get(position)
            }
        }
        sp_tue_2.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tue2=options.get(position)
            }
        }
        sp_tue_3.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tue3=options.get(position)
            }
        }
        sp_tue_4.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tue4=options.get(position)
            }
        }

        sp_wed_1.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                wed1=options.get(position)
            }
        }
        sp_wed_2.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                wed2=options.get(position)
            }
        }
        sp_wed_3.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                wed3=options.get(position)
            }
        }
        sp_wed_4.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                wed4=options.get(position)
            }
        }

        sp_thu_1.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                thu1=options.get(position)
            }
        }
        sp_thu_2.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                thu2=options.get(position)
            }
        }
        sp_thu_3.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                thu3=options.get(position)
            }
        }
        sp_thu_4.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               thu4=options.get(position)
            }
        }


        sp_fri_1.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                fri1=options.get(position)
            }
        }
        sp_fri_2.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                fri2=options.get(position)
            }
        }
        sp_fri_3.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                fri3=options.get(position)
            }
        }
        sp_fri_4.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 fri4=options.get(position)
            }
        }

        sp_sat_1.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sat1=options.get(position)
            }
        }
        sp_sat_2.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sat2=options.get(position)
            }
        }
        sp_sat_3.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sat3=options.get(position)
            }
        }
        sp_sat_4.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               sat4=options.get(position)
            }
        }

       val u_id:String=intent.getStringExtra("user_id_detail")
        btn_upload_schedule.setOnClickListener {
            schedule_preloader.setBackgroundResource(R.drawable.loader)
            backgroundTask0().execute(u_id,mon1,mon2,mon3,mon4)
            backgroundTask1().execute(u_id,tue1,tue2,tue3,tue4)
            backgroundTask2().execute(u_id,wed1,wed2,wed3,wed4)
            backgroundTask3().execute(u_id,thu1,thu2,thu3,thu4)
            backgroundTask4().execute(u_id,fri1,fri2,fri3,fri4)
            backgroundTask5().execute(u_id,sat1,sat2,sat3,sat4)

        }
    }
    private inner class backgroundTask0 :AsyncTask<String,String,String>()
    {
        var submit_url:String=""
        override fun onPreExecute() {
            submit_url="https://pasko.000webhostapp.com/tt_monday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val user_id=params[0]
            val mon_1=params[1]
            val mon_2=params[2]
            val mon_3=params[3]
            val mon_4=params[4]
            try {
                val url = URL(submit_url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doOutput = true
                httpURLConnection.doInput = true
                val os = httpURLConnection.outputStream
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                val post_data: String =
                    URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&" +
                            URLEncoder.encode("mon1", "UTF-8") + "=" + URLEncoder.encode(mon_1, "UTF-8") + "&" +
                            URLEncoder.encode("mon2", "UTF-8") + "=" + URLEncoder.encode(mon_2, "UTF-8") + "&" +
                            URLEncoder.encode("mon3", "UTF-8") + "=" + URLEncoder.encode(mon_3, "UTF-8") + "&" +
                            URLEncoder.encode("mon4", "UTF-8") + "=" + URLEncoder.encode(mon_4, "UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var line: String = ""
                var data: String = ""
                val ins = httpURLConnection.inputStream
                val br = BufferedReader(InputStreamReader(ins, "ISO-8859-1"))
                while (true) {
                    line = br.readLine() ?: break
                    data += line
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
            catch (e: IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
            //Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
        }
    }

    private inner class backgroundTask1 :AsyncTask<String,String,String>()
    {
        var submit_url:String=""
        override fun onPreExecute() {
            submit_url="https://pasko.000webhostapp.com/tt_tuesday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val user_id=params[0]
            val tue_1=params[1]
            val tue_2=params[2]
            val tue_3=params[3]
            val tue_4=params[4]
            try {
                val url = URL(submit_url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doOutput = true
                httpURLConnection.doInput = true
                val os = httpURLConnection.outputStream
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                val post_data: String =
                    URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&" +
                            URLEncoder.encode("tue1", "UTF-8") + "=" + URLEncoder.encode(tue_1, "UTF-8") + "&" +
                            URLEncoder.encode("tue2", "UTF-8") + "=" + URLEncoder.encode(tue_2, "UTF-8") + "&" +
                            URLEncoder.encode("tue3", "UTF-8") + "=" + URLEncoder.encode(tue_3, "UTF-8") + "&" +
                            URLEncoder.encode("tue4", "UTF-8") + "=" + URLEncoder.encode(tue_4, "UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var line: String = ""
                var data: String = ""
                val ins = httpURLConnection.inputStream
                val br = BufferedReader(InputStreamReader(ins, "ISO-8859-1"))
                while (true) {
                    line = br.readLine() ?: break
                    data += line
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
            catch (e: IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
          //  Toast.makeText(applicationContext,"tuesday"+message,Toast.LENGTH_LONG).show()
        }
    }

    private inner class backgroundTask2 :AsyncTask<String,String,String>()
    {
        var submit_url:String=""
        override fun onPreExecute() {
            submit_url="https://pasko.000webhostapp.com/tt_wednesday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val user_id=params[0]
            val wed_1=params[1]
            val wed_2=params[2]
            val wed_3=params[3]
            val wed_4=params[4]
            try {
                val url = URL(submit_url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doOutput = true
                httpURLConnection.doInput = true
                val os = httpURLConnection.outputStream
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                val post_data: String =
                    URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&" +
                            URLEncoder.encode("wed1", "UTF-8") + "=" + URLEncoder.encode(wed_1, "UTF-8") + "&" +
                            URLEncoder.encode("wed2", "UTF-8") + "=" + URLEncoder.encode(wed_2, "UTF-8") + "&" +
                            URLEncoder.encode("wed3", "UTF-8") + "=" + URLEncoder.encode(wed_3, "UTF-8") + "&" +
                            URLEncoder.encode("wed4", "UTF-8") + "=" + URLEncoder.encode(wed_4, "UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var line: String = ""
                var data: String = ""
                val ins = httpURLConnection.inputStream
                val br = BufferedReader(InputStreamReader(ins, "ISO-8859-1"))
                while (true) {
                    line = br.readLine() ?: break
                    data += line
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
            catch (e: IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
           // Toast.makeText(applicationContext,"wednesday:"+message,Toast.LENGTH_LONG).show()
        }
    }

    private inner class backgroundTask3 :AsyncTask<String,String,String>()
    {
        var submit_url:String=""
        override fun onPreExecute() {
            submit_url="https://pasko.000webhostapp.com/tt_thursday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val user_id=params[0]
            val thu_1=params[1]
            val thu_2=params[2]
            val thu_3=params[3]
            val thu_4=params[4]
            try {
                val url = URL(submit_url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doOutput = true
                httpURLConnection.doInput = true
                val os = httpURLConnection.outputStream
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                val post_data: String =
                    URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&" +
                            URLEncoder.encode("thu1", "UTF-8") + "=" + URLEncoder.encode(thu_1, "UTF-8") + "&" +
                            URLEncoder.encode("thu2", "UTF-8") + "=" + URLEncoder.encode(thu_2, "UTF-8") + "&" +
                            URLEncoder.encode("thu3", "UTF-8") + "=" + URLEncoder.encode(thu_3, "UTF-8") + "&" +
                            URLEncoder.encode("thu4", "UTF-8") + "=" + URLEncoder.encode(thu_4, "UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var line: String = ""
                var data: String = ""
                val ins = httpURLConnection.inputStream
                val br = BufferedReader(InputStreamReader(ins, "ISO-8859-1"))
                while (true) {
                    line = br.readLine() ?: break
                    data += line
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
            catch (e: IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
           // Toast.makeText(applicationContext,"thursday:"+message,Toast.LENGTH_LONG).show()
        }
    }

    private inner class backgroundTask4 :AsyncTask<String,String,String>()
    {
        var submit_url:String=""
        override fun onPreExecute() {
            submit_url="https://pasko.000webhostapp.com/tt_friday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val user_id=params[0]
            val fri_1=params[1]
            val fri_2=params[2]
            val fri_3=params[3]
            val fri_4=params[4]
            try {
                val url = URL(submit_url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doOutput = true
                httpURLConnection.doInput = true
                val os = httpURLConnection.outputStream
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                val post_data: String =
                    URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&" +
                            URLEncoder.encode("fri1", "UTF-8") + "=" + URLEncoder.encode(fri_1, "UTF-8") + "&" +
                            URLEncoder.encode("fri2", "UTF-8") + "=" + URLEncoder.encode(fri_2, "UTF-8") + "&" +
                            URLEncoder.encode("fri3", "UTF-8") + "=" + URLEncoder.encode(fri_3, "UTF-8") + "&" +
                            URLEncoder.encode("fri4", "UTF-8") + "=" + URLEncoder.encode(fri_4, "UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var line: String = ""
                var data: String = ""
                val ins = httpURLConnection.inputStream
                val br = BufferedReader(InputStreamReader(ins, "ISO-8859-1"))
                while (true) {
                    line = br.readLine() ?: break
                    data += line
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
            catch (e: IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
         //   Toast.makeText(applicationContext,"Friday:"+message,Toast.LENGTH_LONG).show()
        }
    }
    private inner class backgroundTask5 :AsyncTask<String,String,String>()
    {
        var submit_url:String=""
        override fun onPreExecute() {
            submit_url="https://pasko.000webhostapp.com/tt_saturday_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val user_id=params[0]
            val sat_1=params[1]
            val sat_2=params[2]
            val sat_3=params[3]
            val sat_4=params[4]
            try {
                val url = URL(submit_url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doOutput = true
                httpURLConnection.doInput = true
                val os = httpURLConnection.outputStream
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                val post_data: String =
                    URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&" +
                            URLEncoder.encode("sat1", "UTF-8") + "=" + URLEncoder.encode(sat_1, "UTF-8") + "&" +
                            URLEncoder.encode("sat2", "UTF-8") + "=" + URLEncoder.encode(sat_2, "UTF-8") + "&" +
                            URLEncoder.encode("sat3", "UTF-8") + "=" + URLEncoder.encode(sat_3, "UTF-8") + "&" +
                            URLEncoder.encode("sat4", "UTF-8") + "=" + URLEncoder.encode(sat_4, "UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var line: String = ""
                var data: String = ""
                val ins = httpURLConnection.inputStream
                val br = BufferedReader(InputStreamReader(ins, "ISO-8859-1"))
                while (true) {
                    line = br.readLine() ?: break
                    data += line
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
            catch (e: IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            val ja=JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
            btn_upload_schedule.text="Uploaded"
            Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
            schedule_preloader.setBackgroundResource(0)
        }
    }
}
