package com.ocean.jun.ui.gallery

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ocean.jun.R
import com.ocean.jun.databinding.FragmentGalleryBinding
import com.ocean.jun.utils.PermissionUtils

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var photoAdapter: PhotoAdapter
    private val photoList = mutableListOf<String>()
    
    // 권한 요청 런처 - 여러 권한을 한번에 요청할 수 있도록 수정
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            loadPhotos()
        } else {
            Toast.makeText(requireContext(), getString(R.string.permission_gallery_required), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        
        setupRecyclerView()
        checkPermissionAndLoadPhotos()
        
        return root
    }
    
    private fun setupRecyclerView() {
        photoAdapter = PhotoAdapter(photoList)
        val recyclerView: RecyclerView = binding.root.findViewById(R.id.photos_recycler_view)
        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = photoAdapter
        }
    }
    
    private fun checkPermissionAndLoadPhotos() {
        if (PermissionUtils.hasStoragePermissions(requireContext())) {
            // 권한이 있으면 사진 로드
            loadPhotos()
        } else {
            // 권한이 없으면 요청
            val permissions = PermissionUtils.getStoragePermissions()
            requestPermissionLauncher.launch(permissions)
        }
    }
    
    private fun loadPhotos() {
        try {
            val projection = arrayOf(
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED
            )
            
            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
            
            requireContext().contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
            )?.use { cursor ->
                val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                
                photoList.clear()
                while (cursor.moveToNext()) {
                    val imagePath = cursor.getString(dataColumn)
                    if (imagePath != null) {
                        photoList.add(imagePath)
                    }
                }
                
                photoAdapter.notifyDataSetChanged()
                
                // 디버깅용 로그
                Toast.makeText(requireContext(), "사진 ${photoList.size}개를 불러왔습니다.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "사진을 불러오는 중 오류가 발생했습니다: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // Fragment가 다시 보여질 때 권한 상태를 다시 확인
        if (PermissionUtils.hasStoragePermissions(requireContext()) && photoList.isEmpty()) {
            loadPhotos()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
