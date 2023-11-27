package com.example.practicachat.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicachat.R
import com.example.practicachat.databinding.FragmentChatBinding
import com.example.practicachat.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val viewModel by viewModels<ChatViewModel>()

    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        initListeners()
        setUpUI()
        return binding.root
    }

    private fun initListeners() {
        with(binding){
            ivBack.setOnClickListener {
                findNavController().navigate(R.id.action_back)
            }
            btnSendMsg.setOnClickListener {
                val msg = etChat.text.toString()
                if (msg.isNotEmpty()) {
                    viewModel.sendMessage(msg)
                    etChat.text.clear()
                }
                //if msg is empty is useless clear the text of the editText
            }
        }
    }

    private fun setUpUI() {
        setUpMessages()
        subscribeToMessages()
    }

    private fun setUpMessages() {
        chatAdapter = ChatAdapter(mutableListOf())
        binding.rvMsg.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun subscribeToMessages() {
        lifecycleScope.launch {
            viewModel.messageList.collect {
                chatAdapter.updateList(it.toMutableList(), viewModel.name)
                binding.rvMsg.scrollToPosition(chatAdapter.messageList.size - 1)
            }
        }
    }
}