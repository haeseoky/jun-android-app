package com.ocean.jun.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

object PermissionUtils {
    
    /**
     * 갤러리 접근에 필요한 권한들을 반환
     */
    fun getStoragePermissions(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13 (API 33) 이상
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            // Android 12 (API 32) 이하
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
    
    /**
     * 모든 필수 권한이 허용되었는지 확인
     */
    fun hasStoragePermissions(context: Context): Boolean {
        return getStoragePermissions().all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }
    }
    
    /**
     * 특정 권한이 허용되었는지 확인
     */
    fun hasPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
    
    /**
     * 갤러리 접근 권한 상태를 문자열로 반환 (디버깅용)
     */
    fun getPermissionStatus(context: Context): String {
        val permissions = getStoragePermissions()
        val status = permissions.map { permission ->
            val granted = hasPermission(context, permission)
            "$permission: ${if (granted) "허용됨" else "거부됨"}"
        }
        return status.joinToString("\n")
    }
}
