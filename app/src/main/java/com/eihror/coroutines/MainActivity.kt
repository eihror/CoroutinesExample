package com.eihror.coroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.eihror.coroutines.customView.CustomProgressLayout
import com.eihror.coroutines.extensions.toast
import com.eihror.coroutines.model.Post
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mListPosts: ArrayList<Post>
    private lateinit var adapter: MainAdapter

    private val mPresenter: MainPresenter by lazy { MainPresenter() }

    private lateinit var progress: CustomProgressLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mListPosts = arrayListOf()
        progress = CustomProgressLayout(applicationContext)

        setupList()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.bindView(this)

        adapter.clearAll()
        mPresenter.getPosts()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.unbindView()
    }

    private fun setupList() {
        adapter = MainAdapter(mListPosts, { post: Post -> clickListener(post) }, { })
        val llm = LinearLayoutManager(applicationContext)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }

    private fun clickListener(post: Post) {
        this.toast(post.title.toString())
    }

    override fun showLoading(show: Boolean) {
        progress.show(show)
    }

    override fun onGetPosts(listPosts: List<Post>) {
        if (listPosts.isNotEmpty()) {
            mListPosts.addAll(listPosts)
        }

        adapter.addToList(mListPosts)
    }

    override fun onError(show: Boolean, message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {

    }
}
