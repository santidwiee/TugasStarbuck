package com.example.tugasstarbuck.menu

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class MenuDataSource (
    private val menuService : MenuService,
    private val compositeDisposable : CompositeDisposable) : PageKeyedDataSource<Int, Coffee>(){

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable : Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Coffee>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            menuService.getMenu(1, params.requestedLoadSize)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(response.coffee,
                            null, 2)
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action{loadInitial(params, callback)})
                    })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Coffee>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            menuService.getMenu(params.key, params.requestedLoadSize)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(response.coffee,
                            params.key + 1)
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action{loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Coffee>) {
    }

    private fun updateState(state: State){
        this.state.postValue(state)
    }

    fun retry(){
        if(retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    private fun setRetry(action: Action?){
        retryCompletable = if(action == null)  null else Completable.fromAction(action)
    }
}
