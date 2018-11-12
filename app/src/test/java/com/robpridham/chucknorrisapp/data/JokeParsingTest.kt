package com.robpridham.chucknorrisapp.data

import com.robpridham.chucknorrisapp.json.JacksonParser
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class JokeParsingTest {

    companion object {
        private const val SINGLE_JOKE_RESPONSE = """
            { "type": "success", "value": { "id": 101, "joke": "Sample joke from JSON", "categories": [] } }
        """
    }

    @Test
    fun `parse single joke response`() {
        val parser = JacksonParser()
        val singleJoke = parser.fromJson(SINGLE_JOKE_RESPONSE, SingleJoke::class.java)
        assertNotNull(singleJoke)
        assertEquals(singleJoke.value, Joke(101, "Sample joke from JSON"))
    }
}