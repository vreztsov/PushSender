package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .setDatabaseUrl(dbUrl)
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
          "author": "Сергей Городецкий",
          "text": "Здравствуй, весенняя первая травка!
Как распустилась? Ты рада теплу?
Знаю, y вас там веселье и давка,
Дружно работают в каждом yглy.
Высyнyть листик иль синий цветочек
Каждый спешит молодой корешок
Раньше, чем ива из ласковых почек
Первый покажет зеленый листок."          
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}