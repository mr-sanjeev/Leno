package com.example.sanjeev.giet
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_homepage.*
import org.json.JSONArray
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

class CustomAdapter(var ctx:Context,var item :List<People>)
    :ArrayAdapter<People>(ctx,R.layout.pop_up_people_list,item) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(R.layout.pop_up_people_list, null)
        val people_name: TextView = view.findViewById(R.id.tv_people_name)
        val sms: Button = view.findViewById(R.id.btn_sms)
        val call: Button = view.findViewById(R.id.btn_call)
        val req: Button = view.findViewById(R.id.btn_request)
        val people: People = item[position]
        val rec_name = people.name
        people_name.setText(rec_name)
        val mob_num = people.number
        val sender_name_is=people.sender_name
        val subject_is=people.subject
        val timing_is=people.timing

        sms.setOnClickListener {
            backgroundTask_sms().execute(mob_num, rec_name,sender_name_is,subject_is,timing_is)
        }
        call.setOnClickListener {
            val i = Intent(Intent.ACTION_DIAL)
            i.data = Uri.parse("tel:$mob_num")
            ctx.startActivity(i)
        }
        req.setOnClickListener {
            val req_messge="Hello,$rec_name Professor $sender_name_is has requested you to take his $subject_is class from $timing_is."
           backgroundTask_request().execute(sender_name_is,rec_name,req_messge,subject_is,timing_is)
            req.text="Requested"

        }

        return view
    }

    private inner class backgroundTask_sms : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {
            val mobile_number_is = params[0]
            val reciever_name_is = params[1]
            val sender_name_is= params[2]
            val subject_is=params[3]
            val timing_is=params[4]
            try {
                // Construct data....
                val apiKey = "apikey=" + "fTqywfGxat4-lGvCBJNCW1O1n59tsAvVMWH5wZ4YZ3"//new api(15aei056.giet@gmail.com)
                val message = "&message=" + "Dear,$reciever_name_is\nProfessor $sender_name_is has requested you to take his $subject_is class,\nfrom $timing_is.\nHope you like our services,\nThank you.\nTeam MAVERICK"
                val sender = "&sender=" + "TXTLCL"
                val numbers = "&numbers=" + "8084200232"
                // Send data
                val conn = URL("https://api.textlocal.in/send/?").openConnection() as HttpURLConnection
                val data = apiKey + numbers + message + sender
                conn.setDoOutput(true)
                conn.setRequestMethod("POST")
                conn.setRequestProperty("Content-Length", Integer.toString(data.length))
                conn.getOutputStream().write(data.toByteArray())
                val rd = BufferedReader(InputStreamReader(conn.getInputStream()))
                var sb: String = ""
                var line: String = ""
                while (true) {
                    line = rd.readLine() ?: break
                    sb += line
                }
                rd.close();
                return sb
            } catch (e: Exception) {
                e.printStackTrace()
                return e.message.toString()
            }
        }
        override fun onPostExecute(result: String?) {
           Toast.makeText(context,"Thank You, SMS has been sent",Toast.LENGTH_LONG).show()
        }
    }

    private inner class backgroundTask_request :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/send_request.php"

        }
        override fun doInBackground(vararg params: String?): String {
            //date...
            val date=SimpleDateFormat("dd-MM-yyyy").format(Date())
            //time..
            val cal=Calendar.getInstance()
            val sdf=SimpleDateFormat("HH:mm:ss")
            val time= sdf.format(cal.time)

            val req_from=params[0]
            val req_to=params[1]
            val req_sms=params[2]
            val subject_is=params[3]
            val timing_is=params[4]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("reqfrom","UTF-8")+"="+URLEncoder.encode(req_from,"UTF-8")+"&"+
                        URLEncoder.encode("reqto","UTF-8")+"="+URLEncoder.encode(req_to,"UTF-8")+"&"+
                        URLEncoder.encode("reqsms","UTF-8")+"="+URLEncoder.encode(req_sms,"UTF-8")+"&"+
                        URLEncoder.encode("sub","UTF-8")+"="+URLEncoder.encode(subject_is,"UTF-8")+"&"+
                        URLEncoder.encode("timing","UTF-8")+"="+URLEncoder.encode(timing_is,"UTF-8")+"&"+
                        URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"+
                        URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")
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
            val code=jo.getString("code")
            if(code=="success")
            {
                val message=jo.getString("message")
                Toast.makeText(context,"$message",Toast.LENGTH_LONG).show()

            }
            else
            {
                Toast.makeText(context,"Sorry, Request sending failed!!!",Toast.LENGTH_LONG).show()
            }
        }
    }
}

