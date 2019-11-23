package com.example.atispinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ati_spinner_mohamad.SmartSpiner;
import com.example.ati_spinner_mohamad.Model_SmartSpiner;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Model_SmartSpiner> List = new ArrayList<>();
        List.add(new Model_SmartSpiner("11", "محمد محمودیان", "برنامه نویس",false));
        List.add(new Model_SmartSpiner("22", "حسن شصتی", "برنامه نویس PHP",false));
        List.add(new Model_SmartSpiner("33", "سید محمد حسینی", "شبکه CCNA",false));
        List.add(new Model_SmartSpiner("44", "حسین معافی", "برنامه نویس PHP Java",false));
        List.add(new Model_SmartSpiner("55", "امین تاجیک", "برنامه نویس PHP JavaScript",false));
        List.add(new Model_SmartSpiner("66", "حمید حیدری", "",false));
        List.add(new Model_SmartSpiner("77", "علی فرهادی", "",false));
        List.add(new Model_SmartSpiner("88", "رضا نادری", "طراح",false));

        new SmartSpiner(MainActivity.this, List, true, false,true, true) {

            @Override
            public void onCreateing() {
                super.onCreateing();
                this.setTitle = "گزینه هارا انتخاب کنید";
                this.setDescription = "توضیحاتی در باره گزینه ها";

                //this.List_View.getLayoutParams().height = 400;
                //this.BorderColors = R.color.colorAccent;
            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                String Tag = String.valueOf(v.getTag());
                switch (Tag) {

                    case "OK":
                        Toast.makeText(v.getContext(), "OK", Toast.LENGTH_SHORT).show();
                        break;

                    case "CANCEL":
                        this.myDialog.dismiss();
                        Toast.makeText(v.getContext(), "CANCEL", Toast.LENGTH_SHORT).show();
                        break;

                    case "ToolbarBack":
                        Toast.makeText(v.getContext(), "ToolbarBack", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                super.onItemClick(parent, view, position, id);
                int x = this.ListSelected.size();
            }

        }.ShowDialog();


    }
}
