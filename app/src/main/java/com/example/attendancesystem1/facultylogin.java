package com.example.attendancesystem1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class facultylogin extends AppCompatActivity {

    private EditText editText;
    private ImageView imageView;
    DBHelper d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultylogin);
        imageView = findViewById(R.id.imageView);

    }
    long timestamp = System.currentTimeMillis();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    String timestampString = dateFormat.format(new Date(timestamp));



    public void QRCodeButton(View view) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            String subjectCode = facultypage.fscode.getText().toString();
            //String timestamp = Long.toString(System.currentTimeMillis());

            String data = subjectCode + timestampString;

            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
            Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 200; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            imageView.setImageBitmap(bitmap);

            // Schedule a timer to update the QR code every minute

            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String newTimestamp = Long.toString(System.currentTimeMillis());
                            Date date = new Date(Long.parseLong(newTimestamp));
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            String formattedDate = sdf.format(date);

                            String newData = subjectCode + formattedDate ;
                            BitMatrix newBitMatrix = null;
                            try {
                                newBitMatrix = qrCodeWriter.encode(newData, BarcodeFormat.QR_CODE, 200, 200);
                                Bitmap newBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
                                for (int x = 0; x < 200; x++) {
                                    for (int y = 0; y < 200; y++) {
                                        newBitmap.setPixel(x, y, newBitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                                    }
                                }
                                imageView.setImageBitmap(newBitmap);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }, 500, 500); // Schedule timer to update QR code every minute
        } catch (Exception e) {
            e.printStackTrace();
        }
        Button button = findViewById(R.id.check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://console.firebase.google.com/project/attendancesystem-7b6ba/database/attendancesystem-7b6ba-default-rtdb/data";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
