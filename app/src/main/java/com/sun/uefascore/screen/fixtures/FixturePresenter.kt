package com.sun.uefascore.screen.fixtures

import com.sun.uefascore.data.model.Fixture
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import com.sun.uefascore.data.source.repository.FixtureRepository

class FixturePresenter internal constructor(private val repository: FixtureRepository?) :
    ContractFixture.Presenter {

    private var view: ContractFixture.View? = null

    override fun getFixture(date: String, season: String) {
        repository?.getFixture(
            date,
            season,
            object : OnFetchDataJsonListener<MutableList<Fixture>> {
                override fun onSuccess(data: MutableList<Fixture>) {
                    view?.onGetFixtureSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            })
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun setView(view: ContractFixture.View?) {
        this.view = view
    }
}