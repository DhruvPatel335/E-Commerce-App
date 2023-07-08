package com.example.ecommerceapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.constants.NODE_AUTHORS
import com.example.ecommerceapp.model.Author
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class AuthorViewModel : ViewModel() {
    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result

    fun addAuthor(author: Author, authorId: String) {
        val dbAuthors =
            Firebase.database("https://e-commerceapp-7c242-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference(NODE_AUTHORS)

        author.id = authorId
        val sessionId = UUID.randomUUID().toString()
        dbAuthors.child(author.id!!).child(sessionId).setValue(author)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }
}