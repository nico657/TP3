package ca.ulaval.ima.tp3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.ulaval.ima.tp3.model.Model

class ModelNameRecyclerViewAdapter (private val items_List:List<Model>) :
    RecyclerView.Adapter<ModelNameRecyclerViewAdapter.ViewHolder>() {

    lateinit var onItemClickListener: ((IntArray)->Unit)
    inner class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCars: TextView = itemView.findViewById(R.id.textView_list)
    }
        fun setOnModelClickListener(onItemClickListener: ((IntArray)->Unit)){
            this.onItemClickListener = onItemClickListener
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.simple_cell_recycler_view,
                parent, false)
            return ViewHolder(itemView)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val car: Model = items_List[position]
            holder.itemCars.text = car.name
            holder.itemCars.setOnClickListener {
                val searchID:IntArray = intArrayOf(car.id, car.brand.id)
                onItemClickListener(searchID)
            }
        }


        override fun getItemCount(): Int {
            return items_List.count()
        }


    }
