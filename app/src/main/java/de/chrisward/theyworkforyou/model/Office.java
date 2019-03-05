package de.chrisward.theyworkforyou.model;

import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Office {
    @PrimaryKey(autoGenerate = true)
    @Expose
    public int office_id;

    @SerializedName("dept")
    @Expose
    public String dept;

    @SerializedName("position")
    @Expose
    public String position;

    @SerializedName("from_date")
    @Expose
    public String from_date;

    @SerializedName("to_date")
    @Expose
    public String to_date;

    public Office() {}

    public Office(
            String dept,
            String position,
            String from_date,
            String to_date
    ) {
        this.dept = dept;
        this.position = position;
        this.from_date = from_date;
        this.to_date = to_date;
    }
}
