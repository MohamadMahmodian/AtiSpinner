package com.example.ati_spinner_mohamad;

public class Model_SmartSpiner {

    String Id;
    String Name;
    String Commernt;
    Object Extra;
    Boolean Checked = false;



    public Model_SmartSpiner(String id, String name , String comment,Object extra, Boolean checked) {
        Id = id;
        Name = name;
        Commernt = comment;
        Extra = extra;
        Checked = checked;
    }

    public Object getExtra() {
        return Extra;
    }

    public void setExtra(Object extra) {
        Extra = extra;
    }

    public Boolean getChecked() {
        return Checked;
    }

    public void setChecked(Boolean checked) {
        Checked = checked;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCommernt() {
        return Commernt;
    }

    public void setCommernt(String commernt) {
        Commernt = commernt;
    }
}
