package kz.islam.datastrore

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Created by Osman Zhuzhoev on 15/10/2022.
 */

object UserScheme {

    val FIELD_NAME = stringPreferencesKey("name")
    //    val FIELD_LAST_NAME = preferencesKey<String>("last_name")
    val FIELD_AGE = intPreferencesKey("age")
//    val FIELD_ACTIVE = preferencesKey<Boolean>("active")
}