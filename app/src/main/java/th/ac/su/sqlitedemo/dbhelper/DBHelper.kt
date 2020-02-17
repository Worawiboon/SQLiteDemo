package th.ac.su.sqlitedemo.dbhelper

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import th.ac.su.sqlitedemo.model.Student
import java.security.AccessControlContext

class DBHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VESION){

    companion object{
        private  val DATABASE_VESION = 1
        private  val DATABASE_NAME = "university.db"
        private  val TABLE_NAME = "student"
        private  val COL_ID = "id"
        private  val COL_NAME = "name"
        private  val COL_EMAIL = "email"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY,$COL_NAME TEXT,$COL_EMAIL TEXT)")
        db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val allStudent:ArrayList<Student>
    get(){
        val listStudent = ArrayList<Student>()

        var SELECT_QUERY = "SELECT* FROM $TABLE_NAME"
        val db:SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = db.rawQuery(SELECT_QUERY,null)

        if(cursor.moveToFirst()){
            do{

                val student = Student()
                student.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                student.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                student.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))

                listStudent.add(student)

            }while (cursor.moveToNext())

        }

        return  listStudent
    }


    fun addStudent(student: Student){

        val db:SQLiteDatabase = this.writableDatabase

        val contentValue = ContentValues()
        contentValue.put(COL_ID,student.id)
        contentValue.put(COL_NAME,student.name)
        contentValue.put(COL_EMAIL,student.email)

        db.insert(TABLE_NAME,null,contentValue)
        db.close()

    }

}