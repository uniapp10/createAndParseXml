package com.example.day02_createandparsexml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class XmlActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_number;
    private String  sex;
    private RadioGroup rg_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);


        et_name = findViewById(R.id.et_name);
        et_number = findViewById(R.id.et_number);
        rg_sex = findViewById(R.id.rg_sex);
        Button btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String student_name = et_name.getText().toString().trim();
        String student_number = et_number.getText().toString().trim();

        int rg_id = rg_sex.getCheckedRadioButtonId();
        switch (rg_id){
            case R.id.rg_man:
                sex = "男";
                break;
            case R.id.rg_female:
                sex = "女";
                break;
            default:
                sex = "保密";
                    break;
        }

        if (TextUtils.isEmpty(student_name) || TextUtils.isEmpty(student_number) || TextUtils.isEmpty(sex)){
            Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        XmlSerializer serializer = Xml.newSerializer();
        File file = new File(getFilesDir(), student_name+".xml");
        try {
            OutputStream os = new FileOutputStream(file);
            serializer.setOutput(os, "utf-8");

            serializer.startDocument("utf-8", true);
            serializer.startTag(null,"student");

            serializer.startTag(null, "name");
            serializer.text(student_name);
            serializer.endTag(null, "name");

            serializer.startTag(null, "sex");
            serializer.text(sex);
            serializer.endTag(null, "sex");

            serializer.startTag(null, "number");
            serializer.text(student_number);
            serializer.endTag(null, "number");

            serializer.endTag(null,"student");

            serializer.endDocument();
            os.close();
            Toast.makeText(this, "信息保存到文件成功", Toast.LENGTH_SHORT).show();

            ResultActivity.start(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
