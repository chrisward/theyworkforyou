package de.chrisward.theyworkforyou.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mps")
data class MP (
        @PrimaryKey @SerializedName("member_id") @Expose var member_id: Int,
        @SerializedName("person_id") @Expose var person_id: Int,
        @SerializedName("name") @Expose var name: String,
        @SerializedName("party") @Expose var party: String,
        @SerializedName("constituency") @Expose var constituency: String,
        @SerializedName("office") @Ignore var office: List<Office>? = null
) {
    constructor() : this(0, 0, "", "", "", null)
}
