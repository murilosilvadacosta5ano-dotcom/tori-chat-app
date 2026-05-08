package com.example.compose.jetchat.ai

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AIClient {

    private val client = OkHttpClient()

    // SEU TOKEN (igual Discord bot)
    private const val TOKEN = "TORI_SECRET_123"

    suspend fun ask(prompt: String): String {

        val json = """
        {
          "message": "$prompt"
        }
        """.trimIndent()

        val body = json.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("http://127.0.0.1:8000/ai")
            .addHeader("Authorization", "Bearer $TOKEN")
            .post(body)
            .build()

        return withContext(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            response.body?.string() ?: "sem resposta"
        }
    }
}
