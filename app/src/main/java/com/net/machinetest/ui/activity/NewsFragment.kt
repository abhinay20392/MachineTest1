package com.net.machinetest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.machinetest1.R
import com.example.machinetest1.databinding.FragmentNewsBinding
import com.net.machinetest.adapters.NewsAdapter
import com.net.machinetest.data.responses.news.Article


private const val ARG_PARAM1 = "param1"


class NewsFragment : Fragment(), NewsAdapter.OnItemAction {
    private var articleList: ArrayList<Article>? = null
    private lateinit var binding: FragmentNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articleList = it.getSerializable(ARG_PARAM1) as ArrayList<Article>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NewsAdapter(ArrayList(articleList), this@NewsFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<Article>) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }

    override fun onItemSelect(url: String) {
        requireActivity().startActivity(
            Intent(
                requireActivity(),
                WebViewActivity::class.java
            ).putExtra("url", url)
        )
    }
}