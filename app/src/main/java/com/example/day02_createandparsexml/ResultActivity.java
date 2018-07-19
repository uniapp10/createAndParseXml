package com.example.day02_createandparsexml;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * Created by zhudongdong on 2018/7/19.
 */

public class ResultActivity extends AppCompatActivity {

    public static void start(Context context){
        Intent intent = new Intent(context, ResultActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        TextView tv_result = findViewById(R.id.tv_result);

        SAXReader reader = new SAXReader();
        File file = new File(getFilesDir(), "Zhudong.xml");
        try {
            Document document = reader.read(file);

            Element el_student = document.getRootElement();
            Element el_name = el_student.element("name");
            String name = el_name.getText();

            Element el_sex = el_student.element("sex");
            String sex = el_sex.getText();

            Element el_number = el_student.element("number");
            String number = el_number.getText();

            String res = "name: " + name + "; \n"+
                    "sex: " + sex + "; \n"+
            "number: " + number + "; \n";
            tv_result.setText(res);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
