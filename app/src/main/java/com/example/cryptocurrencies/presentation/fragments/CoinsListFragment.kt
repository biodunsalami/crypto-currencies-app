package com.example.cryptocurrencies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencies.R
import com.example.cryptocurrencies.common.Resource
import com.example.cryptocurrencies.databinding.FragmentCoinsListBinding
import com.example.cryptocurrencies.databinding.ItemCoinsListBinding
import com.example.cryptocurrencies.domain.models.Coin
import com.example.cryptocurrencies.presentation.adapter.BaseAdapter
import com.example.cryptocurrencies.presentation.viewmodels.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinsListFragment : Fragment() {

    private lateinit var binding: FragmentCoinsListBinding

    private val viewModel: CoinsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoinsListBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.coinList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    result.data?.let { setUpCoinsList(it) }
                    binding.progressBar.visibility = View.INVISIBLE

                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), result.message ?: "Error", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }


    private fun setUpCoinsList(list: List<Coin>) {
        var mAdapter = BaseAdapter<Coin>()


        //Sample data
        mAdapter.listOfItems = list


        mAdapter.expressionViewHolderBinding = { item, viewBinding ->
            //eachItem will provide the each item in the list, in this case its a string type
            //cast the viewBinding with you layout binding class
            var view = viewBinding as ItemCoinsListBinding

            val id = list.indexOf(item) + 1
            view.coinNumber.text = getString(R.string.id, id)
            view.coinName.text = item.name

            view.coinStatus.text = item.symbol
            //you can use click listener on root or any button
            view.root.setOnClickListener {
                //Click item value is eachItem
            }
        }

        mAdapter.expressionOnCreateViewHolder = { viewGroup ->
            //Inflate the layout and send it to the adapter
            ItemCoinsListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        }

        //finally put the adapter to recyclerview
        binding.coinsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }


}