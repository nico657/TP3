package ca.ulaval.ima.tp3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SimpleListRecyclerViewAdapter(private val items_List:Array<String>, private val listener:((String)-> Unit)) :RecyclerView.Adapter<SimpleListRecyclerViewAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCars: TextView = itemView.findViewById(R.id.textView_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView:View = LayoutInflater.from(parent.context).inflate(R.layout.simple_cell_recycler_view,
            parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car:String = items_List[position]
        holder.itemCars.text = car
        holder.itemView.setOnClickListener { listener(car) }

    }

    override fun getItemCount(): Int {
        return items_List.count()
    }


}