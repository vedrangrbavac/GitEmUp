package com.example.gitemup.common.extensions

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Date
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class DateExtKtTest{

    @Test
    fun testFromDateToStringIsValid(){
        val date = Date(1667944702 * 1000L)
        val string = "08-11-2022 22:58:22"
        assertEquals(string, date.fromDateToString())
    }
}