package com.robosoft.photoplayandroid.data.database.dao

import androidx.room.*
import com.robosoft.photoplayandroid.data.model.Photo
import io.reactivex.Single

@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photo: Photo)

    @Delete
    fun deletePhoto(photo: Photo)

    @Query("SELECT * FROM Photo")
    fun getFavouritePhotos(): Single<List<Photo>>

    @Query("SELECT * FROM Photo WHERE id =:value")
    fun getFavoriteForID(value: Int): Single<Photo>
}
