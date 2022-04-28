package ci.miage.mob.networkAS.database.dao

import androidx.room.*
import ci.miage.mob.networkAS.database.entity.GraphEntity

@Dao
interface GraphEntityDao {
    @Query("SELECT * FROM graph")
    fun getAll() : List<GraphEntity>

    @Query("SELECT * FROM graph WHERE id = :id")
    fun getOne(id : Int)

    @Update
    fun update(graphEntity: GraphEntity)

    @Insert
    fun save(graphEntity: GraphEntity)

    @Delete
    fun delete(graphEntity: GraphEntity)
}