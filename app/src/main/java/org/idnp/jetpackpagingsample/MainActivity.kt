package org.idnp.jetpackpagingsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.idnp.jetpackpagingsample.adapters.UserAdapter
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.paging.CountryViewModel
import org.idnp.jetpackpagingsample.tools.FillDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val countries: List<Country> =
            FillDatabase.readAndParseFile("C:\\Users\\Usuario\\Downloads\\countries.txt")
        // Aquí puedes utilizar la lista de países como desees, por ejemplo, almacenarla en una base de datos.



        val viewModel = CountryViewModel(this)
        insertCountriesAsync(viewModel, countries)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val pagingAdapter = UserAdapter()

        recyclerView.adapter = pagingAdapter
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            viewModel.items().collectLatest { pageData ->
                pagingAdapter.submitData(pageData)
            }
        }
    }
}

fun insertCountriesAsync(viewModel: CountryViewModel, countries: List<Country>) {
    GlobalScope.launch {
        withContext(Dispatchers.IO) {
            viewModel.insertCountries(countries)
        }
    }
}