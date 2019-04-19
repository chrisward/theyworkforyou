package de.chrisward.theyworkforyou.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "lords")
data class Lord(
    @PrimaryKey @SerializedName("member_id") @Expose var member_id: Int,
    @SerializedName("person_id") @Expose var person_id: Int,
    @SerializedName("name") @Expose var name: String,
    @SerializedName("party") @Expose var party: String,
    @SerializedName("office") @Ignore var office: List<Office>?
) {
    constructor() : this(0, 0, "", "", null)
}
