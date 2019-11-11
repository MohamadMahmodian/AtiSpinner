package com.example.ati_spinner_mohamad;


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

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


public class Adapter_ListDialogTemplate extends ArrayAdapter<Model_ListDialogTemplate>
{

    java.util.List<Model_ListDialogTemplate> List;
    java.util.List<Model_ListDialogTemplate> List_Copy;
    boolean MultiSelect,ShowCheckBox;

    public Adapter_ListDialogTemplate(Context context, int textViewResourceId, java.util.List<Model_ListDialogTemplate> list, Boolean showCheckBox, Boolean Multiselect) {
        super(context, textViewResourceId, list);
        this.List = list;
        this.List_Copy = this.List;
        MultiSelect = Multiselect;
        ShowCheckBox = showCheckBox;
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
        CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox_survey);
        ConstraintLayout cons_checkBox = (ConstraintLayout) v.findViewById(R.id.consCheckBox);


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

        if(!ShowCheckBox)
            checkBox.setVisibility(View.GONE);

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
                java.util.List<Model_ListDialogTemplate> myArray = new ArrayList<>();

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

                List = (ArrayList<Model_ListDialogTemplate>) results.values;
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
