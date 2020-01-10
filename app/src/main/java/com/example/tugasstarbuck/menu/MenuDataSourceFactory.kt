package com.example.tugasstarbuck.menu

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import androidx.paging.DataSource

class MenuDataSourceFactory (
    private val compositeDisposable: CompositeDisposable,
    private val menuService: MenuService
) : DataSource.Factory<Int, Coffee>(){

    val menuDataSourceLiveData = MutableLiveData<MenuDataSource>()

    override fun create() : DataSource<Int, Coffee>{
        val menuDataSource = MenuDataSource(menuService, compositeDisposable)
        menuDataSourceLiveData.postValue(menuDataSource)
        return menuDataSource
    }
}
