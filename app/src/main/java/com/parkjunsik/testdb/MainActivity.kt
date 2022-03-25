package com.parkjunsik.testdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.parkjunsik.testdb.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var db: LoginDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = LoginDatabase.getInstance(applicationContext)!!
        refreshLogin()

        binding.btn.setOnClickListener {
            addLogin()
            refreshLogin()
            startLogin()
        }
    }

    private fun startLogin() {
        Toast.makeText(this,"success!!",Toast.LENGTH_SHORT).show()
        Log.d("login","success!!")
    }

    private fun addLogin() {
        val username = binding.etID.text.toString()
        val password = binding.etPW.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            db.loginDao().insetLogin(LoginEntity(username, password))
        }
    }

    private fun refreshLogin(){
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async {
                db.loginDao().getLogin()
            }.await()

            if(data!=null) {
                binding.etID.setText(data.username)
                binding.etPW.setText(data.password)
            }
        }
    }
}