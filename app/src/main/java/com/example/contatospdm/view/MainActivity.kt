package com.example.contatospdm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.contatospdm.R
import com.example.contatospdm.databinding.ActivityMainBinding
import com.example.contatospdm.model.Constante.EXTRA_CONTACT
import com.example.contatospdm.model.Contact

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Data source
    private val contactlist: MutableList<Contact> = mutableListOf()

    //adpter
    private val contactAdpater: ArrayAdapter<String> by lazy {
        ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            contactlist.map { contact ->
                contact.name
            }
            )
    }

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        //fillContatcts()    ---------------> A lista vai começar vazia sem o fillContatcs
        amb.contatosLv.adapter = contactAdpater

        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){result ->
            if (result.resultCode == RESULT_OK){
                val contact = result.data?.getParcelableExtra<Contact>(EXTRA_CONTACT)
                contact?.let { _contact ->
                    contactlist.add(_contact)
                    contactAdpater.add(_contact.name)
                    contactAdpater.notifyDataSetChanged()

                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addContactMi ->  {
                carl.launch(Intent(this, ContactActivity::class.java))
                true
            }
            else -> false
        }
    }

    private fun fillContatcts(){
        for (i in 1..50){
            contactlist.add(
                Contact(
                    i,
                    "Nome $i",
                    "Endereço $i",
                    "Telefone $i",
                    "E-mail $i"
                )
            )
        }
    }
}