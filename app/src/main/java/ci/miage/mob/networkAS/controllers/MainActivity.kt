package ci.miage.mob.networkAS.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import ci.miage.mob.networkAS.R
import ci.miage.mob.networkAS.database.AppDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createDb() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}