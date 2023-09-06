package com.example.testing

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.RemoteViews
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testing.View.Cart
import com.example.testing.View.Dashboard
import com.example.testing.View.History
import com.example.testing.data.fromJson
import com.example.testing.repository.DogApi
import com.example.testing.viewModel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity(){
    var loaded: Boolean = false

    lateinit var navController:NavController

    lateinit var bottomNavigationView:BottomNavigationView
    lateinit var navHostFragment: NavHostFragment

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"


    lateinit var reloadButton: ImageView
    override fun notify(data: Any) {
        setNotification()
    }

    fun setNotification(){
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, MainActivity::class.java)

        // FLAG_UPDATE_CURRENT specifies that if a previous
        // PendingIntent already exists, then the current one
        // will update it with the latest intent
        // 0 is the request code, using it later with the
        // same method again will get back the same pending
        // intent for future reference
        // intent passed here is to our afterNotification class
        val pendingIntent = NavDeepLinkBuilder(this)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.dogPriceList)
            .setComponentName(MainActivity::class.java)
            .createPendingIntent()
        // RemoteViews are used to use the content of
        // some different layout apart from the current activity layout


        // checking if android version is greater than oreo(API 26) or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        } else {

            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())
    }

    override fun receiveDataFromAll(data: String) {
        /// When the receive Data is called
    }

    override fun receiveDataSeperatly(reciever: Any?) {
        if(reciever==this){
            getTheApiData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController =navHostFragment.navController




// Navigate to the deep destination
        var defaultOpen:String="PriceList"

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)



        // Connect the NavController to the BottomNavigationView
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dashboard -> {
                    popController()
                    navController.navigate(R.id.dashboard)
                    true
                }

                R.id.cart -> {
                    popController()
                    navController.navigate(R.id.cart)
                    true
                }

                R.id.history -> {
                    popController()
                    navController.navigate(R.id.history)
                    //navController.popBackStack(R.id.dashboard,false)
                    true
                }

                R.id.smart -> {
                    popController()
                    navController.navigate(R.id.smart)
                    true
                }

                R.id.activity -> {
                    popController()
                    navController.navigate(R.id.activity)
                    //navController.popBackStack(R.id.dashboard,false)
                    true
                }
                // Add cases for other fragments

                else -> false
            }


        }

        var isEnv:Boolean=false

        val menuItemToDisable = bottomNavigationView.menu.findItem(R.id.smart)
        val menuItemToDisable1 = bottomNavigationView.menu.findItem(R.id.activity)

        // Disable or enable the menu item based on the condition
        menuItemToDisable.isEnabled = isEnv
        menuItemToDisable1.isEnabled = isEnv

        var directNavigate:Boolean=false

        if(directNavigate){
            directNavigation()
        }


        getTheApiData()
//        handleDeepLink(intent)
    }
    private fun handleDeepLink(intent: Intent?) {
        intent?.data?.let { deepLinkUri ->
            navController.handleDeepLink(intent)
        }
    }
    private fun directNavigation(){
        val navInflater = navController.navInflater
        val graph: NavGraph = navInflater.inflate(R.navigation.nav_graph)

        graph.setStartDestination(R.id.cart) // Replace with your desired start destination

        navController.graph = graph
        bottomNavigationView.selectedItemId=R.id.cart

// Specify the ID of the deep destination
        val deepDestinationId = R.id.dogPriceList
        navController.navigate(deepDestinationId)

    }

    private fun popController() {
        navController.popBackStack(R.id.dashboard, true)
        navController.popBackStack(R.id.cart, true)
        navController.popBackStack(R.id.history, true)
    }


    private fun getTheApiData() {


        val dogApi = HomeViewModel.getInstance().create(DogApi::class.java)
        GlobalScope.launch {
            val result = dogApi.getDogs()
            var dog = result.body()?.message ?: "value";
            historyList= historyList.plus(dogImage).distinct()
            if (result.body() != null) {
                Log.d("Dog Value: ", dogImage)
            } else {
                Log.d("Dog Value 2s: ", dogImage)
            }
            sendDataToALl(dog)
//            reloadButton = findViewById(R.id.right_action)
//            reloadButton.setOnClickListener(View.OnClickListener {
//                getTheApiData()
//            })
//            val firstFragment = Dashboard()
//            val secondFragment = Cart()
//            val thirdFragment = History()
//            setCurrentFragment(firstFragment)
//            var bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
//            bottomNavigationView.setOnNavigationItemSelectedListener {
//                when (it.itemId) {
//                    R.id.dashboard -> {
//                        setCurrentFragment(firstFragment)
//                    }
//                    R.id.cart -> {
//                        setCurrentFragment(secondFragment)
//                    }
//                    R.id.history -> {
//                        setCurrentFragment(thirdFragment)
//                    }
//                }
//                true
//            }
//
//            val popMenu: PopupMenu=PopupMenu(this@MainActivity,null)
//            popMenu.inflate(R.menu.bottom_nav_menu)
//            val menu = popMenu.menu
//            bottomNavigationView.setupWithNavController(navController)
        }
    }
//
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp()||super.onSupportNavigateUp()
//    }
//
//
//    private fun setCurrentFragment(fragment: Fragment): FragmentTransaction {
//
//        val fragmentTransaction = supportFragmentManager.beginTransaction().replace(R.id.mainFrame, fragment)
//        fragmentTransaction.detach(fragment).attach(fragment).commit()
//        return fragmentTransaction
//    }
}