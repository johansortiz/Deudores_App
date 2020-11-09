package com.johans.deudores.ui.actualizar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.johans.deudores.Deudores
import com.johans.deudores.R
import com.johans.deudores.data.database.entities.Deudor
import com.johans.deudores.databinding.FragmentActualizarBinding

class ActualizarFragment : Fragment() {

    private lateinit var binding: FragmentActualizarBinding
    private var isSearching = true
    private var idDeudor = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actualizar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentActualizarBinding.bind(view)

        binding.buttonBuscar.setOnClickListener {
            val nombre = binding.nombreBuscarEditText.text.toString()
            when {
                nombre.isEmpty() -> {
                    binding.nombreBuscarEditText.error = getString(R.string.nombres)
                    binding.nombreBuscarEditText.requestFocus()
                }
                else -> {
                    binding.telefonoEditText.visibility = View.VISIBLE
                    binding.valorEditText.visibility = View.VISIBLE
                    val telefono = binding.telefonoEditText.text.toString()
                    val valor = binding.valorEditText.text.toString()

                    val deudorDAO = Deudores.database.DeudorDAO()

                    if (isSearching) { // buscando
                        val deudor = deudorDAO.searchDeudor(nombre)
                        if (deudor != null) {
                            isSearching = false
                            binding.buttonBuscar.text = getString(R.string.actualizar)
                            idDeudor = deudor.id
                            binding.telefonoEditText.setText(deudor.telefono)
                            binding.valorEditText.setText(deudor.valor?.toString())
                        } else {
                            Toast.makeText(context, "No existe", Toast.LENGTH_SHORT).show()
                        }
                    } else {//actualizando
                        val deudor = Deudor(idDeudor, nombre, telefono, valor.toLong())

                        deudorDAO.updateDeudor(deudor)
                        isSearching = true
                        binding.buttonBuscar.text = getString(R.string.buscar)
                        Toast.makeText(context, "Deudor actualizado con éxito", Toast.LENGTH_SHORT)
                            .show()

                    }
                }
            }

        }
    }

    companion object
}