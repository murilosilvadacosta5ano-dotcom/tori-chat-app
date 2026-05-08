package com.example.jetchat.ai

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AIClient {

    private val client = OkHttpClient()

    suspend fun ask(prompt: String): String {

        val json = """
        {
          "prompt": "$prompt",
          "n_predict": 200
        }
        """.trimIndent()

        val body = json.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("http://127.0.0.1:8000/completion")
            .post(body)
            .build()

        return withContext(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            response.body?.string() ?: "sem resposta"
        }
    }
}

