package com.example.jitsimeet

//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.google.firebase.Firebase
//import com.google.firebase.auth.auth
//import com.google.firebase.initialize
//import org.jitsi.meet.sdk.JitsiMeet
//import org.jitsi.meet.sdk.JitsiMeetActivity
//import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
//import java.net.URL
//
//import android.content.BroadcastReceiver
//import android.content.IntentFilter
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import org.jitsi.meet.sdk.BroadcastEvent
//import org.jitsi.meet.sdk.BroadcastIntentHelper
//import timber.log.Timber
//import java.net.MalformedURLException
//
//class MainActivity : ComponentActivity() {
//
////    private val broadcastReceiver = MyBroadcastReceiver()
//    private var isReceiverRegistered = false
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val serverURL: URL
//        serverURL = try {
//            // When using JaaS, replace "https://meet.jit.si" with the proper serverURL
//            URL("https://meet.jit.si")
//        } catch (e: MalformedURLException) {
//            e.printStackTrace()
//            throw RuntimeException("Invalid server URL!")
//        }
//        val isReceiverRegistered = savedInstanceState?.getBoolean(KEY_RECEIVER_REGISTERED, false) ?: false
//
//        val defaultOptions = JitsiMeetConferenceOptions.Builder()
//            .setServerURL(serverURL)
//            // When using JaaS, set the obtained JWT here
//            //.setToken("MyJWT")
//            // Different features flags can be set
//            //.setFeatureFlag("toolbox.enabled", false)
//            //.setFeatureFlag("filmstrip.enabled", false)
//            .setFeatureFlag("welcomepage.enabled", false)
//            .build()
//        JitsiMeet.setDefaultConferenceOptions(defaultOptions)
//        registerReceiverIfNeeded()
//
////        registerForBroadcastMessages()
//        setContent {
//            MyApp()
//        }
//    }
//
//    private fun registerReceiverIfNeeded() {
//        if (!isReceiverRegistered) {
//            val intentFilter = IntentFilter().apply {
//                // Add appropriate actions to the intent filter
//            }
//            registerReceiver(broadcastReceiver, intentFilter)
//            isReceiverRegistered = true
//        }
//    }
//    override fun onSaveInstanceState(outState: Bundle) {
//        // Save the receiver registration state
//        outState.putBoolean(KEY_RECEIVER_REGISTERED, isReceiverRegistered)
//        super.onSaveInstanceState(outState)
//    }
//
//    inner class MyBroadcastReceiver : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            onBroadcastReceived(intent)
//        }
//    }
//
//    private fun registerForBroadcastMessages() {
//        val intentFilter = IntentFilter()
//
//        for (type in BroadcastEvent.Type.values()) {
//            intentFilter.addAction(type.action)
//        }
//
//        registerReceiver(broadcastReceiver, intentFilter)
//    }
//
//    override fun onDestroy() {
//        unregisterReceiver(broadcastReceiver)
//        super.onDestroy()
//    }
//
//    private val broadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            onBroadcastReceived(intent)
//        }
//    }
//
//    private fun onBroadcastReceived(intent: Intent?) {
//        if (intent != null) {
//            val event = BroadcastEvent(intent)
//            when (event.type) {
//                BroadcastEvent.Type.CONFERENCE_JOINED -> Timber.i("Conference Joined with url%s", event.getData()["url"])
//                BroadcastEvent.Type.PARTICIPANT_JOINED -> Timber.i("Participant joined%s", event.getData()["name"])
//                else -> Timber.i("Received event: %s", event.type)
//            }
//        }
//    }
//
//    private fun hangUp() {
//        val hangupBroadcastIntent: Intent = BroadcastIntentHelper.buildHangUpIntent()
//        sendBroadcast(hangupBroadcastIntent)
//    }
//    companion object {
//        private const val KEY_RECEIVER_REGISTERED = "receiver_registered"
//    }
//}
//
//@Composable
//fun MyApp() {
//    val context = LocalContext.current
//    var conferenceName by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Button(
//            onClick = {
//                // Handle favorites button click
//                Toast.makeText(context, "Favorites clicked!", Toast.LENGTH_SHORT).show()
//                val intent = Intent(context, FavoritesActivity::class.java)
//                context.startActivity(intent)
//            }
//        ) {
//            Text("Favorites")
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Enter Conference Name:")
//            Spacer(modifier = Modifier.height(8.dp))
//            TextField(
//                value = conferenceName,
//                onValueChange = { conferenceName = it },
//                label = { Text("Conference Name") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    if (conferenceName.isNotBlank()) {
//                        joinConference(context, conferenceName)
//                    }
//                }
//            ) {
//                Text("Join Conference")
//            }
//        }
//    }
//}
//
//
//fun joinConference(context: Context, conferenceName: String) {
//    val options = JitsiMeetConferenceOptions.Builder()
//        .setRoom(conferenceName)
//        .build()
//    JitsiMeetActivity.launch(context, options)
//}

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import java.io.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        val db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "database-name"
//        ).build()
//        val userDao = db.userDao()

        setContent {
//            LoginActivityTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MyApp(FirebaseAuth.getInstance())
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MyApp(auth: FirebaseAuth) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var isLogin by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Jitsi Meet",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (!isLogin) {
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {
                if (isLogin) {
                    // Sign in with Firebase
                    signInWithFirebase(auth, email, password, context)
                } else {
                    // Register with Firebase
                    registerWithFirebase(auth, email, password, context)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isLogin) "Login" else "Register")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { isLogin = !isLogin },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(if (isLogin) "Need an account? Register" else "Already have an account? Login")
        }
    }
}

private fun signInWithFirebase(
    auth: FirebaseAuth,
    email: String,
    password: String,
//    userDao: UserDao,
    context: Context
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(context as Activity) { task ->
            if (task.isSuccessful) {
                Log.d("login", "signInWithFirebase:success")
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, LoginActivity::class.java)
//                intent.putExtra("dao", userDao)
                context.startActivity(intent)
            } else {
                // Login failed, show an error message
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
}

private fun registerWithFirebase(
    auth: FirebaseAuth,
    email: String,
    password: String,
    context: Context
) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(context as Activity) { task ->
            if (task.isSuccessful) {
                // Registration successful
                // You might want to automatically sign in the user after registration
                // For now, just show a toast
                Log.d("register", "registerWithFirebase:success")
                Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
            } else {
                // Registration failed, show an error message
                Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
            }
        }
}
