package com.ocean.jun

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ocean.jun.utils.PermissionUtils

class SplashActivity : AppCompatActivity() {
    
    private val splashTimeOut: Long = 3000 // 3초
    private var permissionGranted = false
    
    // 권한 요청을 위한 ActivityResultLauncher
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissionGranted = permissions.values.all { it }
        
        if (permissionGranted) {
            Toast.makeText(this, getString(R.string.permission_gallery_granted), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.permission_gallery_denied), Toast.LENGTH_LONG).show()
        }
        
        // 권한 요청 완료 후 MainActivity로 이동
        proceedToMainActivity()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        // 상태바를 투명하게 만들어 전체 화면 효과
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // 애니메이션 효과 시작
        startAnimations()
        
        // 권한 체크 및 요청
        checkAndRequestPermissions()
    }
    
    private fun checkAndRequestPermissions() {
        if (PermissionUtils.hasStoragePermissions(this)) {
            // 이미 권한이 있는 경우
            permissionGranted = true
            Handler(Looper.getMainLooper()).postDelayed({
                proceedToMainActivity()
            }, splashTimeOut)
        } else {
            // 권한이 없는 경우 - 애니메이션 완료 후 권한 요청
            Handler(Looper.getMainLooper()).postDelayed({
                val permissions = PermissionUtils.getStoragePermissions()
                requestPermissionLauncher.launch(permissions)
            }, 1500) // 애니메이션이 어느정도 진행된 후 권한 요청
        }
    }
    
    private fun proceedToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // 부드러운 전환 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish() // SplashActivity 종료
    }
    
    private fun startAnimations() {
        val logo = findViewById<View>(R.id.splash_logo)
        val dot1 = findViewById<View>(R.id.dot1)
        val dot2 = findViewById<View>(R.id.dot2)
        val dot3 = findViewById<View>(R.id.dot3)
        
        // 로고 페이드인 + 스케일 애니메이션
        logo.alpha = 0f
        logo.scaleX = 0.5f
        logo.scaleY = 0.5f
        
        ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
        
        ObjectAnimator.ofFloat(logo, "scaleX", 0.5f, 1f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
        
        ObjectAnimator.ofFloat(logo, "scaleY", 0.5f, 1f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
        
        // 로딩 점들 애니메이션
        animateLoadingDots(dot1, 0)
        animateLoadingDots(dot2, 200)
        animateLoadingDots(dot3, 400)
    }
    
    private fun animateLoadingDots(dot: View, delay: Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            val animator = ObjectAnimator.ofFloat(dot, "alpha", 0.3f, 1f, 0.3f)
            animator.duration = 1000
            animator.repeatCount = ObjectAnimator.INFINITE
            animator.start()
        }, delay)
    }
}
