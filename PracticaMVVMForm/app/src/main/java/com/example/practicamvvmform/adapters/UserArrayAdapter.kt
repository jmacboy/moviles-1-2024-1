package com.example.practicamvvmform.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.example.practicamvvmform.models.User

class UserArrayAdapter(
    context: Context,
    resource: Int,
    objects: MutableList<User>
) :
    ArrayAdapter<User>(context, resource, objects) {
}