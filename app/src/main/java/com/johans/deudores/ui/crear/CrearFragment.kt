package com.johans.deudores.ui.crear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.johans.deudores.Deudores
import com.johans.deudores.R
import com.johans.deudores.data.database.dao.DeudorDAO
import com.johans.deudores.data.database.entities.Deudor
import com.johans.deudores.databinding.FragmentCrearBinding
import java.sql.Types.NULL

class CrearFragment : Fragment() {

    private lateinit var binding: FragmentCrearBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCrearBinding.bind(view)

        binding.guardarBtnCrear.setOnClickListener {
            val nombre = binding.nombreInputText.text.toString()
            val telefono = binding.telefonoInputText.text.toString()
            val valor1 = binding.valorInputText.text.toString()

            when {
                nombre.isEmpty() -> {
                    binding.nombreInputText.error = getString(R.string.nombres)
                    binding.nombreInputText.requestFocus()
                }
                telefono.isEmpty() -> {
                    binding.telefonoInputText.error = getString(R.string.telefono)
                    binding.telefonoInputText.requestFocus()
                }
                valor1.isEmpty() -> {
                    binding.valorInputText.error = getString(R.string.deuda)
                    binding.valorInputText.requestFocus()
                }
                else -> {
                    val valor = valor1.toLong()
                    val deudor = Deudor(NULL, nombre, telefono, valor)
                    val deudorDAO: DeudorDAO = Deudores.database.DeudorDAO()
                    deudorDAO.insertDeudor(deudor)
                    Toast.makeText(context, "Deudor agregado con éxito", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    companion object
}