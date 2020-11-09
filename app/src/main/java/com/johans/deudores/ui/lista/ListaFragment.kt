package com.johans.deudores.ui.lista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.johans.deudores.Deudores
import com.johans.deudores.R
import com.johans.deudores.data.database.dao.DeudorDAO
import com.johans.deudores.data.database.entities.Deudor
import com.johans.deudores.databinding.FragmentListaBinding


class ListaFragment : Fragment() {

    private lateinit var binding: FragmentListaBinding
    var listDeudores: List<Deudor> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_lista, container, false)
        val root = inflater.inflate(R.layout.fragment_lista, container, false)
        val rv_deudores = root.findViewById<RecyclerView>(R.id.deudores_recycler_view)
        rv_deudores.layoutManager =
            LinearLayoutManager(requireActivity().applicationContext, RecyclerView.VERTICAL, false)
        rv_deudores.setHasFixedSize(true)
        var deudorDao: DeudorDAO = Deudores.database.DeudorDAO()
        listDeudores = deudorDao.getDeudores()

        var deudoresRVAdapter = DeudoresRVAdapter(
            requireActivity().applicationContext,
            listDeudores as ArrayList<Deudor>
        )
        rv_deudores.adapter = deudoresRVAdapter
        deudoresRVAdapter.notifyDataSetChanged()
        return root
    }


}