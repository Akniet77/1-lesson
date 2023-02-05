package com.example.a1_lesson.ui.home

//noinspection SuspiciousImport
import android.app.AlertDialog
import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1_lesson.R
import com.example.a1_lesson.databinding.FragmentHomeBinding
import com.example.a1_lesson.ui.home.new_task.NewTaskFragment
import com.example.a1_lesson.ui.home.new_task.TaskAdapter
import com.example.a1_lesson.ui.home.new_task.TaskModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!
    private val taskAdapter = TaskAdapter(mutableListOf())
    val dataSource = mutableListOf<ClipData.Item>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()

    }


    private fun initView() {
        binding.rvTasks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }

    }


    private fun initListeners() {
        binding.btnFab.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment)
            setFragmentResultListener(
                NewTaskFragment.NEW_TASK_RESULT_KEY,
            ) { key, data ->
                val title = data.getString(NewTaskFragment.NEW_TASK_TITLE_KEY)
                val description = data.getString(NewTaskFragment.NEW_TASK_DESC_KEY)

                if (title != null) {
                    val taskModel = TaskModel(title, description ?: "")
                    taskAdapter.add(taskModel)
                }
            }
        }
    }



    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
