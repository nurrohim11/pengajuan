package com.rohim.rohimmodule;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.kinda.alert.KAlertDialog;

import java.util.HashMap;
import java.util.Map;

public class RequestQueueService {
    private static RequestQueueService mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;
    private static KAlertDialog mProgressDialog;

    private RequestQueueService(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestQueueService(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public Map<String, String> getRequestHeader() {
        Map<String, String> headerMap = new HashMap<>();
        return headerMap;
    }

    public void clearCache() {
        mRequestQueue.getCache().clear();
    }

    public void removeCache(String key) {
        mRequestQueue.getCache().remove(key);
    }

    public static void showAlertSuccess(String message, final FragmentActivity context, final int kode) {
        // kode 1 = finish
        // kode 0 = tidak finish
        LayoutInflater inflater = LayoutInflater.from(context); // 1
        View v = inflater.inflate(R.layout.dialog_success, null);
        TextView tvMessage = v.findViewById(R.id.tv_message);
        Button btnOk = v.findViewById(R.id.buttonOk);
        tvMessage.setText(message);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(v);
        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if(kode == 1){
                    context.finish();
                }
            }
        });
    }

    public static void showAlertError(String message, final Context context) {
        try {
            mProgressDialog = new KAlertDialog(context, KAlertDialog.ERROR_TYPE);
            mProgressDialog.setTitleText("Ooopssss....");
            mProgressDialog.setContentText(message);
            mProgressDialog.setConfirmClickListener(new KAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(KAlertDialog kAlertDialog) {
                    mProgressDialog.dismiss();
                }
            });
            mProgressDialog.show();
//            LayoutInflater inflater = LayoutInflater.from(context); // 1
//            View v = inflater.inflate(R.layout.dialog_error, null);
//            TextView tvMessage = v.findViewById(R.id.tv_message);
//            Button btnOk = v.findViewById(R.id.buttonOk);
//            tvMessage.setText(message);
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setView(v);
//            builder.setCancelable(false);
//            final AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//            btnOk.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    alertDialog.dismiss();
//                }
//            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Start showing progress
    public static void showProgressDialog(final Context activity) {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) cancelProgressDialog();
        }
        mProgressDialog = new KAlertDialog(activity, KAlertDialog.PROGRESS_TYPE);
        mProgressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mProgressDialog.setTitleText("Loading");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    //Stop showing progress
    public static void cancelProgressDialog() {
        if (mProgressDialog != null){
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

}
