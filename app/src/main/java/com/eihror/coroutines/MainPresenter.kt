package com.eihror.coroutines

import com.eihror.coroutines.extensions.getErrors
import com.eihror.coroutines.network.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private var scope: GlobalScope = GlobalScope

    override fun getPosts() {

        val view: MainContract.View? = getViewRef()

        if (view != null) {
            scope.launch(Dispatchers.Main) {
                view.showLoading(true)

                val response = RetrofitFactory.getPosts().await()
                if (response.isSuccessful) {
                    response.body()?.let {
                        view.onGetPosts(it)
                    }
                    view.showLoading(false)
                } else {
                    view.showLoading(false)
                    view.onError(true, response.getErrors())
                }

            }
        }

    }

}