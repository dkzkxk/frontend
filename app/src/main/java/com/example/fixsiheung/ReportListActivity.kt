package com.example.fixsiheung

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fixsiheung.databinding.ActivityReportListBinding
import com.example.fixsiheung.model.Report

class ReportListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportListBinding
    private lateinit var reportAdapter: ReportAdapter

    var reportDataList = mutableListOf<Report>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemaddbtn.setOnClickListener {
            val intent = Intent(this, ReportAddActivity::class.java)
            startActivity(intent)
        }

        initDummyData()

        val defaultSortedList = reportDataList.sortedByDescending { it.id }
        reportDataList.clear()
        reportDataList.addAll(defaultSortedList)

        reportAdapter = ReportAdapter(reportDataList)
        binding.reportRecyclerView.adapter = reportAdapter
        binding.reportRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.Mainbtn.setOnClickListener {
            finish()
        }

        binding.sortfastbtn.setOnClickListener {
            sortByfastCount()
        }

        binding.sortlikebtn.setOnClickListener {
            sortByLikeCount()
        }
    }

    private fun initDummyData() {
        reportDataList.add(
            Report(
                4,
                "민원 제목",
                "민원 내용.",
                "카테고리",
                37.340,
                126.733,
                "칭호창",
                6,
                "처리중"
            )
        )
        reportDataList.add(
            Report(
                1,
                "정문 앞 보도블록 파손",
                "보도블록이 튀어나와 있어 통행이 위험합니다.",
                "시설물 파손",
                37.340,
                126.733,
                "민원왕",
                5,
                "처리중"
            )
        )
        reportDataList.add(
            Report(
                2,
                "공원 내 쓰레기 무단 투기",
                "벤치 주변에 쓰레기가 너무 많아요.",
                "쓰레기",
                37.341,
                126.732,
                "클린시민",
                12,
                "접수"
            )
        )
        reportDataList.add(
            Report(
                3,
                "가로등 점등 불량",
                "밤에 너무 어두워서 무섭습니다.",
                "가로등",
                37.342,
                126.731,
                "야간산책러",
                2,
                "해결"
            )
        )
    }

    //id 역순 정렬해 최신순 정렬됨
    private fun sortByfastCount() {
        val sortedList = reportDataList.sortedByDescending { it.id }

        reportDataList.clear()
        reportDataList.addAll(sortedList)

        reportAdapter.notifyDataSetChanged() //새로고침
    }

    //공감 역순 정렬해 공감순 정렬됨
    private fun sortByLikeCount() {
        val sortedList = reportDataList.sortedByDescending { it.likeCount }

        reportDataList.clear()
        reportDataList.addAll(sortedList)

        reportAdapter.notifyDataSetChanged() //새로고침
    }
}
