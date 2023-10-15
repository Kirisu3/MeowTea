import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MilkTeaTable")
data class MilkTea(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val price: Int,
    val imagePath: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeString(imagePath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MilkTea> {
        override fun createFromParcel(parcel: Parcel): MilkTea {
            return MilkTea(parcel)
        }

        override fun newArray(size: Int): Array<MilkTea?> {
            return arrayOfNulls(size)
        }
    }
}
