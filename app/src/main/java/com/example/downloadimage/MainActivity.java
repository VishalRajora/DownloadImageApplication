package com.example.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
{
    ImageView img;
    public class DownloadImage extends AsyncTask<String, Void, Bitmap > {

        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url = null;
            try {
                url = new URL ( urls[0] );

                HttpURLConnection httpURLConnection = ( HttpURLConnection ) url.openConnection ();

                httpURLConnection.connect ();

                InputStream inputStream = httpURLConnection.getInputStream ();
                Bitmap m = BitmapFactory.decodeStream ( inputStream );

            return  m;

            } catch (Exception e)
            {
                e.printStackTrace ();
            }
            return  null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

      img =(ImageView)findViewById ( R.id.img );
        Button btn = (Button)findViewById ( R.id.btn );


    }

    public void downlaod(View view)
    {
        DownloadImage downloadImage=new DownloadImage ();
        Bitmap yourimage;
        try {

            yourimage=downloadImage.execute ( "https://img.purch.com/w/660/aHR0cDovL3d3dy5saXZlc2NpZW5jZS5jb20vaW1hZ2VzL2kvMDAwLzEwNC84MTkvb3JpZ2luYWwvY3V0ZS1raXR0ZW4uanBn" ).get ();
        img.setImageBitmap ( yourimage );

        } catch (ExecutionException e) {
            e.printStackTrace ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }

    }
}
