package com.example.ati_spinner_mohamad;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class SmartSpiner implements View.OnClickListener, AdapterView.OnItemClickListener {


    private Activity myActivity;
    private Context myContext;
    private java.util.List<Model_SmartSpiner> List;

    private Adapter_SmartSpiner Adapter;
    //public int REQ_CODE_SPEECH_INPUT = 100;
    private ImageView img_voiceSearch;
    private ConstraintLayout consSearch;
    private ConstraintLayout consBack;
    public ConstraintLayout imgSearch;
    private ImageView imgClearSearch;
    private ConstraintLayout toolbar;
    private EditText editSearch;
    public ConstraintLayout toolbar_back;
    public ConstraintLayout Btn_Add;

    CardView cardView2,cardView4;
    TextView cons_ok,cons_cancel;

    private ListView List_View;
    public Dialog myDialog;
    public String setTitle = "";
    public String setDescription = "";

    private boolean isCheckBox;
    private boolean isButton;
    private boolean MultiSelect;
    private boolean RowNumber;
    private boolean ShowExtra;

    public int setBorderColors = R.color.toolbar_newUI;
    public int setToolbarColors = R.color.toolbar_newUI;
    public int setItemBorderColors = R.color.toolbar_newUI;
    public int setCheckBoxColors = R.color.toolbar_newUI;


    public java.util.List<Model_SmartSpiner> ListSelected = new ArrayList<>();

    public SmartSpiner(Activity activity, java.util.List<Model_SmartSpiner> list, Boolean CheckBox, boolean Multiselect,Boolean rowNumber,Boolean showExtra, Boolean Button) {

        myActivity = activity;
        myContext = myActivity.getApplicationContext();
        List = list;

        if(setTitle.equals(""))
            setTitle = "یک گزینه را انتخاب کنید";
        isCheckBox =CheckBox;
        isButton = Button;
        MultiSelect = Multiselect;
        RowNumber = rowNumber;
        ShowExtra = showExtra;




        onCreateing();
    }

    public void onCreateing(){
        myDialog = new Dialog(myActivity);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.setCancelable(true);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.setContentView(R.layout.listdialogtemplate);

        finds();
    }




    @SuppressLint("ResourceAsColor")
    public void ShowDialog() {


        myDialog.show();

        ((TextView) myDialog.findViewById(R.id._toolbar_title)).setText(setTitle);
        ((TextView) myDialog.findViewById(R.id._txt_desk)).setText(setDescription);
        if(setDescription.equals(""))
            cardView2.setVisibility(View.GONE);

        if(!isButton)
            cardView4.setVisibility(View.GONE);

        //<editor-fold desc="Set Colors">
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ((CardView) myDialog.findViewById(R.id.border)).setCardBackgroundColor(myActivity.getResources().getColor(setBorderColors, null));
            ((ConstraintLayout) myDialog.findViewById(R.id.toolbar)).setBackgroundColor(myActivity.getResources().getColor(setToolbarColors, null));
            ((ConstraintLayout) myDialog.findViewById(R.id._search_panel_back)).setBackgroundColor(myActivity.getResources().getColor(setToolbarColors, null));
        }else {
            ((CardView) myDialog.findViewById(R.id.border)).setCardBackgroundColor(setBorderColors);
            ((ConstraintLayout) myDialog.findViewById(R.id.toolbar)).setBackgroundColor(setToolbarColors);
            ((ConstraintLayout) myDialog.findViewById(R.id._search_panel_back)).setBackgroundColor(setToolbarColors);
        }
        //</editor-fold>




        Adapter = new Adapter_SmartSpiner(myContext, R.layout.listdilaogtempalte_item, List,isCheckBox,MultiSelect,RowNumber,ShowExtra, setItemBorderColors, setCheckBoxColors);
        List_View.setAdapter(Adapter);
        List_View.setOnItemClickListener(this);

        //Animation anim = AnimationUtils.loadAnimation(myContext, R.anim.aaa);
        //anim.setDuration(1500);
        //List_View.setAnimation(anim);

        cons_ok.setTag("OK");
        cons_ok.setOnClickListener(this);

        cons_cancel.setTag("CANCEL");
        cons_cancel.setOnClickListener(this);

        toolbar_back.setTag("ToolbarBack");
        toolbar_back.setOnClickListener(this);

        Btn_Add.setTag("Btn_Add");
        Btn_Add.setOnClickListener(this);

        handleSearch();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Adapter.HandelCheked_CheckBox(Adapter.List.get(position).Id);
        Adapter.notifyDataSetChanged();


        ListSelected.clear();
        for (int i = 0; i < Adapter.List.size(); i++) {
            if (Adapter.List.get(i).Checked) {
                ListSelected.add(Adapter.List.get(i));
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 10086: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String hasan = result.get(0);
                    editSearch.setText(ConverPersian2English(hasan));
                    Adapter.getFilter().filter(editSearch.getText().toString());
                }
                break;
            }
        }
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 5000);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 5000);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fa-IR");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"لطفا صحبت کنید");
        try {
            myActivity.startActivityForResult(intent, 10086);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(myContext,"گوشی شما از این امکان پشتیبانی نمی کند", Toast.LENGTH_SHORT).show();
        }
    }


    private void finds() {

        cardView2 = (CardView) myDialog.findViewById(R.id.cardView2);
        cardView4 = (CardView) myDialog.findViewById(R.id.cardView4);
        List_View = (ListView) myDialog.findViewById(R.id.list_item);
        cons_ok = (TextView) myDialog.findViewById(R.id._btn_ok);
        cons_cancel = (TextView) myDialog.findViewById(R.id._btn_cancel);

        toolbar = (ConstraintLayout)  myDialog.findViewById(R.id.toolbar);
        imgSearch = (ConstraintLayout) myDialog.findViewById(R.id.img_search);
        consSearch = (ConstraintLayout) myDialog.findViewById(R.id.includeSearch);
        consBack = (ConstraintLayout) myDialog.findViewById(R.id._cons_back);
        editSearch = (EditText) myDialog.findViewById(R.id._editSearch);
        imgClearSearch = (ImageView) myDialog.findViewById(R.id._imgClearEditSearch);
        img_voiceSearch = (ImageView) myDialog.findViewById(R.id._img_voiceSearch);
        toolbar_back = (ConstraintLayout) myDialog.findViewById(R.id.toolbar_back);
        Btn_Add = (ConstraintLayout) myDialog.findViewById(R.id.img_add);
    }

    private void handleSearch() {
        consSearch.setVisibility(View.GONE);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consSearch.setVisibility(View.VISIBLE);
            }
        });

        imgClearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editSearch.getText().toString().equals("")){
                    consSearch.setVisibility(View.INVISIBLE);
                    toolbar.setVisibility(View.VISIBLE);
                }else
                    editSearch.setText("");
            }
        });
        consBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consSearch.setVisibility(View.GONE);
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Adapter.getFilter().filter(ConverPersian2English(editSearch.getText().toString()));
            }
        });

        img_voiceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }

    public String ConverPersian2English(String number){
        char[] chars = new char[number.length()];
        for(int i=0;i<number.length();i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);

    }


}