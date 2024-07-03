package com.example.jitsimeet

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavoritesActivity : AppCompatActivity() {

    // Define the database instance
//    private lateinit var db: AppDatabase

    // Define the user DAO
//    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the database instance
//        db = DatabaseBuilder.getInstance(applicationContext)

        // Get UserDao from the database
//        userDao = db.userDao()
//        val dao = intent.getSerializableExtra("dao") as UserDao



        setContent {
            // Access user list
//            val userList = runBlocking { userDao.getAllUsers() }

//            FavoritesContent(userList, onAddUser = { username, roomName ->
//                onAddUser(username, roomName)
//            })
            FavoritesContent2(this.application)
        }
    }
//    fun onAddUser(username : String , roomName : String) {
//        val user = User(username, roomName)
//        runBlocking {
//            userDao.insert(user)
//        }
//    }
}


//@Composable
//fun FavoritesContent(users: LiveData<List<User?>?>, onAddUser: (String, String) -> Unit) {
//    val context = LocalContext.current
//    val clipboardManager = remember { context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }
//    val dialogOpen = remember { mutableStateOf(false) }
//    users.value?.let { users1 ->
//
//        LazyColumn {
//            items(users1) { user ->
//                Box(
//                    modifier = Modifier
//                        .clickable {
//                            val clip = ClipData.newPlainText("Room Name", user?.roomName ?: "hello_buddy")
//                            clipboardManager.setPrimaryClip(clip)
//                            Toast.makeText(
//                                context,
//                                "Room Name Copied to Clipboard",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        } // Fixed missing closing parenthesis
//                ) {
//                    Column {
//                        if (user != null) {
//                            Text(text = user.name)
//                        }
//                        if (user != null) {
//                            Text(text = user.roomName)
//                        }
//                    }
//
//                }
//            }
//        }
//    }
//    Button(onClick = {dialogOpen.value = true}) {
//        Text("Add New User")
//    }
//    var username = ""
//    var roomName = ""
//    if (dialogOpen.value) {
//        AlertDialog(
//            onDismissRequest = { dialogOpen.value = false },
//            title = { Text("Add New User") },
//            text = {
//                Column {
//                    TextField(
//                        value = username,
//                        onValueChange = { username = it },
//                        label = { Text("Username") }
//                    )
//                    TextField(
//                        value = roomName,
//                        onValueChange = { roomName = it },
//                        label = { Text("Room Name") }
//                    )
//                }
//            },
//            confirmButton = {
//                Button(
//                    onClick = {
//                        // Call the callback with the new user data
//                        onAddUser(username,roomName)
//                        dialogOpen.value = false
//                    }
//                ) {
//                    Text("Add")
//                }
//            },
//            dismissButton = {
//                Button(
//                    onClick = { dialogOpen.value = false }
//                ) {
//                    Text("Cancel")
//                }
//            }
//        )
//    }
//}

