// 🛠️ ReportAddActivity.kt 파일 전체에 덮어씌우기
package com.example.fixsiheung

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fixsiheung.databinding.ActivityReportAddBinding

class ReportAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "민원 제보하기"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 💡 민원 신청하기 버튼 클릭 이벤트
        binding.btnSubmit.setOnClickListener {
            val title = binding.addTitle.text.toString().trim()
            val category = binding.addCategory.text.toString().trim()
            val location = binding.addLocation.text.toString().trim()
            val content = binding.addContent.text.toString().trim()

            // 하나라도 안 적은 칸이 있으면 경고 문구를 띄우고 진행을 막습니다.
            if (title.isEmpty() || category.isEmpty() || location.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "모든 항목을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 📦 인텐트(택배 상자) 생성 후 데이터 집어넣기
            val resultIntent = Intent()
            resultIntent.putExtra("intent_title", title)
            resultIntent.putExtra("intent_category", category)
            resultIntent.putExtra("intent_location", location)
            resultIntent.putExtra("intent_content", content)

            // 결과 성공(RESULT_OK) 신호와 함께 택배 보내기
            setResult(RESULT_OK, resultIntent)
            finish() // 제보 화면을 종료하고 리스트 화면으로 돌아감
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}