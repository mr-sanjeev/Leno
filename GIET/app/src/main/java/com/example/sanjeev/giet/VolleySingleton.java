package com.example.sanjeev.giet;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import org.jetbrains.annotations.Nullable;

public class VolleySingleton
{
    private static VolleySingleton mInstance;
    private RequestQueue requestQueue;
    private  static Context mctx;
    private VolleySingleton(Context context)
    {
        mctx=context;
        requestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue=Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleySingleton getmInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance=new VolleySingleton(context);
        }
        return mInstance;
    }
    public <T>void addToRequestque(Request<T> request)
    {
        requestQueue.add(request);
    }
}
