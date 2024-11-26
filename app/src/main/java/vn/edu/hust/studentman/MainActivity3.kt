package vn.edu.hust.studentman

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val hovaten = findViewById<EditText>(R.id.edit_name)
        val mssv = findViewById<EditText>(R.id.edit_mssv)

        val currentName = intent.getStringExtra("studentName")
        val currentId = intent.getStringExtra("studentId")
        val position = intent.getIntExtra("position", -1)

        hovaten.setText(currentName)
        mssv.setText(currentId)

        findViewById<Button>(R.id.ok).setOnClickListener{
            val hovaten3 = hovaten.text.toString()
            val mssv3 = mssv.text.toString()

            if(hovaten3.isNotBlank() && mssv3.isNotBlank()){
                val resultIntent = Intent().apply{
                    putExtra("studentName", hovaten3)
                    putExtra("studentId", mssv3)
                    putExtra("position", position)
                }
                setResult(RESULT_OK,resultIntent)
                finish()
            }
        }
        findViewById<Button>(R.id.cancel).setOnClickListener{
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}