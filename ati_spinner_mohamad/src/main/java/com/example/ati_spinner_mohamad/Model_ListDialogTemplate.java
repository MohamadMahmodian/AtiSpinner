package com.example.ati_spinner_mohamad;

public class Model_ListDialogTemplate {

    String Id;
    String Name;
    String Extra;
    Boolean Checked = false;

    public Boolean getChecked() {
        return Checked;
    }

    public void setChecked(Boolean checked) {
        Checked = checked;
    }

    public Model_ListDialogTemplate(String id, String name , String extra) {
        Id = id;
        Name = name;
        Extra = extra;
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
