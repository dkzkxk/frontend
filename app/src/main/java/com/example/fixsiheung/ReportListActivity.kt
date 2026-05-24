package com.example.fixsiheung

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fixsiheung.databinding.ActivityReportListBinding
import com.example.fixsiheung.model.Report

class ReportListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportListBinding
    private lateinit var reportAdapter: ReportAdapter

    //제보화면 데이터 추가
    private lateinit var addReportLauncher: ActivityResultLauncher<Intent>

    //민원데이터 저장하는 리스트
    var reportDataList = mutableListOf<Report>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addReportLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val title = data.getStringExtra("intent_title") ?: "[무제]"
                    val category = data.getStringExtra("intent_category") ?: "기타 일반"
                    val location = data.getStringExtra("intent_location") ?: "한국공대"
                    val content = data.getStringExtra("intent_content") ?: "내용 없음"

                    // 새 민원의 고유 ID 부여 (현재 개수 + 1)
                    val newId = reportDataList.size + 1

                    // 새 Report 데이터 데이터 모델 포맷으로 생성 (초기 공감 0, 상태는 "접수")
                    val newReport = Report(newId, title, content, category, 37.340, 126.733, "시민님", 0, "접수")

                    // 🔥 중요: 최신 글이 맨 위에 와야 하므로 리스트의 0번(맨 앞) 인덱스에 추가합니다!
                    reportDataList.add(0, newReport)

                    // 갱신 신호: 어댑터야 0번에 새 아이템 들어왔으니 화면에 그려줘!
                    reportAdapter.notifyItemInserted(0)

                    // 추가되자마자 리스트 맨 위로 자동 스크롤 이동
                    binding.reportRecyclerView.scrollToPosition(0)
                }
            }
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

        binding.itemaddbtn.setOnClickListener {
            val intent = Intent(this, ReportAddActivity::class.java)
            addReportLauncher.launch(intent)
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
