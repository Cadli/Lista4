package pl.edu.uwr.pum.lista4

import android.os.Parcel
import android.os.Parcelable
import java.util.UUID

class ExerciseList(
    val listNumber: Int,
    val exercises: List<String>,
    val subject: String,
    val grade: Double,
    val id: String = UUID.randomUUID().toString()
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(listNumber)
        parcel.writeString(id)
        parcel.writeStringList(exercises)
        parcel.writeString(subject)
        parcel.writeDouble(grade)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ExerciseList> {
        override fun createFromParcel(parcel: Parcel): ExerciseList {
            return ExerciseList(parcel)
        }

        override fun newArray(size: Int): Array<ExerciseList?> {
            return arrayOfNulls(size)
        }
    }
}