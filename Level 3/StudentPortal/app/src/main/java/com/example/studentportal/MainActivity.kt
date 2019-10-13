package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.Builder
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


const val PORTAL_REQUEST_CODE = 100
class MainActivity : AppCompatActivity() {
    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }


    private fun initViews() {
        rvPortal.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        rvPortal.adapter = portalAdapter
        addFab.setOnClickListener { onFabClick() }
        portalAdapter.onItemClick = {
            portal -> onPortalClick(portal.uri)

        }
        portals.add(Portal("Roosters","https://rooster.hva.nl"))
        portalAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun onFabClick() {
        val intent = Intent(this, AddPortal::class.java)
        startActivityForResult(intent, PORTAL_REQUEST_CODE)
    }

    private fun onPortalClick(url: String){
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                PORTAL_REQUEST_CODE ->{
                    val portal = data!!.getParcelableExtra<Portal>(AddPortal.EXTRA_PORTAL)
                    portals.add(portal)
                    portalAdapter.notifyDataSetChanged()

                }
            }
        }
    }

}