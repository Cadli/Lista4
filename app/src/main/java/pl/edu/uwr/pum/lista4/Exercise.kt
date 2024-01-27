package pl.edu.uwr.pum.lista4

import android.os.Parcel
import android.os.Parcelable
import kotlin.random.Random

data class Exercise(
    val content: String,
    val points: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(content)
        parcel.writeInt(points)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Exercise> = object : Parcelable.Creator<Exercise> {
            override fun createFromParcel(parcel: Parcel): Exercise {
                return Exercise(parcel)
            }

            override fun newArray(size: Int): Array<Exercise?> {
                return arrayOfNulls(size)
            }
        }

        fun createRandomExercise(): Exercise {
            val randomPoints = Random.nextInt(1, 6)
            val randomLoremIpsumLength = Random.nextInt(50, 201)

            val loremIpsum = generateLoremIpsum(randomLoremIpsumLength)
            return Exercise(loremIpsum, randomPoints)
        }

        private fun generateLoremIpsum(length: Int): String {
            val loremIpsumWords = listOf(
                "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "sed",
                "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore", "magna", "aliqua"
            )
            val loremIpsum = StringBuilder()
            var currentLength = 0

            while (currentLength < length) {
                val word = loremIpsumWords.random()
                val wordLength = word.length + 1
                if (currentLength + wordLength <= length) {
                    loremIpsum.append("$word ")
                    currentLength += wordLength
                } else {
                    break
                }
            }

            return loremIpsum.toString().trim()
        }
    }
}