package ca.ulaval.ima.tp3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.ulaval.ima.tp3.model.LightOutput
import ca.ulaval.ima.tp3.model.Model

class ShortDetailRecyclerViewAdapter (private val items_List:LightOutput) :
        RecyclerView.Adapter<ShortDetailRecyclerViewAdapter.ViewHolder>() {

    lateinit var onItemClickListener: ((Int?)->Unit)
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.imageView_model)
        val titre: TextView  = itemView.findViewById(R.id.textView_brandmodel)
        val annee: TextView  = itemView.findViewById(R.id.textView_year_ans)
        val kilo: TextView = itemView.findViewById(R.id.textView_miles_ans)
        val prix: TextView = itemView.findViewById(R.id.textView_price_ans)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.simple_cell_recycler_view,
                parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car: LightOutput = items_List
        //holder.photo.setImageResource(car.image)
        holder.titre.text = car.model.brand.name + " " +car.model.id
        holder.annee.text = car.year.toString()
        holder.kilo.text = car.kilometers.toString()
        holder.prix.text = car.price.toString()

        holder.itemView.setOnClickListener { car.created}
    }

    override fun getItemCount(): Int {
        return 1

    }

    fun setOnModelClickListener(onItemClickListener: ((Int?)->Unit)){
        this.onItemClickListener = onItemClickListener
    }
}