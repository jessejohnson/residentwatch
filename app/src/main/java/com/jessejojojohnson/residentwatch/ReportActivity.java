package com.jessejojojohnson.residentwatch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jessejojojohnson.residentwatch.util.GPSTracker;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

import javax.security.auth.login.LoginException;


public class ReportActivity extends ActionBarActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    EditText tag;
    RequestParams params;
    String type;
    Double longi, lati;
    GPSTracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        tracker = new GPSTracker(ReportActivity.this);
        params = new RequestParams();

        Intent intent = getIntent();
        type = intent.getStringExtra("name");

        Button reportButton = (Button) findViewById(R.id.reportButton);
        tag = (EditText) findViewById(R.id.tag);
        imageView = (ImageView) findViewById(R.id.image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendReport();
            }
        });
    }

    public void sendReport(){

        //before we post, create a progress dialog to indicate loading
        final ProgressDialog progressDialog = new ProgressDialog(ReportActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("please wait while we submit your report...");

        //now show the progress dialog...to be dismissed in onSuccess and onFailure callbacks
        progressDialog.show();

        longi = tracker.getLongitude();
        lati = tracker.getLatitude();

        Log.e("RW", tracker.getLatitude() + "");
        String locString = longi + "," + lati;

        params.put("location", locString);
        params.put("tag", tag.getText().toString());
        params.put("type", type);
        params.put("phone_number", "000");

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://residentwatch.herokuapp.com/api/v1.0/users/reports/", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
                progressDialog.dismiss();
                Toast.makeText(ReportActivity.this, "Well done good citizen!", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                progressDialog.dismiss();
                Toast.makeText(ReportActivity.this, "Connection Error!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progressDialog.dismiss();
                Toast.makeText(ReportActivity.this, "Connection Error!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(ReportActivity.this, "Connection Error!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void pickImage(){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");


                Uri imageUri = data.getData();
                String imagePath = getPath(imageUri);
//                Bitmap imageBitmap = BitmapFactory.decodeFile(imagePath);

                imageView.setImageBitmap(imageBitmap);

                //create new file
                File favatar = new File(imagePath);
                FileOutputStream fileOutputStream = new FileOutputStream(favatar);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();

                Log.e("RW", imagePath);

                params.put("photo", favatar);


            }
        } catch (Exception e){
            Log.e("RW", e.toString());
        }
    }

    public String getPath(Uri uri) {
        if( uri == null ) {
            return null;
        }
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = this.getContentResolver().query(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
