package com.robosoft.photoplayandroid.data.database

import com.robosoft.photoplayandroid.data.database.dao.PhotosDao
import com.robosoft.photoplayandroid.data.model.Photo


class PhotoVideoDbRepository(private val photosDao: PhotosDao) {

    fun addPhotoToFavourite(photo: Photo) = photosDao.insertPhoto(photo)

    fun getFavouritePhotos() = photosDao.getFavouritePhotos()

    fun deletePhotos(photo: Photo) = photosDao.deletePhoto(photo)
}
