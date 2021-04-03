package ca.ulaval.ima.tp3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.ulaval.ima.tp3.Networking.NetworkCenter
import ca.ulaval.ima.tp3.Networking.TP3API
import ca.ulaval.ima.tp3.model.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelActivity : AppCompatActivity() {
    val tp3NetworkCenter = NetworkCenter.buildService(TP3API::class.java)
    lateinit var recyclerViewList: RecyclerView
 //   var brandId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val brandId = intent.extras?.getString("brand_id")?.toInt()
        Log.d("testModeleActivity",brandId.toString())

        recyclerViewList = findViewById<RecyclerView>(R.id.simpleListRecyclerView)
        recyclerViewList.layoutManager = LinearLayoutManager(this)
        getListModel(brandId)
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


    fun getListModel(brandId:Int?){
        tp3NetworkCenter.listModel(brandId).enqueue(object :
                Callback<TP3API.ContentResponse<List<Model>>> {
            override fun onResponse(
                    call: Call<TP3API.ContentResponse<List<Model>>>,
                    response: Response<TP3API.ContentResponse<List<Model>>>
            ) {
                response.body()?.content?.let {
                    for (model in it) {
                        Log.d(
                                "ima-demo_ListModel",
                                " ${model.name} with id ${model.id}"
                        )
                    }
                    val adapter = ModelNameRecyclerViewAdapter(it)
                    recyclerViewList.adapter = adapter
                    adapter.setOnModelClickListener {
                        Toast.makeText(baseContext, "testpage2", Toast.LENGTH_SHORT).show()
                        val newActivity2 = Intent(baseContext, ModelSelect::class.java)
                        newActivity2.putExtra("searchId",it)
                        startActivity(newActivity2)
                    }
                    Log.d("demo", "An element was seleted: $it")

                }
            }

            override fun onFailure(
                    call: Call<TP3API.ContentResponse<List<Model>>>,
                    t: Throwable
            ) {
                Log.d("ima-demo", "listModels Failure $t")
            }
        }
        )
    }

}