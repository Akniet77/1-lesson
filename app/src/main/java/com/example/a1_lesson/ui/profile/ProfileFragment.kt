
package com.example.a1_lesson.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.a1_lesson.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    var imageView: ImageView? = null
    var editText: EditText? = null
    var image: Uri? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profile = view.findViewById<ImageView>(com.example.a1_lesson.R.id.profile_picture)

        val profilePick = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ){ result: ActivityResult ->
            image = result.data!!.data
            Glide.with(this)
                .load(image)
                .timeout(60)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
                .into(profile)

        }
        profile.setOnClickListener { view1: View? ->
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            profilePick.launch(intent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



