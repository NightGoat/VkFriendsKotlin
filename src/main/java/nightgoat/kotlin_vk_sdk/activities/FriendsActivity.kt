package nightgoat.kotlin_vk_sdk.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.vkfriends.models.VKUser
import nightgoat.kotlin_vk_sdk.R
import nightgoat.kotlin_vk_sdk.presenters.FriendsPresenter
import nightgoat.kotlin_vk_sdk.views.FriendsView
import kotlinx.android.synthetic.main.activity_friends.*
import nightgoat.kotlin_vk_sdk.adapters.FriendsAdapter

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    private lateinit var mAdapter: FriendsAdapter

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        friendsPresenter.loadFriends()
        mAdapter = FriendsAdapter()

        txt_friends_search.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
               mAdapter.filter(p0.toString())
            }

        })

        recycler_friends.adapter = mAdapter
        recycler_friends.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recycler_friends.setHasFixedSize(true)

    }


    //Friends View implementation
    override fun showError(textResource: Int) {
        txt_friends_no_items.text = getString(textResource)
    }

    override fun setupEmptyList() {
        recycler_friends.visibility = View.GONE
        txt_friends_no_items.visibility = View.VISIBLE
    }

    override fun setupFriendsList(friendsList: List<VKUser>) {
        recycler_friends.visibility = View.VISIBLE
        txt_friends_no_items.visibility = View.GONE
        mAdapter.setupFriend(friendsList = friendsList)
    }

    override fun startLoading() {
        txt_friends_no_items.visibility = View.GONE
        recycler_friends.visibility = View.GONE
        progress_circular_friends.visibility = View.VISIBLE
    }

    override fun endLoading() {
        progress_circular_friends.visibility = View.GONE
    }
}
