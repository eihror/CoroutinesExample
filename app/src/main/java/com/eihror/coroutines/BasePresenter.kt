package com.eihror.coroutines

import java.lang.ref.WeakReference


abstract class BasePresenter<V> {

    protected var view: WeakReference<V>? = null

    fun bindView(view: V) {
        this.view = WeakReference(view)
    }

    fun unbindView() {
        view = null
    }

    fun getViewRef(): V? {
        return if (view != null) view!!.get() else null
    }

}