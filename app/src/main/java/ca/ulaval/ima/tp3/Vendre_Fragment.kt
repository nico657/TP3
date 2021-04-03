package ca.ulaval.ima.tp3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class Vendre_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.vendre_fragment, container, false)
        val button_soumission : Button = root.findViewById(R.id.button)
        val spinnerAnnee: Spinner = root.findViewById(R.id.spinner_annee)
        val spinnertransmissio:Spinner = root.findViewById(R.id.spinner_transmission)
        val editTextKilo: EditText = root.findViewById(R.id.editText_kilo)
        val editTextPrix: EditText = root.findViewById(R.id.editText_prix)
        val choixModele : TextView = root.findViewById(R.id.textView_selecModel)

        choixModele.setOnClickListener(){
            startActivity(Intent(activity as MainActivity, SelectionModeleActivity::class.java))
        }

        button_soumission.setOnClickListener(){

            Toast.makeText(context, "CLIC", Toast.LENGTH_SHORT).show()
        }
        return root
    }


//    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        val annee :String = p0?.getItemAtPosition(p2).toString()
//    }

//    override fun onNothingSelected(p0: AdapterView<*>?) {
//    }
}