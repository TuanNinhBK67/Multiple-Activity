package vn.edu.hust.studentman

import StudentAdapter
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

  private val students = mutableListOf(
    StudentModel("Nguyễn Văn An", "SV001"),
    StudentModel("Trần Thị Bảo", "SV002"),
    StudentModel("Lê Hoàng Cường", "SV003"),
    StudentModel("Phạm Thị Dung", "SV004"),
    StudentModel("Đỗ Minh Đức", "SV005"),
    StudentModel("Vũ Thị Hoa", "SV006"),
    StudentModel("Hoàng Văn Hải", "SV007"),
    StudentModel("Bùi Thị Hạnh", "SV008"),
    StudentModel("Đinh Văn Hùng", "SV009"),
    StudentModel("Nguyễn Thị Linh", "SV010"),
    StudentModel("Phạm Văn Long", "SV011"),
    StudentModel("Trần Thị Mai", "SV012"),
    StudentModel("Lê Thị Ngọc", "SV013"),
    StudentModel("Vũ Văn Nam", "SV014"),
    StudentModel("Hoàng Thị Phương", "SV015"),
    StudentModel("Đỗ Văn Quân", "SV016"),
    StudentModel("Nguyễn Thị Thu", "SV017"),
    StudentModel("Trần Văn Tài", "SV018"),
    StudentModel("Phạm Thị Tuyết", "SV019"),
    StudentModel("Lê Văn Vũ", "SV020")
  )

  private lateinit var studentAdapter: StudentAdapter

  // Khởi tạo ActivityResultLauncher để nhận dữ liệu từ MainActivity2
  private val addStudentLauncher =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
      if (result.resultCode == RESULT_OK) {
        val data = result.data
        if (data != null) {
          val studentName = data.getStringExtra("studentName")
          val studentId = data.getStringExtra("studentId")
          if (!studentName.isNullOrBlank() && !studentId.isNullOrBlank()) {
            // Thêm sinh viên vào danh sách
            students.add(StudentModel(studentName, studentId))
            studentAdapter.notifyDataSetChanged() // Cập nhật RecyclerView
          }
        }
      }
    }
  private  val editStudentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
    if(result.resultCode == RESULT_OK){
      val data = result.data
      if(data != null){
        val studentName = data.getStringExtra("studentName")
        val studentId = data.getStringExtra("studentId")
        val pos = data.getIntExtra("position", -1)
        if(!studentName.isNullOrBlank() && !studentId.isNullOrBlank()){
          students[pos].studentName = studentName
          students[pos].studentId = studentId
          studentAdapter.notifyItemChanged(pos)
        }
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    studentAdapter = StudentAdapter(students, this)
    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_students)

    recyclerView.apply {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }
    registerForContextMenu(recyclerView)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.main_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.add_student -> {
        // Mở MainActivity2 để thêm sinh viên mới
        val intent = Intent(this, MainActivity2::class.java)
        addStudentLauncher.launch(intent)
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onContextItemSelected(item: MenuItem): Boolean {
    val pos = StudentAdapter.selectedPosition
    when(item.itemId){
      R.id.edit->{
        val intent = Intent(this, MainActivity3::class.java).apply {
          putExtra("studentName", students[pos].studentName)
          putExtra("studentId", students[pos].studentId)
          putExtra("position", pos)
        }
        editStudentLauncher.launch(intent)
        return true
      }
      R.id.delete->{
        students.removeAt(pos)
        studentAdapter.notifyItemRemoved(pos)
        return true
      }
    }
    return super.onContextItemSelected(item)
  }
}
