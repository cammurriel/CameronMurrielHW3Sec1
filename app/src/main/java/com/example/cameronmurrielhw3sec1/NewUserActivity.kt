package com.example.cameronmurrielhw3sec1
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class NewUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        Toast.makeText(this, "hi there", Toast.LENGTH_SHORT).show()

         findViewById<Button>(R.id.createAccount_button).setOnClickListener {
             val firstName = findViewById<EditText>(R.id.name_editText).text.toString()
             val password = findViewById<EditText>(R.id.pw_editText).text.toString()
             val confirmPassword = findViewById<EditText>(R.id.confirmPW_editText).text.toString()

             val db = FirebaseFirestore.getInstance()
             val user: MutableMap<String, Any> = HashMap()
             user["firstName"] = firstName

               if (password == confirmPassword) {

                   db.collection("users")
                       .add(user)
                       .addOnSuccessListener {
                           Log.d("dbfirebase", "save: ${user}")
                       }
                       .addOnFailureListener {
                           Log.d("dbfirebase Failed", "${user}")
                       }


                 Toast.makeText(applicationContext, "You successfully created an account", Toast.LENGTH_SHORT).show()
             }
             else{
                 Toast.makeText(applicationContext, "Error Passwords dont match", Toast.LENGTH_SHORT).show()
             }
         }



    }
}