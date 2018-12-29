package com.example.saren.lab2

class JSONEntity {
    data class TranslateInfo(
        val code: Int,
        val lang: String,
        val text: ArrayList<String>
    )
}