package nightgoat.kotlin_vk_sdk.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.vkfriends.models.VKUser

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FriendsView: MvpView {

    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupFriendsList(friendsList: List<VKUser>)
    fun startLoading()
    fun endLoading()

}