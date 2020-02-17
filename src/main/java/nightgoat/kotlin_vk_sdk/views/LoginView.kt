package nightgoat.kotlin_vk_sdk.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface LoginView: MvpView {

    fun setLoading(isLoading: Boolean)
    fun openFriends()
    fun showError(textResource: Int)

}