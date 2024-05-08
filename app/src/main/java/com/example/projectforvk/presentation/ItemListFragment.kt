package com.example.projectforvk.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectforvk.R
import com.example.projectforvk.data.network.ConnectionType
import com.example.projectforvk.data.network.NetworkMonitorUtil
import com.example.projectforvk.databinding.FragmentItemListBinding
import com.example.projectforvk.data.network.model.Products
import com.example.projectforvk.presentation.adapters.ItemListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class ItemListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressBar: ProgressBar

    private lateinit var searchView: SearchView

    private lateinit var recyclerView: RecyclerView

    private val viewModel by viewModel<MainViewModel>()




    private var adapter = ItemListAdapter { products ->
        Log.d("ID", "$products")
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_container, DetailInfoFragment.newInstance(products))
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)

        initialize()
        val layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        addOnScrollListener()
        initObserve()
        viewModel.fetchData()
        onQueryTextListener()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        return binding.root
    }

    private fun initObserve() {
        viewModel.products.observe(viewLifecycleOwner) { data ->
            productsSubmit(data)
        }

        viewModel.isEmptySearch.observe(viewLifecycleOwner) { isEmptySearch ->
            binding.tvEmptySearch.isVisible = isEmptySearch
            binding.rvMain.isVisible = !isEmptySearch
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { data ->
            binding.progressBar.isVisible = data
        }
    }

    private fun addOnScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.fetchData()
                }
            }
        })
    }

    private fun onQueryTextListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length >= 3) {
                    searchProduct(newText)
                }
                if (newText.isEmpty()) {
                    searchProduct(newText)
                }

                Timber.tag("onQueryTextChange")
                    .d("newText = " + newText + ", " + newText.isEmpty())

                return true
            }
        })
    }

    fun searchProduct(text: String) {
        viewModel.getProductsSearching(text)
    }

    private fun initialize() {
        recyclerView = binding.rvMain
        progressBar = binding.progressBar
        searchView = binding.svMain
    }

    private fun productsSubmit(products: List<Products>) {
        val submitList = products.map { ItemsList.ProductsItemList(it) }
        adapter.submitList(submitList)
    }
}