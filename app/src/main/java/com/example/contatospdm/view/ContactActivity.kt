package com.example.contatospdm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contatospdm.R
import com.example.contatospdm.databinding.ActivityContactBinding
import com.example.contatospdm.model.Constante.EXTRA_CONTACT
import com.example.contatospdm.model.Contact
import kotlin.random.Random

class ContactActivity : AppCompatActivity() {
    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)


        with(acb){
            saveBt.setOnClickListener{
                val contact: Contact = Contact(
                    gerenetaId(),
                    nameEt.text.toString(),
                    addressEt.text.toString(),
                    phoneEt.text.toString(),
                    emailEt.text.toString()
                )

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_CONTACT, contact)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }


    }

    private fun gerenetaId(): Int = Random(System.currentTimeMillis()).nextInt()
}