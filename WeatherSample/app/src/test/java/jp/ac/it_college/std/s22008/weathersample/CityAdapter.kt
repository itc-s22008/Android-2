package jp.ac.it_college.std.s22008.weathersample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import jp.ac.it_college.std.s22008.weathersample.databinding.RowBinding

class CityAdapter(val callback: (CityData.City) -> Unit) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root)

    class RowBinding {

        val root: View
            get() {
                TODO()
            }

        companion object {
            fun inflate(
                from: LayoutInflater?,
                parent: ViewGroup,
                b: Boolean
            ): RowBinding {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = CityData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.apply {
            text = CityData [position].name
            setOnClickListener {
                callback(CityData[position])
            }
        }
    }

    private fun setOnClickListener(function: () -> Unit) {
        TODO("Not yet implemented")
    }
}