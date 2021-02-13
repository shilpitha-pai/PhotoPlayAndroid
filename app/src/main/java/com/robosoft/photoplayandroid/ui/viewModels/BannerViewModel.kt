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

class BannerViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _bannerImage = MutableLiveData<Resource<PhotoResults>>()
    val bannerImage: LiveData<Resource<PhotoResults>>
        get() = _bannerImage

    private val _networkError = MutableLiveData<Void>()
    val networkError: LiveData<Void>
        get() = _networkError

    fun getBannerImage() {
        if(networkHelper.isNetworkConnected()) {
            disposable.addAll(
                mainRepository.getBannerImage()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        _bannerImage.postValue(Resource.success(result.body()))
                    }, {
                        Log.e("Error", "${it.message}")
                    })
            )
        }
        else{
            Log.e("Error", "NO network")

        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
