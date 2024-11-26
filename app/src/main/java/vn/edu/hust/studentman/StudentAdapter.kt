import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import vn.edu.hust.studentman.R
import vn.edu.hust.studentman.StudentModel

class StudentAdapter(
  private val students: List<StudentModel>,
  private val context: Context
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

  companion object {
    var selectedPosition: Int = -1
  }

  inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnCreateContextMenuListener {

    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)

    init {
      itemView.setOnCreateContextMenuListener(this)
      itemView.setOnLongClickListener {
        selectedPosition = adapterPosition
        false
      }
    }

    override fun onCreateContextMenu(
      menu: ContextMenu,
      v: View?,
      menuInfo: ContextMenu.ContextMenuInfo?
    ) {
      (context as AppCompatActivity).menuInflater.inflate(R.menu.main_menu2, menu)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(
      R.layout.layout_student_item,
      parent,
      false
    )
    return StudentViewHolder(itemView)
  }

  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]
    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId
  }
}
