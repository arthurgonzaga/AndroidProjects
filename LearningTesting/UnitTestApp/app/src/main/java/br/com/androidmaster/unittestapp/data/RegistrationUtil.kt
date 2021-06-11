package br.com.androidmaster.unittestapp.data

import java.util.regex.Pattern

object RegistrationUtil {

    // Simulate users database table
    val usersEmail = listOf(
        "test@email.com",
        "joao@email.com"
    )


    /**
     *  The registration is not successful when the user:
     *
     *  1. Name/email/password is empty
     *  2. Email is not using the email pattern
     *  3. Email is already taken
     *  4. Password is less than 5 digits
     *
     */
    fun register(user: User): Boolean{

        if(user.name.isBlank() or
            user.email.isBlank() or
            user.password.isBlank()
        ){
            return false
        }

        if(!checkEmailPatter(user.email)) return false

        if(user.email in usersEmail){
            return false
        }

        if(user.password.count { it.isDigit() } < 6){
            return false
        }

        return true
    }


    fun checkEmailPatter(string: String): Boolean{
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        return EMAIL_ADDRESS_PATTERN.matcher(string).matches()
    }
}