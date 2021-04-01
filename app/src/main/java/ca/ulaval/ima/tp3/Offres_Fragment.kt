package ca.ulaval.ima.tp3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.ulaval.ima.tp3.Networking.NetworkCenter
import ca.ulaval.ima.tp3.Networking.TP3API
import ca.ulaval.ima.tp3.model.Brand
import ca.ulaval.ima.tp3.model.BrandList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Offres_Fragment : Fragment() {

    val tp3NetworkCenter = NetworkCenter.buildService(TP3API::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.offre_fragment, container, false)
        val recyclerViewList = root.findViewById<RecyclerView>(R.id.simpleListRecyclerView)
        val values = arrayOf("GELGIF", "IFT", "GLO", "GMC", "GCI", "STT", "GMT", "PHI")
        recyclerViewList.layoutManager = LinearLayoutManager(root.context)
        recyclerViewList.adapter = SimpleListRecyclerViewAdapter(values){
            Log.d("demo","An element was seleted: $it")
        }
        return root
    }

    fun getListBrands(){
        tp3NetworkCenter.listBrand().enqueue(object :
                Callback<TP3API.ContentResponse<BrandList<Brand>>> {
                override fun onResponse(
                    call: Call<<TP3API.ContentResponse<BrandList<Brand>>>,
                    response: Response<<TP3API.ContentResponse<BrandList<Brand>>>
                ) {
                    response.body()?.content?.results?.let {
                        Toast.makeText(
                            applicationContext,
                            "Got ${it.count()} Restaurants",
                            Toast.LENGTH_LONG
                        ).show()
                        for (restaurant in it) {
                            Log.d(
                                "ima-demo",
                                "Got Restaurant ${restaurant.name} with id ${restaurant.id}"
                            )
                        }
                    }
                }

                override fun onFailure(
                    call: Call<KungryAPI.ContentResponse<PaginatedResultSerializer<Restaurant>>>,
                    t: Throwable
                ) {
                    Log.d("ima-demo", "listRestaurants Failure $t")
                }

            }

        )
    }
}