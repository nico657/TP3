package ca.ulaval.ima.tp3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.ulaval.ima.tp3.Networking.NetworkCenter
import ca.ulaval.ima.tp3.Networking.TP3API
import ca.ulaval.ima.tp3.model.Brand
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Offres_Fragment : Fragment() {
    lateinit var marque:List<Brand>
    val tp3NetworkCenter = NetworkCenter.buildService(TP3API::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.offre_fragment, container, false)
        getListBrand(root)
        //val values = arrayOf("GELGIF", "IFT", "GLO", "GMC", "GCI", "STT", "GMT", "PHI")

        return root
    }

    fun getListBrand(root:View){
        tp3NetworkCenter.listBrand().enqueue(object :
                Callback<TP3API.ContentResponse<List<Brand>>> {
                override fun onResponse(
                    call: Call<TP3API.ContentResponse<List<Brand>>>,
                    response: Response<TP3API.ContentResponse<List<Brand>>>
                ) {
                    response.body()?.content?.let {
                        for (brand in it) {
                            Log.d(
                                "ima-demo",
                                " ${brand.name} with id ${brand.id}"
                            )
                        }
                        val recyclerViewList = root.findViewById<RecyclerView>(R.id.simpleListRecyclerView)
                        recyclerViewList.layoutManager = LinearLayoutManager(root.context)
                        recyclerViewList.adapter = BrandNameRecyclerViewAdapter(it){
                            Log.d("demo","An element was seleted: $it")
                            val newActivity2 = Intent(root.context, ModelActivity::class.java)
                            newActivity2.putExtra("brand_id",it)
                            startActivity(newActivity2)


                        }

                    }
                }

                override fun onFailure(
                    call: Call<TP3API.ContentResponse<List<Brand>>>,
                    t: Throwable
                ) {
                    Log.d("ima-demo", "listBrands Failure $t")
                }

            }

        )

    }


}