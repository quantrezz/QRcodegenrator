package com.example.dell_vostro.QrGenerator;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class MainActivity extends AppCompatActivity {
    Button btn_generate;
    EditText et_email;
    ImageView iv_qrcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_generate= (Button)findViewById(R.id.btn_generate);
        et_email  =(EditText)findViewById(R.id.et_email);
        iv_qrcode=(ImageView)findViewById(R.id.iv_qrcode);


        btn_generate .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // edit text into string

                String email= et_email.getText().toString();
                WindowManager windowManager= (WindowManager)getSystemService(WINDOW_SERVICE);
                Display display=windowManager.getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int x=point.x;
                int y=point.y;
                 int icon=x<y?x:y;
                icon = icon*3/4;
                QRCodeEncoder qrCodeEncoder=new QRCodeEncoder(email,null,Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(),icon);
                try{
                    Bitmap bitmap=qrCodeEncoder.encodeAsBitmap();
                    iv_qrcode.setImageBitmap(bitmap);
                }
                catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });
    }

}
