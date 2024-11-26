package vn.edu.hust.studentman

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val hovaten = findViewById<EditText>(R.id.add_name)
        val mssv = findViewById<EditText>(R.id.add_mssv)

        findViewById<Button>(R.id.btn).setOnClickListener {
            val hovaten2 = hovaten.text.toString().trim()
            val mssv2 = mssv.text.toString().trim()

            if (hovaten2.isNotBlank() && mssv2.isNotBlank()) {
                // Gửi dữ liệu về MainActivity
                val resultIntent = Intent().apply {
                    putExtra("studentName", hovaten2)
                    putExtra("studentId", mssv2)
                }
                setResult(RESULT_OK, resultIntent)
                finish() // Đóng Activity
            } else {
                if (hovaten2.isBlank()) {
                    hovaten.error = "Không được để trống họ và tên"
                }
                if (mssv2.isBlank()) {
                    mssv.error = "Không được để trống MSSV"
                }
            }
        }
        findViewById<Button>(R.id.cancel).setOnClickListener{
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
