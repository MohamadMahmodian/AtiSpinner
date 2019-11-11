package com.example.ati_spinner_mohamad;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.speech.RecognizerIntent;
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

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;


import java.util.ArrayList;

public abstract class ListDialogTemplate implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Activity myActivity;
    private Context myContext;
    private java.util.List<Model_ListDialogTemplate> List;

    private Adapter_ListDialogTemplate Adapter;
    private int REQ_CODE_SPEECH_INPUT = 100;
    private ImageView img_voiceSearch;
    private ConstraintLayout consSearch;
    private ConstraintLayout consBack;
    private ConstraintLayout imgSearch;
    private ImageView imgClearSearch;
    private ConstraintLayout toolbar;
    private EditText editSearch;
    private ConstraintLayout toolbar_back;

    CardView cardView2,cardView4;
    TextView cons_ok,cons_cancel;

    public ListView List_View;
    public Dialog myDialog;
    public String setTitle = "";
    public String setDescription = "";
    public boolean isCheckBox;
    public boolean isButton;
    public boolean MultiSelect;

    public java.util.List<Model_ListDialogTemplate> ListSelected = new ArrayList<>();

    public ListDialogTemplate(Activity activity, java.util.List<Model_ListDialogTemplate> list, Boolean CheckBox, boolean Multiselect, Boolean Button) {

        myActivity = activity;
        myContext = myActivity.getApplicationContext();
        List = list;

        if(setTitle.equals(""))
            setTitle = "یک گزینه را انتخاب کنید";
        isCheckBox =CheckBox;
        isButton = Button;
        MultiSelect = Multiselect;

        myDialog = new Dialog(myActivity);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.setCancelable(true);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.setContentView(R.layout.listdialogtemplate);

        finds();

        onCreateing();
    }

    public void onCreateing(){}

    public void ShowDialog() {


        myDialog.show();

        ((TextView) myDialog.findViewById(R.id.toolbar_title)).setText(setTitle);
        ((TextView) myDialog.findViewById(R.id.txt_desk)).setText(setDescription);
        if(setDescription.equals(""))
            cardView2.setVisibility(View.GONE);

        if(!isButton)
            cardView4.setVisibility(View.GONE);



        Adapter = new Adapter_ListDialogTemplate(myContext, R.layout.listdilaogtempalte_item, List,isCheckBox,MultiSelect);
        List_View.setAdapter(Adapter);
        List_View.setOnItemClickListener(this);

        cons_ok.setTag("OK");
        cons_ok.setOnClickListener(this);

        cons_cancel.setTag("CANCEL");
        cons_cancel.setOnClickListener(this);

        toolbar_back.setTag("ToolbarBack");
        toolbar_back.setOnClickListener(this);



        handleSearch();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Adapter.aa(Adapter.List.get(position).Id);
        Adapter.notifyDataSetChanged();


        ListSelected.clear();
        for (int i = 0; i < Adapter.List.size(); i++) {
            if (Adapter.List.get(i).Checked) {
                ListSelected.add(Adapter.List.get(i));
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
            myActivity.startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(myContext,"گوشی شما از این امکان پشتیبانی نمی کند", Toast.LENGTH_SHORT).show();
        }
    }

    private void finds() {

        cardView2 = (CardView) myDialog.findViewById(R.id.cardView2);
        cardView4 = (CardView) myDialog.findViewById(R.id.cardView4);
        List_View = (ListView) myDialog.findViewById(R.id.list_item);
        cons_ok = (TextView) myDialog.findViewById(R.id.btn_ok);
        cons_cancel = (TextView) myDialog.findViewById(R.id.btn_cancel);

        toolbar = (ConstraintLayout)  myDialog.findViewById(R.id.toolbar);
        imgSearch = (ConstraintLayout) myDialog.findViewById(R.id.img_search);
        consSearch = (ConstraintLayout) myDialog.findViewById(R.id.includeSearch);
        consBack = (ConstraintLayout) myDialog.findViewById(R.id.cons_back);
        editSearch = (EditText) myDialog.findViewById(R.id.editSearch);
        imgClearSearch = (ImageView) myDialog.findViewById(R.id.imgClearEditSearch);
        img_voiceSearch = (ImageView) myDialog.findViewById(R.id.img_voiceSearch);
        toolbar_back = (ConstraintLayout) myDialog.findViewById(R.id.toolbar_back);
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

                Adapter.getFilter().filter(MCS.ConverPersian2English(editSearch.getText().toString()));
            }
        });

        img_voiceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }


}
