package com.johans.deudores.ui.lista

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johans.deudores.R
import com.johans.deudores.data.database.entities.Deudor
import com.johans.deudores.databinding.DeudoresItemBinding

class DeudoresRVAdapter(
    var context: Context,
    var deudoresList: ArrayList<Deudor>
) : RecyclerView.Adapter<DeudoresRVAdapter.DeudoresViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeudoresViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.deudores_item, parent, false)
        return DeudoresViewHolder(itemView, context)
    }

    override fun onBindViewHolder(holder: DeudoresViewHolder, position: Int) {
        val deudor = deudoresList[position]
        holder.bindDeudor(deudor)
    }

    override fun getItemCount(): Int = deudoresList.size


    class DeudoresViewHolder(itenView: View, context: Context) : RecyclerView.ViewHolder(itenView) {

        private val binding = DeudoresItemBinding.bind(itenView)

        fun bindDeudor(deudor: Deudor) {
            binding.nombreTextView.text = deudor.nombre
            binding.valorTextView.text = deudor.valor.toString()
        }
    }
}

