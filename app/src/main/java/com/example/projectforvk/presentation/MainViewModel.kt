package com.example.projectforvk.presentation

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectforvk.data.network.model.Object
import com.example.projectforvk.data.network.model.Products
import com.example.projectforvk.data.network.service.APIService
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val apiService: APIService) : ViewModel() {

    private var skip = 0
    private val limit = 20
    private var total = 0

    private val _data = MutableLiveData<Object>()
    val data: LiveData<Object> get() = _data

    private val _isLoader = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoader

    private val _isEmptySearch = MutableLiveData(false)
    val isEmptySearch: LiveData<Boolean> get() = _isEmptySearch

    private val _products = MutableLiveData<List<Products>>(emptyList())
    val products: LiveData<List<Products>> get() = _products

    fun fetchData() {
        viewModelScope.launch {
            try {
                if (skip <= total) {
                    _isLoader.value = true
                    val response = apiService.getRequest(limit, skip)

                    Timber.tag("fetchData").d("products: %s", response.products)
                    Timber.tag("MainViewModel").d("Response: %s", response)

                    _products.value = products.value!!.toMutableList().apply {
                        addAll(response.products)
                    }
                    total = response.total
                    skip += limit
                }
            } catch (e: Exception) {
                Timber.tag("MainViewModel").e(e, "Error fetching data: %s", e.message)
            }
            _isLoader.value = false
        }
    }

    fun getProductsSearching(text: String) {
        viewModelScope.launch {
            try {
                _isLoader.value = true
                val response = apiService.getProducts(text)
                Timber.tag("MainViewModel").d("Response: %s", response)
                _data.value = response

                if (response.products.isEmpty()) {
                    _isEmptySearch.value = true
                } else {
                    _products.value = response.products
                    _isEmptySearch.value = false
                }
                total = 0
                skip = 0

            } catch (e: Exception) {
                Timber.tag("MainViewModel").e("Error fetching data: %s", e.message)
            }
            _isLoader.value = false
        }
    }
}