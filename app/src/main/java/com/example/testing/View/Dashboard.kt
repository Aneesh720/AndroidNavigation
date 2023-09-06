package com.example.testing.View


import android.app.Dialog
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testing.BaseFragment
import com.example.testing.MainActivity
import com.example.testing.R
import com.example.testing.cartList
import com.example.testing.data.CartDetails
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Dashboard : BaseFragment() {
    //Default dog Image
    private var dogImage:String= "https://images.dog.ceo/breeds/malinois/n02105162_7461.jpg"

    override fun receiveDataFromAll(data: String) {
        /// Manage the Received Data
        Log.d("image",data)
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            setImage(data)
        }

    }


    fun setImage(data:String){
        dogImage=data
        Picasso.get()
            .load(dogImage)
            .into(imageView)

    }

    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)

        imageView = view.findViewById(R.id.image)
        Log.d("Image", dogImage)

        val dashboard:Button= view.findViewById(R.id.dashboardButton)

        dashboard.setOnClickListener{
            callTheNOtif()

        }
        toolBar(view)

        imageView.setOnClickListener(View.OnClickListener {
            ////Adding a Dialog Box
            ///Open the Form
            openForm()

        })
        return view
    }

    fun createNotification(context: Context, title: String, message: String): Notification {
        val channelId = "channel_id"
        val notificationId = 1  // Unique ID for the notification

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.background)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentIntent(pendingIntent).setChannelId("1")
        return notificationBuilder.build()
    }

    private fun toolBar(view:View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val titleTextView = toolbar.findViewById<TextView>(R.id.toolbar_title)
        val leftMenuIcon = toolbar.findViewById<ImageView>(R.id.left_icon)
        val rightMenuIcon = toolbar.findViewById<ImageView>(R.id.right_action)

        // Customize the toolbar attributes
        titleTextView.text = "DashBoard"
        leftMenuIcon.isVisible=true
        rightMenuIcon.isVisible=true

        leftMenuIcon.setOnClickListener{
            val intent = Intent(requireContext(), Login::class.java)
            startActivity(intent)
        }

        rightMenuIcon.setOnClickListener{
            Log.d("API","Api called")
            callTheApi()
        }
        titleTextView.setOnClickListener {
            callTheDialog()
        }
    }

    private fun callTheDialog() {
        val dialog = Dialog(requireContext())


        dialog.setContentView(R.layout.custom_layout)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.setCancelable(true)
        dialog.window?.setGravity(Gravity.TOP)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        dialog.show()

    }


    private fun openForm() {
        ///Adding in the Stack
        findNavController().navigate(R.id.action_dashboard_to_form)
    }


    private fun showDialog(dogImage: String) {
        val dialog = Dialog(requireContext())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_layout)

        val editTextView: EditText = dialog.findViewById(R.id.editText)
        val button: Button = dialog.findViewById(R.id.button)

        button.setOnClickListener {
            ///Store the Value
            var cartDetails: CartDetails =
                CartDetails(image = dogImage, price = editTextView.text.toString().toDouble())
            cartList=cartList.plus(cartDetails).distinct()
            Log.d("price Size", cartList.size.toString())
            dialog.dismiss()
        }

        dialog.show()
    }

}