@Composable
fun FavoritesContent1(application: Application) {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    val userDao = db.userDao()

    var users: List<User> by remember { mutableStateOf(emptyList()) }
    val dialogOpen = remember { mutableStateOf(false) }
    var flag = remember { mutableStateOf(false) }

    Column {
        Row {
            Button(onClick = {
                // Retrieve favorites from the database
//            CoroutineScope(Dispatchers.IO).launch {
                flag.value = true
                CoroutineScope(Dispatchers.IO).launch {
                    users = userDao.getAllUsers()
                }
                users.forEachIndexed { index, weatherData ->
                    Log.d("WeatherData", "Index: $index, Data: $weatherData")
                }
//
//                Log.d("x",users.toString())
//                users = getUsersonline(userDao, context)
//            }
//                LaunchedEffect(Unit) {
//                    try {
//                        val userListLiveData = userDao.getAllUsers()
//                        userListLiveData.observeForever { userList ->
//                            users = userList
//                                ?.filterNotNull()
//                                ?: emptyList()
//                        }
//                    } catch (e: Exception) {
//                        // Handle database operation error
//                        Toast.makeText(context, "Error loading favorites", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
            }) {
                Text(text = "Get Favourite")
            }

            Button(onClick = { dialogOpen.value = true }) {
                Text("Add New User")
            }

            var username by rememberSaveable { mutableStateOf("") }
            var roomName by rememberSaveable { mutableStateOf("") }


            if (dialogOpen.value) {
                // Show dialog for adding new user
                AlertDialog(
                    onDismissRequest = { dialogOpen.value = false },
                    title = { Text("Add New User") },
                    text = {
                        Column {
                            // TextFields for entering username and room name

                            TextField(
                                value = username,
                                onValueChange = { username = it },
                                label = { Text("Username") }
                            )
                            TextField(
                                value = roomName,
                                onValueChange = { roomName = it },
                                label = { Text("Room Name") }
                            )
                        }
                    },
                    confirmButton = {
                        // Confirm button to add new user
                        Button(
                            onClick = {
                                val Newuser = User(username, roomName)
                                CoroutineScope(Dispatchers.IO).launch {
                                    userDao.insert(Newuser)
                                }
                                username = ""
                                roomName = ""
                                dialogOpen.value = false
//                            CoroutineScope(Dispatchers.IO).launch {
//                                try {
//                                    // Insert new user into database
//                                    val user = User(username, roomName)
//                                    userDao.insert(user)
//                                    dialogOpen.value = false
//                                } catch (e: Exception) {
//                                    // Handle database operation error
//                                    Toast.makeText(context, "Error adding user", Toast.LENGTH_SHORT)
//                                        .show()
//                                }
//                            }
                            }
                        ) {
                            Text("Add")
                        }
                    },
                    dismissButton = {
                        // Dismiss button to cancel adding new user
                        Button(
                            onClick = { dialogOpen.value = false }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Column {
            if (flag.value == true) {
                val clipboardManager =
                    remember { context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

                LazyColumn {
                    items(users) { user ->
                        Box(
                            modifier = Modifier
                                .clickable {
                                    val clip = ClipData.newPlainText("Room Name", user.roomName)
                                    clipboardManager.setPrimaryClip(clip)
                                    Toast.makeText(
                                        context,
                                        "Room Name Copied to Clipboard",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        ) {
                            Row {
                                Text(text = user.name)
                                Spacer(modifier = Modifier.weight(0.5f))
                                Text(text = user.roomName)
                            }

                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesContent2(application: Application) {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    val userDao = db.userDao()

    var users: List<User> by remember { mutableStateOf(emptyList()) }
    val dialogOpen = remember { mutableStateOf(false) }
    var flag = remember { mutableStateOf(false) }

    Column {
        Row {
            Button(onClick = {
                // Retrieve favorites from the database
                flag.value = true
                CoroutineScope(Dispatchers.IO).launch {
                    users = userDao.getAllUsers()
                }
                Toast.makeText(context,"Swipe Right to Delete",Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Get Favourite")
            }
            Spacer(modifier = Modifier.weight(0.2f))

            Button(onClick = { dialogOpen.value = true }) {
                Text("Add New User")
            }

            var username by rememberSaveable { mutableStateOf("") }
            var roomName by rememberSaveable { mutableStateOf("") }


            if (dialogOpen.value) {
                // Show dialog for adding new user
                AlertDialog(
                    onDismissRequest = { dialogOpen.value = false },
                    title = { Text("Add New User") },
                    text = {
                        Column {
                            // TextFields for entering username and room name
                            TextField(
                                value = username,
                                onValueChange = { username = it },
                                label = { Text("Username") }
                            )
                            TextField(
                                value = roomName,
                                onValueChange = { roomName = it },
                                label = { Text("Room Name") }
                            )
                        }
                    },
                    confirmButton = {
                        // Confirm button to add new user
                        Button(
                            onClick = {
                                val Newuser = User(username, roomName)
                                CoroutineScope(Dispatchers.IO).launch {
                                    userDao.insert(Newuser)
                                }
                                username = ""
                                roomName = ""
                                dialogOpen.value = false
                            }
                        ) {
                            Text("Add")
                        }
                    },
                    dismissButton = {
                        // Dismiss button to cancel adding new user
                        Button(
                            onClick = { dialogOpen.value = false }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Column {
            if (flag.value == true) {
                val clipboardManager =
                    remember { context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

                LazyColumn {
                    items(users) { user ->
                        SwipeToDismiss(
                            state = rememberDismissState(confirmValueChange = {
                                if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                                    // Handle the item dismissal here, e.g., remove the item from the list
                                    // users.remove(user)
                                    CoroutineScope(Dispatchers.IO).launch {
                                        userDao.delete(user)
                                    }
                                    flag.value = false
                                    true // This indicates that the dismissal is confirmed
                                } else {
                                    false
                                }
                            }),
                            background = {
                                val color = Color.Transparent
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color)
                                        .padding(horizontal = 20.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.White
                                    )
                                }
                            },
                            directions = setOf(DismissDirection.StartToEnd),
                            dismissContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                        .clickable {
                                            val clip = ClipData.newPlainText("Room Name", user.roomName)
                                            clipboardManager.setPrimaryClip(clip)
                                            Toast.makeText(
                                                context,
                                                "Room Name Copied to Clipboard",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                ) {
                                    Row {
                                        Text(text = user.name)
                                        Spacer(modifier = Modifier.weight(0.5f))
                                        Text(text = user.roomName)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
