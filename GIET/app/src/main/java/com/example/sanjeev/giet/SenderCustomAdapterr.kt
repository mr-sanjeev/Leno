package com.example.sanjeev.giet

import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_homepage.*
import org.json.JSONArray
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

class SenderCustomAdapterr(var ctx:Context,var item:List<Sender>) :
ArrayAdapter<Sender>(ctx,R.layout.pop_up_people_list,item)
{
    var status:String=""
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(R.layout.see_requests, null)
        val r_sms=view.findViewById<TextView>(R.id.tv_see_requests)
        val accept=view.findViewById<Button>(R.id.btn_accept)
        val reject=view.findViewById<Button>(R.id.btn_reject)
        val sender:Sender=item[position]
        val req_sms=sender.req_sms
        val req_from=sender.req_from
        r_sms.setText(req_sms)

        accept.setOnClickListener {
            status="accepted"
            background_accept().execute(req_from,req_sms,status)
            reject.isEnabled=false
        }

        reject.setOnClickListener {
            status="rejected"
            val date=SimpleDateFormat("dd-MM-yyyy").format(Date())
            val cal=Calendar.getInstance()
            val sdf=SimpleDateFormat("hh:mm:ss")
            val time=sdf.format(cal.time)
           // Toast.makeText(ctx,"$req_from , $req_sms , $status, $date ,$time",Toast.LENGTH_LONG).show()

            background_reject().execute(req_from,req_sms,status,date,time)
            accept.isEnabled=false
        }
        return view
    }

    private inner class background_accept :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/notifications_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val date=SimpleDateFormat("dd-MM-yyyy").format(Date())
            val cal=Calendar.getInstance()
            val sdf=SimpleDateFormat("hh:mm:ss")
            val time=sdf.format(cal.time)
            val req_from=params[0]
            val req_sms=params[1]
            val status=params[2]
            try {
                val  url=URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw=BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String=
                        URLEncoder.encode("sname","UTF-8")+"="+URLEncoder.encode(req_from,"UTF-8")+"&"+
                        URLEncoder.encode("reqsms","UTF-8")+"="+URLEncoder.encode(req_sms,"UTF-8")+"&"+
                        URLEncoder.encode("st","UTF-8")+"="+URLEncoder.encode(status,"UTF-8")+"&"+
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
            val ja= JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
            Toast.makeText(ctx,"$message",Toast.LENGTH_LONG).show()
        }
    }



    private inner class background_reject :AsyncTask<String,String,String>()
    {
        var fetch_url:String=""
        override fun onPreExecute() {
            fetch_url="https://pasko.000webhostapp.com/notifications_details.php"
        }
        override fun doInBackground(vararg params: String?): String {
            val req_from=params[0]
            val req_sms=params[1]
            val status=params[2]
            val date=params[3]
            val time=params[4]

            try {
                val  url= URL(fetch_url)
                val httpURLConnection=url.openConnection()as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput=true
                httpURLConnection.doInput=true
                val os=httpURLConnection.outputStream
                val bw= BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                val post_data:String= URLEncoder.encode("sname","UTF-8")+"="+ URLEncoder.encode(req_from,"UTF-8")+"&"+
                        URLEncoder.encode("reqsms","UTF-8")+"="+ URLEncoder.encode(req_sms,"UTF-8")+"&"+
                        URLEncoder.encode("st","UTF-8")+"="+ URLEncoder.encode(status,"UTF-8")+"&"+
                        URLEncoder.encode("date","UTF-8")+"="+ URLEncoder.encode(date,"UTF-8")+"&"+
                        URLEncoder.encode("time","UTF-8")+"="+ URLEncoder.encode(time,"UTF-8")
                bw.write(post_data)
                bw.flush()
                bw.close()
                os.close()
                var data:String=""
                var line:String=""
                val ins=httpURLConnection.inputStream
                val br= BufferedReader(InputStreamReader(ins,"ISO-8859-1"))
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
            catch (e: IOException)
            {
                e.printStackTrace()
                return "Exception="+e.message
            }
        }
        override fun onPostExecute(result: String?) {
            val ja= JSONArray(result)
            val jo=ja.getJSONObject(0)
            val message=jo.getString("message")
            Toast.makeText(ctx,"$message",Toast.LENGTH_LONG).show()

        }
    }


}

