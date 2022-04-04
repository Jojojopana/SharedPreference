package com.binar.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.binar.sharedpreference.databinding.ActivityMainBinding
import java.util.jar.Attributes

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sharedPreferences : SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener{
            val id: Int = Integer.parseInt(binding.etInputId.text.toString())
            val name: String = binding.etInputName.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key",id)
            editor.putString("name_key",name)
            editor.apply()
            Toast.makeText(this@MainActivity, "Data Saved",Toast.LENGTH_SHORT).show()
        }

        binding.btnView.setOnClickListener{
            val sharedIdValue = sharedPreferences.getInt("id_key",0)
            val sharedNameValue = sharedPreferences.getString("name_key","defaultname")
            if(sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")){
                binding.tvShowName.setText("defaultname: ${sharedNameValue}").toString()
                binding.tvShowId.setText("defaultid: ${sharedIdValue.toString()}")
                Toast.makeText(this@MainActivity,"Data View Kosong", Toast.LENGTH_SHORT).show()
            } else {
                binding.tvShowName.setText(sharedNameValue).toString()
                binding.tvShowId.setText(sharedIdValue.toString())
                Toast.makeText(this@MainActivity, "Data View Ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener{
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.tvShowName.setText("")
            binding.tvShowId.setText("")
            Toast.makeText(this@MainActivity, "Data Clear", Toast.LENGTH_SHORT).show()
        }
    }
}