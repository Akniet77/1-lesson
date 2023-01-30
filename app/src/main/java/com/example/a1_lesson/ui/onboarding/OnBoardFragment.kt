package com.example.a1_lesson.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.a1_lesson.R
import com.example.a1_lesson.databinding.FragmentOnboardBinding
import com.example.a1_lesson.utils.Preferences


class OnBoardFragment : Fragment(), OnBoardPageFragment.OnBoardListener {

    private var _binding: FragmentOnboardBinding? = null
    private val binding get() = _binding!!
    private var boardAdapter: OnBoardAdapter? = null
    private lateinit var viewPager: ViewPager2

    val boardModels = listOf<BoardModel>(
        BoardModel(
            imgResId = R.drawable.ic_onboarding1,
            title = "To-do list!",
            description = "Here you can write down something important or make a schedule for tomorrow:)"
        ),
        BoardModel(
            imgResId = R.drawable.ic_onboarding2,
            title = "Share your crazy idea ^_^",
            description = "You can easily share with your report, list or schedule and it's convenient"
        ),
        BoardModel(
            imgResId = R.drawable.ic_onboarding3,
            title = "Flexibility",
            description = "Your note with you at home, at work, even at the resort"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        boardAdapter = OnBoardAdapter(
            childFragmentManager, lifecycle, boardModels
        )
        viewPager = binding.viewpager
        viewPager.adapter = boardAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSkipClicked() {
        binding.viewpager.setCurrentItem(boardModels.lastIndex, true)
    }

    override fun onNextClicked() {
        binding.viewpager.setCurrentItem(++viewPager.currentItem, true)
    }

    override fun onStartClicked() {
        findNavController().navigateUp()
        Preferences(requireContext()).apply {
            setHaveSeenOnBoarding()
        }
    }

    override fun getViewPager(): ViewPager2 {
        return viewPager
    }
}
