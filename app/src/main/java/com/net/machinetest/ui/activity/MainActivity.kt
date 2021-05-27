package com.net.machinetest.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import com.example.machinetest1.databinding.ActivityMainBinding
import com.net.machinetest.data.network.Resource
import com.net.machinetest.data.responses.news.AllNewsResponse
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<NewsViewModel>()
    private lateinit var binding: ActivityMainBinding

    /*  */
    /**
     * required for custom fonts
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.shimmerFM.startShimmerAnimation()
        binding.shimmerFM.visibility = View.VISIBLE

        viewModel.newsResponse.observe(this, Observer {
            binding.shimmerFM.visibility = View.GONE
            binding.shimmerFM.stopShimmerAnimation()
            if (it is Resource.Success) {
                setupTablayout(it.value)
            }
        })
        viewModel.getData()
    }

    private fun setupTablayout(allNewsResponse: AllNewsResponse) {

        val myViewPageStateAdapter = MyViewPageStateAdapter(supportFragmentManager)

        for ((key, value) in allNewsResponse.mapArticle) {
            myViewPageStateAdapter.addFragment(
                NewsFragment.newInstance(ArrayList(value)),
                key
            )
        }

        binding.viewPagerTest.adapter = myViewPageStateAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPagerTest, true)
    }

    class MyViewPageStateAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val fragmentTitleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList.get(position)
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList.get(position)
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }
    }


}

