package br.com.androidmaster.tddspendtracker.models

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{

    @Test
    fun whenInputIsValid(){
        val amount = 12
        val title = "Hamburger"
        val result = Validator.validate(amount, title)
        assertThat(result).isTrue()
    }

    @Test
    fun whenInputIsInvalid(){
        val amount = 0
        val title = "sadasd"

        val result = Validator.validate(amount, title)
        assertThat(result).isFalse()
    }
}