package com.zhouchen.application.sample.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zhouchen.application.sample.R
import com.zhouchen.application.sample.databinding.ItemSampleBinding
import com.zhouchen.datalayer.model.Sample
import com.zhouchen.application.sample.viewmodel.SampleViewModel

open class SampleListAdapter: RecyclerView.Adapter<SampleListAdapter.ViewHolder>() {
    private lateinit var sampleList:List<Sample>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSampleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_sample, parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setLast(position == itemCount -1)
        holder.bind(sampleList[position])
    }

    override fun getItemCount(): Int {
        return if(::sampleList.isInitialized) sampleList.size else 0
    }

    fun updateSampleList(postList:List<Sample>) {
        this.sampleList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemSampleBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = SampleViewModel()
        private var mLast: Boolean = false

        fun bind(sample:Sample){
            viewModel.bind(sample)
            binding.viewmodel = viewModel
        }

        //for testing
        fun getLast() :Boolean {
            return mLast
        }

        fun setLast(last: Boolean) {
            mLast = last
        }
    }
}