package de.chrisward.theyworkforyou.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "offices")
data class Office (
        @PrimaryKey(autoGenerate = true) @Expose var office_id: Int,
        @SerializedName("dept") @Expose var dept: String,
        @SerializedName("position") @Expose var position: String,
        @SerializedName("from_date") @Expose var from_date: String,
        @SerializedName("to_date") @Expose var to_date: String
)
