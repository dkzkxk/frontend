package com.example.fixsiheung

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fixsiheung.model.Report
import com.example.fixsiheung.databinding.ItemReportBinding

//item_report.xml 랑 세트

class ReportAdapter(private val reportList: List<Report>) :
    RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val currentReport = reportList[position]
        holder.bind(currentReport)
    }

    override fun getItemCount(): Int = reportList.size

    class ReportViewHolder(private val binding: ItemReportBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(report: Report) {      //어댑터 본체
            //report에 이미지url 추가 되면 이미지 코드 추가하기
            binding.itemTitle.text = report.title
            binding.itemCategory.text = report.category
            binding.itemstatus.text = report.status
            binding.itemlikeCount.text = "공감 ${report.likeCount}"  //리스트에 공감+숫자로 보이게함
        }
    }
}