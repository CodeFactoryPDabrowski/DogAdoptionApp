package com.example.androiddevchallenge.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androiddevchallenge.data.source.entity.DogDb
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DogDb::class], version = 1)
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogsDao(): DogRoomDao

    companion object {

        private const val DB_NAME = "dogs_db"

        @Volatile
        private var INSTANCE: DogDatabase? = null

        fun getInstance(context: Context): DogDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                DogDatabase::class.java,
                DB_NAME
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val handler = CoroutineExceptionHandler { _, exception ->
                            println("Caught during database creation --> $exception")
                        }

                        CoroutineScope(Dispatchers.IO).launch(handler) {
                            prePopulateAppDatabase(getInstance(context).dogsDao())
                        }
                    }
                })
                .build()

        suspend fun prePopulateAppDatabase(dogRoomDao: DogRoomDao) {
            val dogsToAdopt = listOf(
                DogDb(1, "Azor", "Mix", "Image"),
                DogDb(2, "Max", "Mix", "Image"),
                DogDb(3, "Fifi", "Mix", "Image"),
                DogDb(4, "Szarik", "Mix", "Image"),
                DogDb(5, "Kokos", "Mix", "Image"),
                DogDb(6, "Milki", "Mix", "Image"),
                DogDb(7, "Puma", "Mix", "Image"),
                DogDb(8, "Burek", "Mix", "Image"),
                DogDb(9, "Dexter", "Mix", "Image"),
                DogDb(10, "Lufi", "Mix", "Image"),
            )
            dogRoomDao.save(dogsToAdopt)
        }
    }
}
