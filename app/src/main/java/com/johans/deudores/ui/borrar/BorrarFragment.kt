package com.johans.deudores.ui.borrar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.johans.deudores.Deudores
import com.johans.deudores.R
import com.johans.deudores.data.database.dao.DeudorDAO
import com.johans.deudores.databinding.FragmentBorrarBinding

class BorrarFragment : Fragment() {

    private lateinit var binding: FragmentBorrarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_borrar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentBorrarBinding.bind(view)

        binding.btnBorrar.setOnClickListener {
            val nombre = binding.textInputNombreBorrar.text.toString()

            val deudorDAO: DeudorDAO = Deudores.database.DeudorDAO()
            val deudor = deudorDAO.searchDeudor(nombre)

            if (deudor != null) {
                deudorDAO.deleteDeudor(deudor)
                Toast.makeText(context, "Deudor eliminado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "no existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object
}
