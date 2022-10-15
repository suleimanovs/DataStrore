package kz.islam.datastrore.proto

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kz.islam.datastrore.UserScheme
import kz.islam.datastrore.databinding.ActivityMainBinding

val Context.protoDataStore: DataStore<Preferences> by preferencesDataStore(name = "user.pb")

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.save.setOnClickListener {

            val name = binding.name.text.toString()
            val age = binding.age.text.toString()

            coroutineScope.launch {
                dataStore.edit { prefs ->
                    prefs[UserScheme.FIELD_NAME] = name
                    prefs[UserScheme.FIELD_AGE] = age.toInt()
                }
            }
        }

        coroutineScope.launch {
            dataStore.data.collect { preferences ->
                val name = preferences[UserScheme.FIELD_NAME] ?: ""
                binding.nameData.text = name

                val age = preferences[UserScheme.FIELD_AGE] ?: 0
                binding.ageData.text = age.toString()
            }
        }

    }
}