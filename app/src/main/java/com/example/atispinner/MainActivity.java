package com.example.atispinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ati_spinner_mohamad.ListDialogTemplate;
import com.example.ati_spinner_mohamad.Model_ListDialogTemplate;


import java.util.ArrayList;
import java.util.List;
import com.example.atispinner.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView nomrehExam = (TextView) findViewById(R.id.j);


        List<Model_ListDialogTemplate> List = new ArrayList<>();
        List.add(new Model_ListDialogTemplate("11", "1", "546"));
        List.add(new Model_ListDialogTemplate("22", "2", "ی453ک"));
        List.add(new Model_ListDialogTemplate("33", "3", "564"));
        List.add(new Model_ListDialogTemplate("44", "4", "564"));
        List.add(new Model_ListDialogTemplate("55", "5", "564"));
        List.add(new Model_ListDialogTemplate("66", "6", "564"));
        List.add(new Model_ListDialogTemplate("77", "7", "564"));
        List.add(new Model_ListDialogTemplate("88", "8", "564"));

        new ListDialogTemplate(MainActivity.this, List, true, false, true) {

            @Override
            public void onCreateing() {
                super.onCreateing();
                this.setTitle = "گزینه هارا انتخاب کنید";
                this.setDescription = "Description";
                //this.List_View.getLayoutParams().height = 150;
            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                String Tag = String.valueOf(v.getTag());
                switch (Tag) {

                    case "OK":
                        //this.myDialog.dismiss();
                        Toast.makeText(v.getContext(), "OK", Toast.LENGTH_SHORT).show();
                        break;

                    case "CANCEL":
                        //this.myDialog.dismiss();
                        Toast.makeText(v.getContext(), "CANCEL", Toast.LENGTH_SHORT).show();
                        break;

                    case "ToolbarBack":
                        //this.myDialog.dismiss();
                        Toast.makeText(v.getContext(), "ToolbarBack", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                super.onItemClick(parent, view, position, id);
                //Toast.makeText(v.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                int x = this.ListSelected.size();
            }

        }.ShowDialog();


    }
}
