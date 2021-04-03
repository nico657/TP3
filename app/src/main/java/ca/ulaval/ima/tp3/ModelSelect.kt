package ca.ulaval.ima.tp3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.ulaval.ima.tp3.Networking.NetworkCenter
import ca.ulaval.ima.tp3.Networking.TP3API
import ca.ulaval.ima.tp3.model.LightOutput
import ca.ulaval.ima.tp3.model.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelSelect : AppCompatActivity() {

    val tp3NetworkCenter = NetworkCenter.buildService(TP3API::class.java)
    lateinit var recyclerViewList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model_select)

 //       setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val searchID = intent.extras?.getIntArray("search_id")
        Log.d("testModeleActivity",searchID.toString())

        recyclerViewList = findViewById<RecyclerView>(R.id.simpleListRecyclerView)
        recyclerViewList.layoutManager = LinearLayoutManager(this)
        getOfferDetail(searchID?.get(0), searchID?.get(1))

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getOfferDetail(modelId:Int?, brandId:Int?){
        tp3NetworkCenter.offerDetail(modelId,brandId).enqueue(object :
            Callback<TP3API.ContentResponse<LightOutput>> {
            override fun onResponse(
                call: Call<TP3API.ContentResponse<LightOutput>>,
                response: Response<TP3API.ContentResponse<LightOutput>>
            ) {
                response.body()?.content?.let {
                        Log.d(
                            "ima-demo_lightOffer",
                            "1-2-test"
                        )
                    val adapter = ShortDetailRecyclerViewAdapter(it)
                    recyclerViewList.adapter = adapter
                    adapter.setOnModelClickListener {
                        Toast.makeText(baseContext, "testpage2", Toast.LENGTH_SHORT).show()
                        //startActivity(Intent(baseContext, LightOutput::class.java))
                    }
                    Log.d("demo", "An element was seleted: $it")

                }
            }

            override fun onFailure(
                call: Call<TP3API.ContentResponse<LightOutput>>,
                t: Throwable
            ) {
                Log.d("ima-demo", "listModels Failure $t")
            }
        }
        )
    }
}