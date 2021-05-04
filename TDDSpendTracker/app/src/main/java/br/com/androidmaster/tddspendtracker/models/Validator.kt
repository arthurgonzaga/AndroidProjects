package br.com.androidmaster.tddspendtracker.models

object Validator{

    fun validate(amount: Int, title: String): Boolean{
        return ((amount > 0) and title.isNotEmpty())
    }
}