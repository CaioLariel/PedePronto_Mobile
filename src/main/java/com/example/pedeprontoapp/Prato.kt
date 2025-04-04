package com.example.pedeprontoapp

import android.os.Parcel
import android.os.Parcelable

data class Prato(
    val nome: String,
    val descricao: String,
    val preco: Double,
    val imagem: Int,
    var quantidade: Int = 1
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeString(descricao)
        parcel.writeDouble(preco)
        parcel.writeInt(imagem)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Prato> {
        override fun createFromParcel(parcel: Parcel): Prato = Prato(parcel)
        override fun newArray(size: Int): Array<Prato?> = arrayOfNulls(size)
    }
}
