package de.chrisward.theyworkforyou

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import de.chrisward.theyworkforyou.view.LordListFragment
import de.chrisward.theyworkforyou.view.MPListFragment
import javax.inject.Inject
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {
    private var drawer: DrawerLayout? = null

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        setupActionBar()

        if (savedInstanceState == null || supportFragmentManager.findFragmentByTag(Constants.FRAGMENT_MPS) == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.main_content, MPListFragment(), Constants.FRAGMENT_MPS)
                    .commit()
        }

    }

    private fun setupActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)

        drawer!!.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_mps) {
            var fragment = supportFragmentManager
                    .findFragmentByTag(Constants.FRAGMENT_MPS)

            val currentFragment = supportFragmentManager.findFragmentById(R.id.main_content)

            if (fragment === currentFragment) {
                drawer!!.closeDrawer(GravityCompat.START)
                return true
            }

            if (fragment == null) {
                fragment = MPListFragment()
            }

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .addToBackStack(null)
                    .commit()
        } else if (id == R.id.nav_lords) {
            var fragment = supportFragmentManager
                    .findFragmentByTag(Constants.FRAGMENT_LORDS)

            val currentFragment = supportFragmentManager.findFragmentById(R.id.main_content)

            if (fragment === currentFragment) {
                drawer!!.closeDrawer(GravityCompat.START)
                return true
            }

            if (fragment == null) {
                fragment = LordListFragment()
            }

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .addToBackStack(null)
                    .commit()
        } else if (id == R.id.nav_debates) {
            //TODO: Action
        } else if (id == R.id.nav_written_answers) {
            //TODO: Action
        }
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }
}
