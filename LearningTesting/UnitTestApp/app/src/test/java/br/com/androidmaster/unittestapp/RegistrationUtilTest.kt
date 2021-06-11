package br.com.androidmaster.unittestapp

import br.com.androidmaster.unittestapp.data.RegistrationUtil
import br.com.androidmaster.unittestapp.data.User
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RegistrationUtilTest {


    @Test
    fun `should return false when name is empty`() {

        val user = User(
            name = "",
            email = "arthur@gmail.com",
            password = "123123"
        )

        val result = RegistrationUtil.register(user)

        assertThat(result).isFalse()
    }

    @Test
    fun `should return true when string is using the email pattern`(){
        val result = RegistrationUtil.checkEmailPatter("arthur@gmail.com")
        assertThat(result).isTrue()
    }


    @Test
    fun `should return false when email is not using the email pattern`(){

        val user = User(
            name = "Arthur",
            email = "test@email.com",
            password = "123123"
        )

        val result = RegistrationUtil.register(user)

        assertThat(result).isFalse()
    }

    @Test
    fun `should return false when email is already taken`(){

        val user = User(
            name = "Test",
            email = "test@email.com",
            password = "123123"
        )

        val result = RegistrationUtil.register(user)

        assertThat(result).isFalse()
    }

    @Test
    fun `should return false when password is less than 5 digits`(){

        val user = User(
            name = "Arthur",
            email = "arthur@gmail.com",
            password = "12"
        )

        val result = RegistrationUtil.register(user)

        assertThat(result).isFalse()
    }
}