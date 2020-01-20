package com.sylvan.kotlin_wanandroid.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.base.BaseActivity
import com.sylvan.kotlin_wanandroid.ui.fragment.*
import kotlinx.android.synthetic.main.content_main.*
import toast

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val homeFragment by lazy { HomeFragment() }
    private val squareFragment by lazy { SquareFragment() }
    private val systemFragment by lazy { SystemFragment() }
    private val wecahtFragment by lazy { WeChatFragment() }
//    private val navFragment by lazy { NavigationFragment() }
    private val projectFragment by lazy { ProjectFragment() }

    private val fragmentList = arrayListOf<Fragment>()
    private val titleList = arrayOf(
        R.string.title_home,
        R.string.title_square,
        R.string.title_system,
        R.string.title_wx,
//        R.string.title_nav,
        R.string.title_project
    )

    init {
        fragmentList.add(homeFragment)
        fragmentList.add(squareFragment)
        fragmentList.add(systemFragment)
        fragmentList.add(wecahtFragment)
//        fragmentList.add(navFragment)
        fragmentList.add(projectFragment)
    }

    override fun setLayoutId(): Int = R.layout.activity_main

    override fun cancelRequest() {
    }

    override fun initImmBar() {
        super.initImmBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewpager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_square -> {
                viewpager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_system -> {
                viewpager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_wx -> {
                viewpager.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
//            R.id.navigation_navigation -> {
//                viewpager.currentItem = 4
//                return@OnNavigationItemSelectedListener true
//            }
            R.id.navigation_project -> {
                viewpager.currentItem = 5
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        bottomNavView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        viewpager.run {
            offscreenPageLimit = titleList.size
            adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getItem(position: Int) = fragmentList[position]

                override fun getCount() = titleList.size

                override fun getPageTitle(position: Int) = getString(titleList[position])

            }
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    bottomNavView.menu.getItem(position).isChecked = true
                }
            })
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                toast("hhhhhhhhhhhhhhhhhhhhh")
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
