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


        List<Model_ListDialogTemplate> List = new ArrayList<>();
        List.add(new Model_ListDialogTemplate("11", "محمد محمودیان", "111"));
        List.add(new Model_ListDialogTemplate("22", "حسن شصتی", "222"));
        List.add(new Model_ListDialogTemplate("33", "سید محمد حسینی", "333"));
        List.add(new Model_ListDialogTemplate("44", "حسین معافی", "444"));
        List.add(new Model_ListDialogTemplate("55", "امین تاجیک", "555"));
        List.add(new Model_ListDialogTemplate("66", "حمید حیدری", "666"));
        List.add(new Model_ListDialogTemplate("77", "علی فرهادی", "777"));
        List.add(new Model_ListDialogTemplate("88", "رضا نادری", "888"));

        new ListDialogTemplate(MainActivity.this, List, true, false, true) {

            @Override
            public void onCreateing() {
                super.onCreateing();
                this.setTitle = "گزینه هارا انتخاب کنید";
                this.setDescription = "توضیحاتی در باره گزینه ها";
                this.List_View.getLayoutParams().height = 400;
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
