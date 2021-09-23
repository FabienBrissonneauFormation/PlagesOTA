package biz.ei6.plages

import android.os.Parcel
import android.os.Parcelable

class Plage (val nom : String, val description : String, val image : String,
             val url: String,
             val largeur : Int, val longueur : Int,
             val latitude : Double, val longitude : Double) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nom)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(url)
        parcel.writeInt(largeur)
        parcel.writeInt(longueur)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plage> {
        override fun createFromParcel(parcel: Parcel): Plage {
            return Plage(parcel)
        }

        override fun newArray(size: Int): Array<Plage?> {
            return arrayOfNulls(size)
        }
    }

}