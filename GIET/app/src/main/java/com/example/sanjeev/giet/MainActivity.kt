package com.example.sanjeev.giet
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.view.*
import android.widget.FrameLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.act_main.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custompopup.*
import org.json.JSONArray
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.net.MalformedURLException
import java.net.URLEncoder
import java.security.KeyStore
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{
    var ms=0
    lateinit var snackbar: Snackbar
    lateinit var ctx:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        ctx=this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        var hasInternet = false
        if (isNetworkAvailabe()) {
            hasInternet = true
            tv_connection_chk.text="Network Availabe"
            tv_connection_chk.setTextColor(Color.parseColor("#00ff00"))

        }
        else
        {
            btn_login.isEnabled=false
            btn_register.isEnabled=false
            tv_forgot_password.isEnabled=false
        }

        retrievedata()

        login_message.isSelected=true
        login_message.text="SignIn Below To Continue ! ! ! ! ! ! ! ! !"

        btn_register.setOnClickListener {
            var i =Intent(this,Register::class.java)
            startActivity(i)
        }

        btn_login.setOnClickListener {
            val emailid =et_email.text.toString()
            val password = et_password.text.toString()
            if(emailid.isEmpty() || password.isEmpty())
            {
                //Toast.makeText(applicationContext,"Fill all data",Toast.LENGTH_LONG).show()
                Snackbar.make(it,"FILL ALL REQUIRED FIELDS",Snackbar.LENGTH_LONG).setAction("Close",null).show()
                //snackbar.setActionTextColor(Color.parseColor("#00ff00"))
               /* val view=snackbar.view
                val text=view.findViewById<TextView>(android.support.design.R.id.snackbar_text )
                text.setTextColor(Color.parseColor("#00ffff"))
                val params =FrameLayout.LayoutParams(view.layoutParams)
                params.gravity=Gravity.TOP
                view.layoutParams=(params)
                view.setBackgroundColor(Color.parseColor("#000"))
               */
            }else
            {
                BackgroundTasks().execute(emailid,password)
                savedata();
            }
        }

        btn_reload.setOnClickListener {
            recreate()
         }

        tv_forgot_password.setOnClickListener {
            val builder= AlertDialog.Builder(this)
            val view=layoutInflater.inflate(R.layout.custompopup,null)
            builder.setView(view)
            builder.setCancelable(false)
            val dialog=builder.create()
            dialog.show()
            dialog.btn_recover_password.setOnClickListener {
                val email_id=dialog.et_recovery_email_id.text.toString()
                if(email_id.isEmpty())
                {
                    Toast.makeText(applicationContext,"Please Enter Your Email-Id",Toast.LENGTH_LONG).show()
                }
                else
                {
                    BackgroundTasksfog_pass().execute(email_id)
                    dialog.dismiss()
                }
            }
            dialog.tv_close_popup.setOnClickListener {
                dialog.dismiss()
            }

        }

        gif_dev_details.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        nav_view.setNavigationItemSelectedListener(this)

    }
    //navigation view methods.............

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_fb -> {
               val i=Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/sanjeev.kapoor.927758"))
                startActivity(i)

            }
            R.id.nav_insta -> {
               val  i=Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/mr.______.sanjeev/?hl=en"))
                startActivity(i)
            }
            R.id.nav_git -> {
               val i=Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/mr-sanjeev?tab=repositories"))
                startActivity(i)
            }
            R.id.nav_linkedin -> {
              val i=Intent(Intent.ACTION_VIEW,Uri.parse("https://www.linkedin.com/in/sanjeev-kumar-18a036165/"))
                startActivity(i)
            }
            R.id.nav_whatsapp -> {
                val num="+918084200232"
                val message="Hie Sanjeev!!!!"
                val i=Intent("android.intent.action.MAIN")
                i.action = Intent.ACTION_VIEW
                i.setPackage("com.whatsapp")
                val url="https://api.whatsapp.com/send?phone=$num&text=$message"
                i.data=Uri.parse(url)
                if(i.resolveActivity(applicationContext.packageManager)!=null) {
                    startActivity(i)
                }
            }
            R.id.nav_share -> {
               val i=Intent(Intent.ACTION_SEND)
                startActivity(Intent.createChooser(i,"share via"))
                Toast.makeText(applicationContext,"shared",Toast.LENGTH_LONG).show()
            }
            R.id.nav_email->{
                val id="15aei056.giet@gmail.com"
                val i=Intent(Intent.ACTION_VIEW,Uri.parse("mailto:$id"))
               i.putExtra(Intent.EXTRA_TEXT,"Hie Sanjeev, How are you?")
                startActivity(i)
            }
            R.id.nav_call->{
                val mob_num:String="8084200232"
                var i=Intent(Intent.ACTION_DIAL)
                i.data=Uri.parse("tel:$mob_num")
                startActivity(i)
                Toast.makeText(applicationContext,"Calling Sanjeev ",Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
//navigation view  methods ends......



    //shared prefrences for data saving ......
    private fun retrievedata()
    {
        val mypref=getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val id= mypref.getString("id","")
        val pass=mypref.getString("password","")
        et_email.setText(id)
        et_password.setText(pass)
    }
    private fun savedata() {
        val mypref=getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val editor=mypref.edit()
        editor.putString("id",et_email.text.toString())
        editor.putString("password",et_password.text.toString())
        editor.apply()
    }


    private inner class BackgroundTasks :AsyncTask<String,Void,String>()
    {
        var login_url:String=""
        override fun onPreExecute() {
            login_url="https://pasko.000webhostapp.com/mainproject_login.php"
        }
        override fun doInBackground(vararg params: String?): String
        {
            var data:String=""
            var line:String=""
            val Emailis = params[0]
            val Passwordis = params[1]
            try
            {
                val url = URL(login_url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput= true
                httpURLConnection.doInput=true
                val os = httpURLConnection.outputStream
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                val postdata:String=(URLEncoder.encode("email_id", "UTF-8")+"="+URLEncoder.encode(Emailis, "UTF-8")+"&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(Passwordis, "UTF-8"))
                bw.write(postdata)
                bw.flush()
                bw.close()
                os.close()
                val inputStreamis = httpURLConnection.inputStream
                val br = BufferedReader(InputStreamReader(inputStreamis,"iso-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                inputStreamis.close()
                httpURLConnection.disconnect()
                return data
            }catch (e:MalformedURLException)
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
        override fun onPostExecute(result: String?)
        {
            val jsonArray= JSONArray(result)
            val jsonObject=jsonArray.getJSONObject(0)
            val code:String=jsonObject.getString("code")
            val message:String=jsonObject.getString("message")
            if(code=="login-success")
            {
                val name:String= jsonObject.getString("first-name")
                val sem:String=jsonObject.getString("Semester")
                val id:String=jsonObject.getString("Collage-Id")
                Toast.makeText(ctx, "$message",Toast.LENGTH_LONG).show()
                var i=Intent(applicationContext,Homepage::class.java)
                i.putExtra("user_first_name",name)
                i.putExtra("user_sem",sem)
                i.putExtra("user_collage_id",id)
                startActivity(i)
            }
            else
            {
               Toast.makeText(ctx,"$message",Toast.LENGTH_LONG).show()
                et_email.setText("")
                et_password.setText("")

                val mypref=getSharedPreferences("mypref", Context.MODE_PRIVATE)
                val editor=mypref.edit()
                editor.clear()
                editor.apply()
            }
        }
    }
//fetching recovery password..for forgot password..
    private inner class BackgroundTasksfog_pass :AsyncTask<String,Void,String>()
    {
        var login_url:String=""
        override fun onPreExecute() {
            login_url="https://pasko.000webhostapp.com/Password_recovery.php"
        }
        override fun doInBackground(vararg params: String?): String
        {
            var data:String=""
            var line:String=""
            val RecEmailis = params[0]
            try
            {
                val url = URL(login_url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.doOutput= true
                httpURLConnection.doInput=true
                val os = httpURLConnection.outputStream
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                val postdata:String= URLEncoder.encode("eid", "UTF-8")+"="+URLEncoder.encode(RecEmailis, "UTF-8")
                bw.write(postdata)
                bw.flush()
                bw.close()
                os.close()
                val inputStreamis = httpURLConnection.inputStream
                val br = BufferedReader(InputStreamReader(inputStreamis,"iso-8859-1"))
                while (true)
                {
                    line=br.readLine()?:break
                    data+=line
                }
                br.close()
                inputStreamis.close()
                httpURLConnection.disconnect()
                return data
            }catch (e:MalformedURLException)
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
        override fun onPostExecute(result: String?)
        {
           // Toast.makeText(applicationContext,"$result",Toast.LENGTH_LONG).show()

             val jsonArray= JSONArray(result)
             val jsonObject=jsonArray.getJSONObject(0)
             val code:String=jsonObject.getString("code")
             val message:String=jsonObject.getString("message")
             if(code=="fail")
             {
                 Toast.makeText(applicationContext,"$message",Toast.LENGTH_LONG).show()
             }
             else
             {
                 val rec_password=jsonObject.getString("password")
                 val mn=jsonObject.getString("mob-num")
                 val fn=jsonObject.getString("first-name")
                 BackgroundTasks_message_sim_card().execute(rec_password,mn,fn)
             }
        }
    }

    private inner class BackgroundTasks_message_sim_card :AsyncTask<String,Void,String>()
    {
        override fun doInBackground(vararg params: String?): String {
            val password_is = params[0]
            val mobile_number_is = params[1]
            val first_name_is = params[2]
            try {
                // Construct data
                val  apiKey = "apikey=" + "i8zSTChw/So-xFOuFo4kD06CneaVjL3wPlvsew9kQX"
                val  message = "&message=" + "Dear,$first_name_is your OnCallManagement account password is: ($password_is),\nKindly don't share it with anyone,\nHope you like our services,\nThank you,\nTeam MAVERICK"
                val  sender = "&sender=" + "TXTLCL"
                val  numbers = "&numbers=" + "$mobile_number_is"

                // Send data
                val conn = URL("https://api.textlocal.in/send/?").openConnection()as HttpURLConnection
                val data = apiKey + numbers + message + sender
                conn.setDoOutput(true)
                conn.setRequestMethod("POST")
                conn.setRequestProperty("Content-Length", Integer.toString(data.length))
                conn.getOutputStream().write(data.toByteArray())
                val rd =BufferedReader(InputStreamReader(conn.getInputStream()))
                var sb:String=""
                var line:String=""
                while (true)
                {
                    line = rd.readLine()?:break
                    sb+=line
                }
                rd.close();
                return sb
            } catch (e:Exception) {
                e.printStackTrace()
                return e.message.toString()
            }
        }
        override fun onPostExecute(result: String?)
        {
            //Toast.makeText(applicationContext,result,Toast.LENGTH_LONG).show()
            Toast.makeText(ctx,"Your password has been sent to your registered mobile number,Thank you!!!",Toast.LENGTH_LONG).show()
        }
    }

    private fun isNetworkAvailabe():Boolean
    {
        val connectivityManager= getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
        val activeNetworkInfo=connectivityManager.activeNetworkInfo
        return activeNetworkInfo !=null && activeNetworkInfo.isConnected
    }
}
