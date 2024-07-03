package com.example.jitsimeet
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.URL

import android.content.BroadcastReceiver
import android.content.IntentFilter
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import org.jitsi.meet.sdk.BroadcastEvent
import org.jitsi.meet.sdk.BroadcastIntentHelper
import timber.log.Timber
import java.net.MalformedURLException

class LoginActivity : ComponentActivity() {

    //    private val broadcastReceiver = MyBroadcastReceiver()
    private var isReceiverRegistered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val serverURL: URL
        serverURL = try {
            URL("https://meet.jit.si")
//            URL("https://8x8.vc")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            throw RuntimeException("Invalid server URL!")
        }
        isReceiverRegistered = savedInstanceState?.getBoolean(KEY_RECEIVER_REGISTERED, false) ?: false

        val defaultOptions = JitsiMeetConferenceOptions.Builder()
            .setServerURL(serverURL)
            // When using JaaS, set the obtained JWT here
            //.setToken("MyJWT")
            // Different features flags can be set
            //.setFeatureFlag("toolbox.enabled", false)
            //.setFeatureFlag("filmstrip.enabled", false)
            .setFeatureFlag("welcomepage.enabled", false)
            .build()
        JitsiMeet.setDefaultConferenceOptions(defaultOptions)
        registerReceiverIfNeeded()

//        registerForBroadcastMessages()
        setContent {
            MyApp()
        }
    }

    private fun registerReceiverIfNeeded() {
        if (!isReceiverRegistered) {
            val intentFilter = IntentFilter().apply {
                // Add appropriate actions to the intent filter
            }
            registerReceiver(broadcastReceiver, intentFilter)
            isReceiverRegistered = true
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        // Save the receiver registration state
        outState.putBoolean(KEY_RECEIVER_REGISTERED, isReceiverRegistered)
        super.onSaveInstanceState(outState)
    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            onBroadcastReceived(intent)
        }
    }

    private fun registerForBroadcastMessages() {
        val intentFilter = IntentFilter()

        for (type in BroadcastEvent.Type.values()) {
            intentFilter.addAction(type.action)
        }

        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            onBroadcastReceived(intent)
        }
    }

    private fun onBroadcastReceived(intent: Intent?) {
        if (intent != null) {
            val event = BroadcastEvent(intent)
            when (event.type) {
                BroadcastEvent.Type.CONFERENCE_JOINED -> Timber.i("Conference Joined with url%s", event.getData()["url"])
                BroadcastEvent.Type.PARTICIPANT_JOINED -> Timber.i("Participant joined%s", event.getData()["name"])
                else -> Timber.i("Received event: %s", event.type)
            }
        }
    }

    private fun hangUp() {
        val hangupBroadcastIntent: Intent = BroadcastIntentHelper.buildHangUpIntent()
        sendBroadcast(hangupBroadcastIntent)
    }
    companion object {
        private const val KEY_RECEIVER_REGISTERED = "receiver_registered"
    }
}

@Composable
fun MyApp() {
    val context = LocalContext.current
    var conferenceName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // Handle favorites button click
                Toast.makeText(context, "Favorites clicked!", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, FavoritesActivity::class.java)
//                intent.putExtra("dao", userDao as Serializable)
                context.startActivity(intent)
            }
        ) {
            Text("Favorites")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Enter Conference Name:")
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = conferenceName,
                onValueChange = { conferenceName = it },
                label = { Text("Conference Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (conferenceName.isNotBlank()) {
                        joinConference(context, conferenceName)
                    }
                }
            ) {
                Text("Join Conference")
            }
        }
    }
}


fun joinConference(context: Context, conferenceName: String) {
    val options = JitsiMeetConferenceOptions.Builder()
        .setRoom(conferenceName)
        .build()
    JitsiMeetActivity.launch(context, options)
}
