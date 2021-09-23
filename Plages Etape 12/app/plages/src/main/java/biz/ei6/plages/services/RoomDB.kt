package biz.ei6.plages.services

import androidx.room.*
import biz.ei6.plages.Plage

@Dao
interface PlagesDAO {
    @Insert
    fun insertPlage(plage: Plage)

    @get:Query("SELECT * from plages")
    val selectPlages: Array<Plage>
}

@Database(
    entities = [Plage::class],
    version = 1,
    exportSchema = true
)
abstract class PlagesDatabase : RoomDatabase() {
    abstract fun plagesDao(): PlagesDAO
}

