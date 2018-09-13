package yardspoon.riogeo.biz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import yardspoon.riogeo.data.GeofencesDAO

class GeofencesViwModel(application: Application) : AndroidViewModel(application) {

    private val dao: GeofencesDAO

    init {
        dao = application.databa
    }

}