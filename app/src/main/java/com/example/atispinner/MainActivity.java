package com.example.atispinner;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ati_spinner_mohamad.SmartSpiner;
import com.example.ati_spinner_mohamad.Model_SmartSpiner;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SmartSpiner smartSpiner;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Object s = "dd";

        List<Model_SmartSpiner> List = new ArrayList<>();
        List.add(new Model_SmartSpiner("11", "محمد محمودیان", "برنامه نویس",s,false));
        List.add(new Model_SmartSpiner("aaa", "حسن شصتی", "برنامه نویس PHP",s,false));
        List.add(new Model_SmartSpiner("33", "سید محمد حسینی", "شبکه CCNA",s,false));
        List.add(new Model_SmartSpiner("44", "حسین معافی", "برنامه نویس PHP Java",s,false));
        List.add(new Model_SmartSpiner("55", "امین تاجیک", "برنامه نویس PHP JavaScript",s,false));
        List.add(new Model_SmartSpiner("66", "حمید حیدری", "",s,false));
        List.add(new Model_SmartSpiner("77", "علی فرهادی", "",s,false));
        List.add(new Model_SmartSpiner("88", "رضا نادری", "طراح",s,false));

        smartSpiner = new SmartSpiner(MainActivity.this, List, true, false,false,true, true) {

            @Override
            public void onCreateing() {
                super.onCreateing();
                this.setTitle = "گزینه هارا انتخاب کنید";
                this.setDescription = "توضیحاتی در باره گزینه ها";


                //this.setCheckBoxColors = R.color.colorAccent;
                //this.setToolbarColors = R.color.colorAccent;
                //this.setBorderColors = R.color.colorAccent;
                //this.List_View.getLayoutParams().height = 400;
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

        };
        smartSpiner.ShowDialog();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        smartSpiner.onActivityResult(requestCode,resultCode,data);
    }
}
