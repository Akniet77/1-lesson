package com.example.a1_lesson.ui.profile

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.a1_lesson.R
import com.example.a1_lesson.databinding.FragmentProfileBinding
import com.example.a1_lesson.utils.Preferences

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    private val imagePicture = registerForActivityResult(

        ActivityResultContracts.GetContent()
    ) { uri  ->
        if (uri != null) {
           Preferences(requireContext()).saveProfilePicture(uri)
        }
        binding?.civProfile?.setImageURI(uri)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.civProfile.setOnClickListener{
            imagePicture.launch("image/*")
        }

        binding.save.setOnClickListener {
            val name = binding.edtProfile.text.toString()
            Preferences(requireContext()).saveProfileName(name)
        }
    }

    private fun initViews() {
        val pref = Preferences(requireContext())

        pref.getProfilePicture()?.let {
            Glide.with(requireContext())
                .load(it)
                .circleCrop()
                .into(binding?.civProfile!!)
        }

        pref.getProfileName()?.let {
            binding?.edtProfile?.setText(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

