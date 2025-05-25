package com.ocean.jun.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ocean.jun.WebViewActivity
import com.ocean.jun.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // 기능 1 카드 클릭 이벤트 (네이버 웹뷰)
        val feature1Card: CardView = binding.root.findViewById(com.ocean.jun.R.id.feature1_card)
        feature1Card.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java).apply {
                putExtra("url", "https://www.naver.com")
                putExtra("title", "네이버")
            }
            startActivity(intent)
        }

        // 기능 2 카드 클릭 이벤트 (구글 지도)
        val feature2Card: CardView = binding.root.findViewById(com.ocean.jun.R.id.feature2_card)
        feature2Card.setOnClickListener {
            try {
                // 구글 지도 앱으로 열기 시도
                val gmmIntentUri = Uri.parse("geo:0,0?q=")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                    setPackage("com.google.android.apps.maps")
                }
                startActivity(mapIntent)
            } catch (e: Exception) {
                // 구글 지도 앱이 없으면 웹으로 열기
                val intent = Intent(requireContext(), WebViewActivity::class.java).apply {
                    putExtra("url", "https://maps.google.com")
                    putExtra("title", "구글 지도")
                }
                startActivity(intent)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}