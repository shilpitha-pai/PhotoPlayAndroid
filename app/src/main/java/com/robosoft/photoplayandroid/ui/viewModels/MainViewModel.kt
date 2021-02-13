package com.robosoft.photoplayandroid.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robosoft.photoplayandroid.data.model.PhotoResults
import com.robosoft.photoplayandroid.data.network.repository.MainRepository
import com.robosoft.photoplayandroid.utils.NetworkHelper
import com.robosoft.photoplayandroid.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _images = MutableLiveData<Resource<PhotoResults>>()
    val images: LiveData<Resource<PhotoResults>>
        get() = _images

    private val _networkError = MutableLiveData<String>()
    val networkError: LiveData<String>
        get() = _networkError

    fun searchPhotos(query: String, itemCount: Int) {
        if (networkHelper.isNetworkConnected()) {
            disposable.addAll(
                mainRepository.searchPhotos(query, itemCount)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(::onSuccess, ::onFailure)
            )
        } else {
            _networkError.postValue("No network")
        }
    }

    private fun onSuccess(value: Response<PhotoResults>) {
        _images.postValue(Resource.success(value.body()))

    }

    private fun onFailure(value: Throwable?) {
        Log.e("Error", "${value}")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
