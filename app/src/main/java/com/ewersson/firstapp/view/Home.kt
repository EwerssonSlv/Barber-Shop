package com.ewersson.firstapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ewersson.firstapp.R
import com.ewersson.firstapp.adapter.adapterservices
import com.ewersson.firstapp.databinding.ActivityHomeBinding
import com.ewersson.firstapp.model.services


class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapterservices: adapterservices
    private val listServices: MutableList<services> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val name = intent.extras?.getString("name")

        binding.txtNameUser.text = "Bem-vindo, $name"
        val recyclerViewservices = binding.recyclerServices
        recyclerViewservices.layoutManager = GridLayoutManager(this, 2)
        adapterservices = adapterservices(this, listServices)
        recyclerViewservices.setHasFixedSize(true)
        recyclerViewservices.adapter = adapterservices
        getServices()

        binding.btnAgendar.setOnClickListener {
            val intent = Intent(this, Agendamento::class.java)
            intent.putExtra("nome", name )
            startActivity(intent)
        }

    }

    private fun getServices(){
        val service1 = services(R.drawable.img1, "Corte de Cabelo")
        listServices.add(service1)

        val service2 = services(R.drawable.img2, "Corte de Barba")
        listServices.add(service2)

        val service3 = services(R.drawable.img3, "Lavagem de Cabelo")
        listServices.add(service3)

        val service4 = services(R.drawable.img4, "Tratamento Capilar")
        listServices.add(service4)

    }
}