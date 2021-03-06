package com.adva.imageList.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adva.R
import com.adva.databinding.FragmentImageListBinding
import com.adva.imageList.ImageListViewModel
import com.adva.imageList.adapter.ImageAdapter
import com.adva.imageList.model.ImageModelItem
import com.adva.localDataBase.Constants
import kotlinx.android.synthetic.main.fragment_image_list.*

class ImageListFragment : Fragment(), ImageAdapter.OnImageClickListener {

    lateinit var viewModel: ImageListViewModel
    private lateinit var binding: FragmentImageListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(ImageListViewModel::class.java)
        binding = FragmentImageListBinding.inflate(this.layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNewLobbyView()
        getNewLobby()
    }

    private fun initNewLobbyView() {
        image_recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    fun getNewLobby() {
        viewModel.imageLists.observe(viewLifecycleOwner, Observer {
            image_recyclerview.adapter = ImageAdapter(it, this)
        })

        viewModel.getImageList()
    }

    override fun onImageClicked(imageModelItem: ImageModelItem) {

        activity?.let {
            val bundle = Bundle()
            bundle.putString(Constants.IMAGE_ID, imageModelItem.thumbnailUrl)
            findNavController().navigate(R.id.action_showFragment, bundle)
        }

    }


}