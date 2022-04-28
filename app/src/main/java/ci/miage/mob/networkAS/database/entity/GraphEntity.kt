package ci.miage.mob.networkAS.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "graph")
data class GraphEntity(@PrimaryKey val id : Int, val label : String, val json : String) {
}