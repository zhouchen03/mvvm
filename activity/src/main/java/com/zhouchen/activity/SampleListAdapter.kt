package com.zhouchen.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zhouchen.activity.databinding.ItemSampleBinding
import com.zhouchen.base.ui.IApp
import com.zhouchen.datalayer.model.Sample
import com.zhouchen.viewmodel.SampleViewModel

class SampleListAdapter(private val app : IApp): RecyclerView.Adapter<SampleListAdapter.ViewHolder>() {
    private lateinit var sampleList:List<Sample>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSampleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_sample, parent, false)
        return ViewHolder(app, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sampleList[position])
    }

    override fun getItemCount(): Int {
        return if(::sampleList.isInitialized) sampleList.size else 0
    }

    fun updateSampleList(postList:List<Sample>){
        this.sampleList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(app : IApp, private val binding: ItemSampleBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = SampleViewModel(app)

        fun bind(sample:Sample){
            viewModel.bind(sample)
            binding.viewModel = viewModel
        }
    }
}