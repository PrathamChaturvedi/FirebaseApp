package com.example.firebaseapp.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.example.firebaseapp.R
import com.example.firebaseapp.utils.FcmEvent
import com.example.firebaseapp.utils.RightAlignedLinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val itemViewModel: ItemViewModel by viewModels()
    private val itemAdapter = ItemAdapter(emptyList())

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private val notificationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Update the RecyclerView when a new notification is received
            //itemViewModel.updateData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize FirebaseApp
        askNotificationPermission(this)
        FirebaseApp.initializeApp(this)

        Firebase.messaging.subscribeToTopic("all")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                }
            }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("jsabfiausdhnf", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d("jsabfiausdhnf", token)
        })

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        //val appBarLayout: AppBarLayout = findViewById(R.id.app_bar_layout)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.setBackgroundColor(Color.WHITE)
        toolbar.setTitleTextColor(Color.BLACK)
        setSupportActionBar(toolbar)

        val layoutManager = RightAlignedLinearLayoutManager(this)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = layoutManager

        itemViewModel.allItems.observe(this) { items ->
            if (items.isNotEmpty()) {
                itemAdapter.updateItems(items)
                Log.d("juhfuyfuyfg", "items: $items")
            } else {
                Log.d("juhfuyfuyfg", "No items received.")
            }
        }

        FcmEvent.itemInserted.observe(this) { itemEntity ->
            itemViewModel.insertItemsAndUpdate(listOf(itemEntity))
        }
    }

    private fun askNotificationPermission(context: Context) {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        // Unregister the BroadcastReceiver when the activity is destroyed
        unregisterReceiver(notificationReceiver)
    }
}