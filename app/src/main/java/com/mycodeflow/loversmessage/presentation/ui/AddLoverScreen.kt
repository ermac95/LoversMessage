package com.mycodeflow.loversmessage.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycodeflow.loversmessage.MyApp
import com.mycodeflow.loversmessage.R
import com.mycodeflow.loversmessage.databinding.FragmentAddLoverScreenBinding
import com.mycodeflow.loversmessage.domain.model.User
import com.mycodeflow.loversmessage.presentation.viewmodels.AppViewModelFactory
import com.mycodeflow.loversmessage.presentation.viewmodels.UserViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


class AddLoverScreen : Fragment(R.layout.fragment_add_lover_screen) {

    private val vb by viewBinding(FragmentAddLoverScreenBinding::bind)

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var userViewModel: UserViewModel

    lateinit var potentialFriend: User

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupViews()
        setupListeners()
    }

    private fun setupViewModel() {
        userViewModel = ViewModelProvider(this, viewModelFactory)
                .get(UserViewModel::class.java)
    }

    private fun setupViews() {
        //make userName not clickable if it is empty
        vb.loversName.isClickable = !vb.loversName.text.isNullOrEmpty()
        //show user in list if one found (exchange for ListView in future
        lifecycleScope.launchWhenResumed {
            userViewModel.userData.collectLatest { user ->
                Log.d("myLogs", "Current userData value is $user")
                vb.loversName.text = user?.userName
            }
        }
        lifecycleScope.launchWhenResumed {
            userViewModel.loading.collectLatest { loading ->
                changeLoadingStatus(loading)
            }
        }
        lifecycleScope.launchWhenResumed {
            userViewModel.errorMessage.collectLatest { error ->
                changeErrorStatus(error)
            }
        }
    }

    private fun setupListeners() {
        //Search lover field
        vb.loverSearchField.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    userViewModel.findUserByName(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        //add friend to friendList
        vb.loversName.setOnClickListener { view ->
            val popup = PopupMenu(context, view)
            popup.inflate(R.menu.lover_actions_popup_menu)
            popup.setOnMenuItemClickListener { menuItem->
                when(menuItem.itemId){
                    R.id.add_lover -> {
                        userViewModel.sendInvitation(potentialFriend.userName)
                        false
                    }
                    else -> return@setOnMenuItemClickListener false
                }
            }
            popup.show()
        }
    }

    private fun changeLoadingStatus(loading: Boolean) {
        Log.d("myLogs", "Change loading status function got loading value which is $loading")
        vb.loadingStatus.visibility = if(loading) View.VISIBLE else View.GONE
    }

    private fun changeErrorStatus(error: String) {
        if (error.isNullOrEmpty()){
            vb.errorMessage.visibility = View.GONE
        } else {
            vb.errorMessage.visibility = View.VISIBLE
            vb.errorMessage.text = error
        }
    }
}