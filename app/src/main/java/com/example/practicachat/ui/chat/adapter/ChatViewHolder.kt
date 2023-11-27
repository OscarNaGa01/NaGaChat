package com.example.practicachat.ui.chat.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.practicachat.databinding.ItemChatMeBinding
import com.example.practicachat.databinding.ItemChatOtherBinding
import com.example.practicachat.domain.model.MessageModel

class ChatViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(messageModel: MessageModel, itemViewType: Int) {
        when (itemViewType) {
            ChatAdapter.SENT_MESSAGE -> bindSentMessage(messageModel)
            ChatAdapter.RECEIVED_MESSAGE -> bindReceivedMessage(messageModel)
        }
    }

    private fun bindReceivedMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatOtherBinding
        with(currentBinding){
            tvDate.text = messageModel.date
            tvChat.text = messageModel.msg
            tvName.text = messageModel.user.userName
            tvHour.text = messageModel.hour
        }
        //Get used to use scope functions, created by Kotlin to make your code more readable
        // easy to understand and avoid duplicated code
    }

    private fun bindSentMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatMeBinding
        with(currentBinding){
            tvDateMe.text = messageModel.date
            tvChatMe.text = messageModel.msg
            tvHour.text = messageModel.hour
        }
        //same here
    }
}