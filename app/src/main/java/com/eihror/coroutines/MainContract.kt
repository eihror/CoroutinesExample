package com.eihror.coroutines

import com.eihror.coroutines.model.Post

interface MainContract {

    interface View : BaseContract.View {

        /**
         * This method could go to the BaseContract depending on the project needs
         */
        fun showLoading(show: Boolean)

        fun onGetPosts(listPosts: List<Post>)
        fun onError(show: Boolean, message: String)

    }

    interface Presenter {
        //Function to get posts
        fun getPosts()
    }

}