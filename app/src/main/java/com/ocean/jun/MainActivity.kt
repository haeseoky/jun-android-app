package com.ocean.jun

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.ocean.jun.databinding.ActivityMainBinding
import com.ocean.jun.utils.PermissionUtils

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        // 뒤로가기 버튼 처리
        setupBackPressHandler()

        binding.appBarMain.fab.setOnClickListener { view ->
            // 갤러리 접근 권한 체크 예시
            if (PermissionUtils.hasStoragePermissions(this)) {
                Snackbar.make(view, "갤러리 권한이 있습니다. 갤러리 기능을 사용할 수 있습니다.", Snackbar.LENGTH_LONG)
                    .setAction("확인", null)
                    .setAnchorView(R.id.fab).show()
            } else {
                Snackbar.make(view, getString(R.string.permission_gallery_required), Snackbar.LENGTH_LONG)
                    .setAction("설정", null)
                    .setAnchorView(R.id.fab).show()
                
                // 권한 상태 로그 출력 (디버깅용)
                Toast.makeText(this, PermissionUtils.getPermissionStatus(this), Toast.LENGTH_SHORT).show()
            }
        }
        
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setupBackPressHandler() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                
                // 현재 화면이 홈 화면인지 확인
                if (navController.currentDestination?.id == R.id.nav_home) {
                    // 홈 화면에서 뒤로가기 시 앱 종료 확인 다이얼로그 표시
                    showExitConfirmationDialog()
                } else {
                    // 다른 화면에서는 일반적인 뒤로가기 동작
                    if (!navController.navigateUp(appBarConfiguration)) {
                        // Navigation이 처리할 수 없는 경우 기본 뒤로가기 실행
                        isEnabled = false
                        onBackPressedDispatcher.onBackPressed()
                        isEnabled = true
                    }
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.app_exit_title))
            .setMessage(getString(R.string.app_exit_message))
            .setPositiveButton(getString(R.string.app_exit_confirm)) { _, _ ->
                // 앱 종료
                finishAffinity()
            }
            .setNegativeButton(getString(R.string.app_exit_cancel)) { dialog, _ ->
                // 다이얼로그 닫기 (홈 화면으로 돌아감)
                dialog.dismiss()
            }
            .setCancelable(true)
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
