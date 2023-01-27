package com.sidukov.fitnesskittest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sidukov.fitnesskittest.R
import com.sidukov.fitnesskittest.data.ScheduleRepository
import com.sidukov.fitnesskittest.data.remote.APIClient

class ScheduleFragment : Fragment() {

    private lateinit var scheduleRecyclerView: RecyclerView
    private lateinit var adapter: ScheduleAdapter
    private lateinit var scheduleViewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ScheduleAdapter(emptyList())
        scheduleViewModel = ScheduleViewModel(
            ScheduleRepository(
                APIClient.fitnessApiClient
            )
        )

        scheduleRecyclerView = view.findViewById(R.id.recycler_view)
        scheduleRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        scheduleRecyclerView.addItemDecoration(EmptyDividerItemDecoration())
        scheduleRecyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            scheduleViewModel.fitnessData.collect {
                if (it.isEmpty()) return@collect
                adapter.updateList(it)
            }
        }
    }

}