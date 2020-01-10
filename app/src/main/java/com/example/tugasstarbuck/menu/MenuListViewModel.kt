package com.example.tugasstarbuck.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable

class MenuListViewModel : ViewModel() {

    private val menuService = MenuService.getService()
    var menuList: LiveData<PagedList<Coffee>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 2
    private val menuDataSourceFactory: MenuDataSourceFactory

    init {
        menuDataSourceFactory = MenuDataSourceFactory(compositeDisposable, menuService)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        menuList = LivePagedListBuilder<Int, Coffee>(menuDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<MenuDataSource,
            State>(menuDataSourceFactory.menuDataSourceLiveData, MenuDataSource::state)

    fun retry() {
        menuDataSourceFactory.menuDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return menuList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
