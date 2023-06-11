package org.idnp.jetpackpagingsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.idnp.jetpackpagingsample.adapters.UserAdapter
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.paging.CountryViewModel
import org.idnp.jetpackpagingsample.tools.FillDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countries: List<Country> = FillDatabase.readAndParseFile(this, "LabCountries.txt")

        // Construir una cadena con los países
        val sb = StringBuilder()
        for (country in countries) {
            sb.append(country).append("\n")
        }

        // Imprimir los países en la consola
        Log.d("Lista de Paises", sb.toString())

        val viewModel = CountryViewModel(this)
        insertCountriesAsync(viewModel, countries)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val pagingAdapter = UserAdapter()

        recyclerView.adapter = pagingAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        lifecycleScope.launch {
            viewModel.items().collectLatest { pageData ->
                pagingAdapter.submitData(pageData)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun insertCountriesAsync(viewModel: CountryViewModel, countries: List<Country>) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.insertCountries(countries)
            }
        }
    }
}