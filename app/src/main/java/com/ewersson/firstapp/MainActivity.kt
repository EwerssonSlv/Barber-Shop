package com.ewersson.firstapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ewersson.firstapp.databinding.ActivityMainBinding
import com.ewersson.firstapp.view.Home
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnSignin.setOnClickListener{
            val name = binding.editName.text.toString()
            val password = binding.editPassword.text.toString()

            when{
                name.isEmpty() -> {
                    mensagem(it, "Preencha seu nome!")
                }
                password.isEmpty() -> {
                    mensagem(it, "Preencha sua senha!")
                }
                password.length <= 5 -> {
                    mensagem(it, "Sua senha deve ter no mínimo de 6 caracteres")
                } else -> {
                    goHome(name)
                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String){
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000"))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

    private fun goHome(name: String){
        val intent = Intent(this, Home::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }
}