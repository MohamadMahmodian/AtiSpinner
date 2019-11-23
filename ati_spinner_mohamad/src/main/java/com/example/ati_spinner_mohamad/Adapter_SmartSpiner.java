package com.example.ati_spinner_mohamad;


import android.annotation.SuppressLint;
import android.content.Context;
//import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


public class Adapter_SmartSpiner extends ArrayAdapter<Model_SmartSpiner>
{

    java.util.List<Model_SmartSpiner> List;
    java.util.List<Model_SmartSpiner> List_Copy;
    boolean MultiSelect,ShowCheckBox,RowNumber;
    int Item_Back,CheckBoxColors;

    public Adapter_SmartSpiner(Context context, int textViewResourceId, java.util.List<Model_SmartSpiner> list, Boolean showCheckBox, Boolean Multiselect,Boolean rowNumber,int item_BackgroundColors,int checkBoxColors) {
        super(context, textViewResourceId, list);
        this.List = list;
        this.List_Copy = this.List;
        MultiSelect = Multiselect;
        ShowCheckBox = showCheckBox;

        Item_Back = item_BackgroundColors;
        RowNumber = rowNumber;
    }


    @Override
    public int getCount() {
        return this.List.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.listdilaogtempalte_item, null);

        TextView txtOptions = (TextView) v.findViewById(R.id.txt_responseOptions);
        TextView txt_responseOptions_extra = (TextView) v.findViewById(R.id.txt_responseOptions_extra);
        CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox_survey);
        ConstraintLayout cons_checkBox = (ConstraintLayout) v.findViewById(R.id.consCheckBox);
        TextView txt_rownumber = (TextView) v.findViewById(R.id.txt_rownumber);
        CardView card_rownumber = (CardView) v.findViewById(R.id.card_rownumber);

        ((CardView) v.findViewById(R.id.card_item)).setCardBackgroundColor(getContext().getResources().getColor(Item_Back,null));


        txtOptions.setText(List.get(position).Name);
        checkBox.setChecked(List.get(position).Checked);

        checkBox.setTag(position);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int Position = Integer.parseInt(((CheckBox) buttonView).getTag().toString());
                aa(List.get(Position).Id);
                notifyDataSetChanged();

            }
        });

        if(!List.get(position).Extra.equals("")) {
            txt_responseOptions_extra.setText(List.get(position).Extra);
        }else {
            txt_responseOptions_extra.setVisibility(View.GONE);
        }

        if(!ShowCheckBox)
            checkBox.setVisibility(View.GONE);

        if(RowNumber) {
            txt_rownumber.setText(String.valueOf(position));
            card_rownumber.setCardBackgroundColor(getContext().getResources().getColor(Item_Back,null));
        }else {
            card_rownumber.setVisibility(View.GONE);
        }
//        cons_checkBox.setTag(position);
//        cons_checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int Position = Integer.parseInt(((ConstraintLayout) v).getTag().toString());
//                aa(Position);
//                notifyDataSetChanged();
//
//            }
//        });

        return v;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                java.util.List<Model_SmartSpiner> myArray = new ArrayList<>();

                for (int i = 0; i < List_Copy.size(); i++) {
                    if (constraint.length() > 0) {
                        if (List_Copy.get(i).getName().contains(constraint.toString()))
                            myArray.add(List_Copy.get(i));
                    } else
                        myArray.add(List_Copy.get(i));
                }
                results.count = myArray.size();
                results.values = myArray;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                List = (ArrayList<Model_SmartSpiner>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }


    public void aa(String id){
        if (!MultiSelect) {
            for (int i = 0; i < List_Copy.size(); i++) {
                List_Copy.get(i).Checked = false;
            }
        }


        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).Id.equals(id)) {
                if (List.get(i).Checked)
                    List.get(i).Checked = false;
                else
                    List.get(i).Checked = true;

                break;
            }
        }

    }
}
