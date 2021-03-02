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
                DogDb(
                    1,
                    "Azor",
                    "Terrier",
                    "https://images.dog.ceo/breeds/terrier-patterdale/patterdale-terrier-1330018870tnN.jpg"
                ),
                DogDb(
                    2,
                    "Max",
                    "Mastiff-bull",
                    "https://images.dog.ceo/breeds/mastiff-bull/n02108422_1549.jpg"
                ),
                DogDb(
                    3,
                    "Fifi",
                    "keeshond",
                    "https://images.dog.ceo/breeds/keeshond/n02112350_7893.jpg"
                ),
                DogDb(
                    4,
                    "Szarik",
                    "Terrier-australian",
                    "https://images.dog.ceo/breeds/terrier-australian/n02096294_989.jpg"
                ),
                DogDb(
                    5,
                    "Kokos",
                    "Terrier-scottish",
                    "https://images.dog.ceo/breeds/terrier-scottish/n02097298_6781.jpg"
                ),
                DogDb(
                    6,
                    "Milki",
                    "Terrier-dandie",
                    "https://images.dog.ceo//breeds//terrier-dandie//n02096437_2639.jpg"
                ),
                DogDb(
                    7,
                    "Puma",
                    "Husky",
                    "https://images.dog.ceo//breeds//husky//n02110185_14479.jpg"
                ),
                DogDb(
                    8,
                    "Burek",
                    "Terrier-kerryblue",
                    "https://images.dog.ceo//breeds//terrier-kerryblue//n02093859_2124.jpg"
                ),
                DogDb(
                    9,
                    "Dexter",
                    "Cairn",
                    "https://images.dog.ceo//breeds//cairn//n02096177_6700.jpg"
                ),
                DogDb(
                    10,
                    "Lufi",
                    "Chow",
                    "https://images.dog.ceo//breeds//chow//n02112137_2290.jpg"
                ),
            )
            dogRoomDao.save(dogsToAdopt)
        }
    }
}
