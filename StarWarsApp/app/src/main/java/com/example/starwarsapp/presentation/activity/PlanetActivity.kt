package com.example.starwarsapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.starwarsapp.R
import com.example.starwarsapp.common.BUNDLE_KEY_PERSON
import com.example.starwarsapp.domain.model.PersonModel
import com.example.starwarsapp.presentation.viewmodel.PlanetViewModel
import com.example.starwarsapp.databinding.ActivityPlanetBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlanetActivity : BaseActivity<ActivityPlanetBinding, PlanetViewModel>(R.layout.activity_planet) {

    private val viewModel: PlanetViewModel by viewModel()
    private lateinit var person: PersonModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        getPersonFromIntent()
        setPersonName()
        requestGetPlanet()
        setObservers()
    }

    private fun getPersonFromIntent() {
        person = intent.getSerializableExtra(BUNDLE_KEY_PERSON) as PersonModel
    }

    private fun setPersonName() {
        binding.personName.text = person.name
    }

    private fun requestGetPlanet() {
        val homeworld = person.homeworld
        val planetId = homeworld?.substring(homeworld.lastIndexOf("/planets/") + "/planets/".length, homeworld.length - 1)?.toInt()
        if (planetId != null) {
            viewModel.getPlanet(planetId)
        }
    }

    private fun setObservers() {
        viewModel.close.observe(this) {
            finish()
        }
    }

    override fun getVM(): PlanetViewModel = viewModel
}