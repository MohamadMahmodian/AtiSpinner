package com.example.ati_spinner_mohamad;

public class Model_SmartSpiner {

    String Id;
    String Name;
    String Extra;
    Boolean Checked = false;



    public Model_SmartSpiner(String id, String name , String extra, Boolean checked) {
        Id = id;
        Name = name;
        Extra = extra;
        Checked = checked;
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

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }
}
