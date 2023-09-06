//package com.example.testing.View
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.NavHostFragment
//import androidx.navigation.ui.setupWithNavController
//import androidx.viewpager2.widget.ViewPager2
//import com.example.testing.R
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.android.material.tabs.TabLayout
//
//class TabContainerFragment : Fragment() {
//
//    private lateinit var viewPager: ViewPager2
//    private lateinit var viewPagerTabLayout: TabLayout
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_tab_container, container, false)
//
//        // Set up BottomNavigationView as before
//        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_nav)
//        bottomNavigationView.setupWithNavController(navController)
//
//        // Set up ViewPager2
//        viewPager = view.findViewById(R.id.view_pager)
//        viewPagerTabLayout = view.findViewById(R.id.view_pager_tab_layout)
//
//        // Create a list of fragments for the ViewPager2
//        val viewPagerFragments = listOf(
//            ViewPagerFragment1(),
//            ViewPagerFragment2(),
//            // Add more ViewPager2 fragments
//        )
//
//        val viewPagerAdapter = ViewPagerFragmentAdapter(this, viewPagerFragments)
//        viewPager.adapter = viewPagerAdapter
//
//        // Attach the TabLayout to the ViewPager2
//        TabLayoutMediator(viewPagerTabLayout, viewPager) { tab, position ->
//            // Customize tab labels if needed
//            tab.text = "Page $position"
//        }.attach()
//
//        return view
//    }
//}