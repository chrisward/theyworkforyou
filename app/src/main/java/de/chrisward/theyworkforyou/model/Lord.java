package de.chrisward.theyworkforyou.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "lords")
public class Lord {
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

    @SerializedName("office")
    @Ignore
    public List<Office> office;

    public Lord() {}

    public Lord(
            int memberId,
            int personId,
            String name,
            String party
    ) {
        this(
                memberId,
                personId,
                name,
                party,
                null
        );
    }

    public Lord(
            int memberId,
            int personId,
            String name,
            String party,
            List<Office> office
    ) {
        this.member_id = memberId;
        this.person_id = personId;
        this.name = name;
        this.party = party;
        this.office = office;
    }
}
