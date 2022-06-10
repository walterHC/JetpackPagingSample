package org.idnp.jetpackpagingsample

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val cuiText = view.findViewById<TextView>(R.id.textViewCui) as TextView
    val firstNameText = view.findViewById<TextView>(R.id.textViewFirstName) as TextView
    val secondNameText = view.findViewById<TextView>(R.id.textViewSecondName) as TextView

    fun bind(user: User) {
        with(user) {
            cuiText.text = cui.toString()
            firstNameText.text = firstName.toString()
            secondNameText.text = lastName.toString()
            Log.d("MainActivity", cui.toString())
        }
    }
}