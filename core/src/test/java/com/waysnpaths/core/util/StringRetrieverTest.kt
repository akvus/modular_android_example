package com.waysnpaths.core.util

import android.content.Context
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StringRetrieverTest {

    @Mock
    lateinit var context: Context

    @InjectMocks
    private lateinit var stringRetriever: StringRetriever

    @Test
    fun getString() {
        val resId = 23
        val string = "sdf"
        whenever(context.getString(resId)).thenReturn(string)

        val result = stringRetriever.getString(resId)

        assertThat(result, equalTo(string))
    }
}