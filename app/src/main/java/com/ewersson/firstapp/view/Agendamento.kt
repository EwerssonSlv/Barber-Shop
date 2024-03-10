package com.ewersson.firstapp.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.ewersson.firstapp.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()
        val nome = intent.extras?.getString("name").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if(dayOfMonth < 10){
                dia = "0$dayOfMonth"
            }

            mes = if(monthOfYear < 10){
                "" + (monthOfYear + 1)
            } else{
                (monthOfYear +1).toString()
            }

            data = "$dia / $mes / $year"
        }
        binding.timePicker.setOnTimeChangedListener { _ , hourOfDay, minute ->
            val minuto: String

            minuto = if(minute < 10){
                "0$minute"
            } else{
                minute.toString()
            }

            hora = "$hourOfDay:$minuto"
        }
        binding.timePicker.setIs24HourView(true)

        binding.btnAgendar.setOnClickListener{

            val barbeiro1 = binding.barber1
            val barbeiro2 = binding.barber2
            val barbeiro3 = binding.barber3

            when{
                hora.isEmpty() -> {
                    mensagem(it, "Preencha o horário", "#FF0000")
                }
                hora < "8:00" && hora > "19:00" -> {
                    mensagem(it, "Estamos fechados nesse horário - Horário de atendimento: 08:00 às 19:00!", "#FF0000")
                }
                data.isEmpty() -> {
                    mensagem(it, "Coloque uma data!", "#FF0000")
                }
                barbeiro1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    saveAgnd(it, nome, "Guilherme", data, hora)
                }
                barbeiro2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    saveAgnd(it, nome, "Pedro", data, hora)
                }
                barbeiro3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    saveAgnd(it, nome, "Lucas", data, hora)
                }
                else ->{
                    mensagem(it, "Escolha um barbeiro!", "#FF0000")
                }

            }
        }
    }

    private fun mensagem(view: View, mensagem:String, cor: String){
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

    private fun saveAgnd(view: View, cliente: String, barbeiro: String, data: String, hora: String){

        val db = FirebaseFirestore.getInstance()

        val dadosCliente = hashMapOf(
            "cliente" to cliente,
            "barbeiro" to barbeiro,
            "data" to data,
            "hora" to hora
        )

        db.collection("agendamento").document(cliente).set(dadosCliente).addOnCompleteListener{
            mensagem(view, "Agendamento realizado com sucesso!", "#FF03DAC5")
        }.addOnFailureListener{
            mensagem(view, "Erro no servidor!", "#FF0000")
        }

    }
}