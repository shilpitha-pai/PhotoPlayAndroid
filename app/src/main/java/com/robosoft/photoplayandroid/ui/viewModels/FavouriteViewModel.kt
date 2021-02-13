package com.robosoft.photoplayandroid.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robosoft.photoplayandroid.data.database.PhotoVideoDbRepository
import com.robosoft.photoplayandroid.data.model.Photo
import com.robosoft.photoplayandroid.utils.NetworkHelper
import com.robosoft.photoplayandroid.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavouriteViewModel(
    private val photoVideoDbRepository: PhotoVideoDbRepository
) : ViewModel() {

    private val TAG = "BASICS"
    private val disposable = CompositeDisposable()

    private val _favoritePhotos = MutableLiveData<Resource<List<Photo>>>()
    val favoritePhotos: LiveData<Resource<List<Photo>>>
        get() = _favoritePhotos

    fun getFavouritePhotos() {
        disposable.addAll(
            photoVideoDbRepository.getFavouritePhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ photos ->
                    _favoritePhotos.postValue(Resource.success(photos))
                    Log.e(TAG, "$photos")
                }, { error ->
                    Log.e("Error", "${error.message}")
                })
        )
    }

    fun favouritePhoto(photo: Photo) {
        photoVideoDbRepository.addPhotoToFavourite(photo)
    }

    fun deletePhoto(photo: Photo) {
        photoVideoDbRepository.deletePhotos(photo)
    }
}
