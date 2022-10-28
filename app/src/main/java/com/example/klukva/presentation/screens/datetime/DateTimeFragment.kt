package com.example.klukva.presentation.screens.datetime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.klukva.R
import com.example.klukva.databinding.FragmentDateTimeBinding

class DateTimeFragment : Fragment() {
    lateinit var binding: FragmentDateTimeBinding
    private val viewModelDateTimeViewModel: DateTimeViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDateTimeBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelDateTimeViewModel.dataLive.observe(viewLifecycleOwner){
            val items:List<String> = viewModelDateTimeViewModel.dataLive.value!!
            val adapterData = ArrayAdapter(requireContext(),R.layout.dropdown_item,items)
            binding.DropdownTextData.setAdapter(adapterData)
        }

        viewModelDateTimeViewModel.durationLive.observe(viewLifecycleOwner){
            val items:List<String> =  viewModelDateTimeViewModel.durationLive.value!!
            val adapterDuration = ArrayAdapter(requireContext(),R.layout.dropdown_item,items)
            binding.DropdownDuration.setAdapter(adapterDuration)
        }

        viewModelDateTimeViewModel.timeLive.observe(viewLifecycleOwner){
            val items:List<String> = viewModelDateTimeViewModel.timeLive.value!!
            val adapterTime = ArrayAdapter(requireContext(),R.layout.dropdown_item,items)
            binding.DropdownTextTime.setAdapter(adapterTime)
        }



        binding.imMenu.setOnClickListener {
            findNavController().navigate(R.id.action_dateTimeFragment_to_profileFragment)
        }

        var n = 1
        binding.textViewCount.text = n.toString()
        binding.buttonMinus.setOnClickListener {
            if (n != 0){
                n--
                binding.textViewCount.text = n.toString()
            }
        }
        binding.buttonPlus.setOnClickListener {
            n++
            binding.textViewCount.text = n.toString()
        }
    }

}