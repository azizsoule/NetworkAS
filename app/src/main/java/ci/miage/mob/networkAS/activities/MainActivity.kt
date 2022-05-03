package ci.miage.mob.networkAS.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.room.Room
import ci.miage.mob.networkAS.R
import ci.miage.mob.networkAS.database.AppDatabase
import ci.miage.mob.networkAS.models.enums.Mode
import ci.miage.mob.networkAS.views.GraphView

class MainActivity : AppCompatActivity() {

    lateinit var  graphView : GraphView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        graphView = findViewById<GraphView>(R.id.graph_view)
        supportActionBar?.title = "Network Ano N'Ganza Jean-noel / Soulé Arémou Malick Aziz"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.mode_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_node_mode -> {
                graphView.mode = Mode.ADD_NODE
            }

            R.id.add_link_mode -> {
                graphView.mode = Mode.ADD_LINK
            }

            R.id.edition_mode -> {
                graphView.mode = Mode.EDIT
            }
        }
        return true
    }

    fun createDb() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}