package com.zhouchen.application.sample.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.zhouchen.application.sample.R
import com.zhouchen.application.sample.databinding.ActivityScrollingBinding
import com.zhouchen.application.sample.di.component.DaggerScrollingActivityComponent
import com.zhouchen.application.sample.viewmodel.SampleListViewModel
import com.zhouchen.base.ui.BaseActivity
import com.zhouchen.application.sample.viewmodel.ViewModelFactory
import com.zhouchen.datalayer.api.IAccess
import javax.inject.Inject

class ScrollingActivity : BaseActivity() {
    @Inject
    lateinit var access : IAccess

    private lateinit var binding: ActivityScrollingBinding
    private val viewModel: SampleListViewModel by viewModels { ViewModelFactory(access) }
    private var errorSnackbar: Snackbar? = null

    override fun createComponent() {
        DaggerScrollingActivityComponent
            .builder()
            .appSubcomponent(getAppSubcomponent())
            .activity(this)
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityScrollingBinding>(this, R.layout.activity_scrolling)
        binding.sampleList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        // Set the LifecycleOwner to be able to observe LiveData objects
        binding.lifecycleOwner = this
        // Bind ViewModel
        binding.viewmodel = viewModel

        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Do some thing", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}