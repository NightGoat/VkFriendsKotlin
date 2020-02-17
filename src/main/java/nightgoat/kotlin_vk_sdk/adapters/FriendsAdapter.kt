package nightgoat.kotlin_vk_sdk.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkfriends.models.VKUser
import com.squareup.picasso.Picasso
import nightgoat.kotlin_vk_sdk.R
import kotlinx.android.synthetic.main.cell_friend.view.*

class FriendsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mFriendsList: ArrayList<VKUser> = ArrayList()
    private var mSourceList: ArrayList<VKUser> = ArrayList()

    fun setupFriend(friendsList: List<VKUser>) {
        mSourceList.clear()
        mSourceList.addAll(friendsList)

        filter(query = "")
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if (it.firstName.contains(query, ignoreCase = true) || it.lastName.contains(query, ignoreCase = true)) {
                mFriendsList.add(it)
            }
        }
        mFriendsList.sortBy { it.firstName }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend, parent, false)
        return FriendsViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return mFriendsList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendsViewHolder) {
            holder.bind(friendModel = mFriendsList[position])
        }
    }

    class FriendsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(friendModel: VKUser) {

            friendModel.photo.let {
                    url ->
                Picasso.get().load(url).into(itemView.friend_civ_avatar)
            }

            itemView.friend_txt_name.text = "${friendModel.firstName} ${friendModel.lastName}"

            if (friendModel.isOnline == 1) {
                itemView.friend_img_online.visibility = View.VISIBLE
            } else {
                itemView.friend_img_online.visibility = View.GONE
            }
        }
    }
}