package th.ac.su.sqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import th.ac.su.sqlitedemo.adapter.ListStudentAdapter
import th.ac.su.sqlitedemo.dbhelper.DBHelper
import th.ac.su.sqlitedemo.model.Student

class MainActivity : AppCompatActivity() {

    var listStudent = ArrayList<Student>()

    lateinit var listViewStudent:ListView

    lateinit var dbHelper:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var student1 = Student(124,"worawiboon","worawiboon@gmail.com")
//        var student2 = Student(222,"chalermkiat","chalermkiat@gmail.com")
//        var student3 = Student(333,"wutiporn","wutiporn@gmail.com")
//        var student4 = Student(444,"thongdee","thongdee@gmail.com")
//        var student5 = Student(555,"pornswat","pornswat@gmail.com")
//        var student6 = Student(666,"jaklern","jaklern@gmail.com")
//        var student7 = Student(777,"pornbapaa","pornbapaa@gmail.com")
//
//        listStudent.add(student1)
//        listStudent.add(student2)
//        listStudent.add(student3)
//        listStudent.add(student4)
//        listStudent.add(student5)
//        listStudent.add(student6)
//        listStudent.add(student7)

        dbHelper = DBHelper(this)

        listViewStudent = findViewById<ListView>(R.id.listviewStudent)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val edtId = findViewById<EditText>(R.id.edtId)
        val edtName = findViewById<EditText>(R.id.edtName)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)



        btnAdd.setOnClickListener(){
            val student = Student(edtId.text.toString().toInt(),edtName.text.toString(),edtEmail.text.toString())

            dbHelper.addStudent(student)
            reloadData()
        }

        reloadData()
    }

    fun reloadData(){

        listStudent = dbHelper.allStudent
        val adapter = ListStudentAdapter(this@MainActivity,listStudent)
        listViewStudent.adapter = adapter


    }
}
