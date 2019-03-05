package de.chrisward.theyworkforyou.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "mps")
public class MP {
    @PrimaryKey
    @SerializedName("member_id")
    @Expose
    public int member_id;

    @SerializedName("person_id")
    @Expose
    public int person_id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("party")
    @Expose
    public String party;

    @SerializedName("constituency")
    @Expose
    public String constituency;

    @SerializedName("office")
    @Ignore
    public List<Office> office;

    public MP() {}

    public MP(
            int memberId,
            int personId,
            String name,
            String party,
            String constituency
    ) {
        this(
                memberId,
                personId,
                name,
                party,
                constituency,
                null
        );
    }

    public MP(
            int memberId,
            int personId,
            String name,
            String party,
            String constituency,
            List<Office> office
    ) {
        this.member_id = memberId;
        this.person_id = personId;
        this.name = name;
        this.party = party;
        this.constituency = constituency;
        this.office = office;
    }
}
