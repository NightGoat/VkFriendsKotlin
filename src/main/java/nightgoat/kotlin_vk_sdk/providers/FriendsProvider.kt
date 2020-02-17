package nightgoat.kotlin_vk_sdk.providers

import com.example.vkfriends.models.VKUser
import com.example.vkfriends.providers.VKFriendsRequest
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import nightgoat.kotlin_vk_sdk.presenters.FriendsPresenter

class FriendsProvider(var presenter: FriendsPresenter) {
    fun loadFriends() {
        VK.execute(VKFriendsRequest(), object : VKApiCallback<List<VKUser>> {

            override fun fail(error: VKApiExecutionException) {
                presenter.showError()
            }

            override fun success(result: List<VKUser>) {
                presenter.friendsLoaded(result)
            }
        })

    }
}