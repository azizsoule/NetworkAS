package ci.miage.mob.networkAS.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ci.miage.mob.networkAS.database.dao.GraphEntityDao
import ci.miage.mob.networkAS.database.entity.GraphEntity

@Database(entities = [GraphEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun graphEntityDao() : GraphEntityDao
}