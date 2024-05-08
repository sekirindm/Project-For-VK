package com.example.projectforvk.presentation

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.projectforvk.databinding.FragmentDetailInfoBinding
import com.example.projectforvk.data.network.model.Products
import com.example.projectforvk.presentation.adapters.ViewPagerAdapter


class DetailInfoFragment : Fragment() {

    private var _binding: FragmentDetailInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: ViewPager2
    private lateinit var vpAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailInfoBinding.inflate(inflater, container, false)

        val products = arguments?.getParcelable<Products>("product")

        setViewPager(products?.image)
        initialize()

        with(binding) {
            tvPriceDetail.text = products?.price.toString() + "$"
            rankDetail.text = products?.rating.toString()
            tvDescriptionsDetail.text = products?.description
            tvTitleDetail.text = products?.title
        }

        val priceBeforeDiscount = products?.price ?: 0
        val discountPercentage = products?.discountPercentage ?: 0.0
        val priceWithDiscount = priceBeforeDiscount / (1 - (discountPercentage / 100))
        val tvPriceBeforeDiscount = binding.tvPriceBeforeDiscount
        tvPriceBeforeDiscount.text = priceWithDiscount.toInt().toString() + "$"
        tvPriceBeforeDiscount.paintFlags = tvPriceBeforeDiscount.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        return binding.root
    }

    private fun initialize() {
        recyclerView = binding.vpDetail
        recyclerView.adapter = vpAdapter
    }

    private fun setViewPager(imageList: List<String>?) {
        if (imageList != null) {
            vpAdapter = ViewPagerAdapter(imageList)
        } else {
            Toast.makeText(context, "Тут пусто, бро", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(product: Products): DetailInfoFragment {
            val fragment = DetailInfoFragment()
            val args = Bundle()
            args.putParcelable("product", product)
            fragment.arguments = args
            return fragment
        }
    }
}