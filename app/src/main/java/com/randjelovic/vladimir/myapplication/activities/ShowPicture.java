package com.randjelovic.vladimir.myapplication.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.common.MyApplication;
import com.randjelovic.vladimir.myapplication.expandableadapter.MyExpandableListAdapter;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShowPicture extends AppCompatActivity {

    Button button;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);
        button = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ShowPicture.ShowPictureService().execute();
            }
        });
    }


    public class ShowPictureService extends AsyncTask<String, Integer, String> {

        private static final String TAG = "ShowPicture";
        private static final String AUTHENTICATION_TAG = "Basic ";
        private static final String AUTHENTICATION_HEADER = "Authorization";
        private Bitmap image;

        @Override
        protected String doInBackground(String... strings) {
            publishProgress(0);
            String message = null;
            HttpURLConnection urlConnection = null;
            try {
//                URL url = new URL(MyApplication.getAppContext().getResources().getString(R.string.url_showPicture));
                URL url = new URL(MyApplication.getAppContext().getResources().getString(R.string.url_showPictureFromDb));
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty(AUTHENTICATION_HEADER, MyApplication.getBasicAuth());
                urlConnection.setRequestMethod("GET");

                publishProgress(1);
                Integer responseCode = urlConnection.getResponseCode();
                if (responseCode==200){
                    image = BitmapFactory.decodeStream(urlConnection.getInputStream());
                    message=(MyApplication.getAppContext().getResources().getString(R.string.login_successful));
                }
                else if(responseCode==401){
                    message=(MyApplication.getAppContext().getResources().getString(R.string.unauthorised));
                    MyApplication.setAuthenticated(false);
                }
                else{
                    message=(MyApplication.getAppContext().getResources().getString(R.string.authentication_error));
                    MyApplication.setAuthenticated(false);
                }
                Log.d(TAG, "HTTP response:"+responseCode.toString());
                publishProgress(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            publishProgress(3);
            return message;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "Progress: " + values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "GET - Result: " + result);
            Toast.makeText(MyApplication.getAppContext(), "Slika je prikazana: "+result, Toast.LENGTH_LONG).show();
            ShowPicture.this.image.setImageBitmap(image);

        }
    }

}
