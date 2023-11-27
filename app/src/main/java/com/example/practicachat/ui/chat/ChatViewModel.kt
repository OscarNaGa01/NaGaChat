package com.example.practicachat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicachat.domain.usecases.GetMessagesUseCase
import com.example.practicachat.domain.usecases.GetUserNameUseCase
import com.example.practicachat.domain.usecases.SendMessageUseCase
import com.example.practicachat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    //Now it's not necessary because it will complicate all but, if you want to use it and take a look:
    //you can make all functions in viewmodel private and only leave one public function, this function
    //will be the only one that the view can access, and this function will call the private functions
    //you can take a look with a real example on Sunset repo develop branch on every viewmodel, you will see I have
    // a package called state where I have all the vals shared between viewmodel and view and then I also I have
    //another class that is called event that is used to send events from viewmodel to view, this is a good practice
    //take a look if you want
    // https://github.com/Mad-Development-Team/Sunset-Android-App/tree/develop/app/src/main/java/com/madteam/sunset/ui/screens/enterusername

    var name: String = ""

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList: StateFlow<List<MessageModel>> = _messageList

    //Normally all the vars in the view model are on the top of the class

    init {
        getUserName()
        getMessages()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            name = getUserNameUseCase()
        }
    }

    private fun getMessages() {
        viewModelScope.launch {
            getMessagesUseCase().collect {
                Log.d("tutorial", "la info es $it")
                _messageList.value = it
            }
        }
    }

    fun sendMessage(msg:String) {
        sendMessageUseCase(msg, name)
    }

}