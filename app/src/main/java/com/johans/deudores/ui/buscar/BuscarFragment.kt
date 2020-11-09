package com.johans.deudores.ui.buscar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.johans.deudores.Deudores
import com.johans.deudores.R
import com.johans.deudores.data.database.dao.DeudorDAO
import com.johans.deudores.databinding.FragmentBuscarBinding

class BuscarFragment : Fragment() {

    private lateinit var binding: FragmentBuscarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentBuscarBinding.bind(view)

        binding.btnBuscar.setOnClickListener {
            val nombre = binding.nombreBuscarEditText.text.toString()

            when {
                nombre.isEmpty() -> {
                    binding.nombreBuscarEditText.error = getString(R.string.nombres)
                    binding.nombreBuscarEditText.requestFocus()
                }
                else -> {

                    val deudorDAO: DeudorDAO = Deudores.database.DeudorDAO()
                    val deudor = deudorDAO.searchDeudor(nombre)

                    if (deudor != null) {
                        binding.telefonoTextViewBuscar.visibility = View.VISIBLE
                        binding.valorTextViewBuscar.visibility = View.VISIBLE
                        binding.telefonoTextViewBuscar.text = deudor.telefono
                        binding.valorTextViewBuscar.text = deudor.valor.toString()
                    } else {
                        Toast.makeText(context, "no Existe", Toast.LENGTH_SHORT).show()
                    }

                }
            }

        }
    }

    companion object
}