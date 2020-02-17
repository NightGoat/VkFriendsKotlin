package nightgoat.kotlin_vk_sdk.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vkfriends.models.VKUser
import nightgoat.kotlin_vk_sdk.R
import nightgoat.kotlin_vk_sdk.providers.FriendsProvider
import nightgoat.kotlin_vk_sdk.views.FriendsView

@InjectViewState
class FriendsPresenter: MvpPresenter<FriendsView>() {

    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(presenter = this).loadFriends()
    }

    fun friendsLoaded(friendsList: List<VKUser>) {
        viewState.endLoading()
        if(friendsList.isEmpty()) {
            viewState.setupEmptyList()
            viewState.showError(textResource = R.string.friends_no_itmes)
        } else {
            viewState.setupFriendsList(friendsList = friendsList)
        }
    }

    fun showError() {
        viewState.showError(R.string.list_error_notification)
    }
}