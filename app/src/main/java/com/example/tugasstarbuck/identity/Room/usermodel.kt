package com.example.tugasstarbuck.identity.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class usermodel(var name: String?, var email: String?, var password: String?) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\''.toString() +
                ", email='" + email + '\''.toString() +
                ", password='" + password + '\''.toString() +
                '}'.toString()
    }
}