package com.ocean.jun

import android.content.Intent
import android.net.Uri
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
            // 이메일 작성 기능
            sendEmail()
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

    private fun sendEmail() {
        try {
            // 먼저 Gmail 앱으로 시도
            if (tryGmailApp()) {
                return
            }
            
            // Gmail 앱이 없으면 일반 이메일 앱으로 시도
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("haeseoky@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Jun 앱에서 보낸 메일")
                putExtra(Intent.EXTRA_TEXT, "안녕하세요!\n\nJun 앱을 통해 메일을 보냅니다.\n\n감사합니다.")
            }
            
            if (emailIntent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(emailIntent, "이메일 앱 선택"))
            } else {
                // 이메일 앱이 없으면 웹 Gmail로 시도
                openWebGmail()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "이메일 전송 중 오류가 발생했습니다: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    
    private fun tryGmailApp(): Boolean {
        return try {
            val gmailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                setPackage("com.google.android.gm")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("haeseoky@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Jun 앱에서 보낸 메일")
                putExtra(Intent.EXTRA_TEXT, "안녕하세요!\n\nJun 앱을 통해 메일을 보냅니다.\n\n감사합니다.")
            }
            
            if (gmailIntent.resolveActivity(packageManager) != null) {
                startActivity(gmailIntent)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
    
    private fun openWebGmail() {
        try {
            val subject = Uri.encode("Jun 앱에서 보낸 메일")
            val body = Uri.encode("안녕하세요!\n\nJun 앱을 통해 메일을 보냅니다.\n\n감사합니다.")
            val gmailUrl = "https://mail.google.com/mail/?view=cm&to=haeseoky@gmail.com&su=$subject&body=$body"
            
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(gmailUrl))
            
            if (webIntent.resolveActivity(packageManager) != null) {
                startActivity(webIntent)
                Toast.makeText(this, "웹 브라우저에서 Gmail을 열었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "이메일을 보낼 수 있는 앱이 없습니다. Play 스토어에서 Gmail을 설치해주세요.", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "웹 Gmail 열기 실패: ${e.message}", Toast.LENGTH_LONG).show()
        }
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
