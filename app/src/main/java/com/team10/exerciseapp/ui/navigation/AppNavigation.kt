package com.team10.exerciseapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team10.exerciseapp.ui.member2_junbeom.page3.Page3Screen
import com.team10.exerciseapp.ui.member2_junbeom.page4.Page4Screen
import com.team10.exerciseapp.ui.member3_Seokjin.page2.Page2Screen
import com.team10.exerciseapp.ui.member3_Seokjin.page6.Page6Screen

/**
 * 앱 전체 네비게이션 설정
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Page3.route
    ) {
        // Page 2 (팀원 3 - Seokjin)
        composable(Screen.Page2.route) {
            Page2Screen()
        }
        
        // Page 3 (팀원 2 - Junbeom) - 시작 화면
        composable(Screen.Page3.route) {
            Page3Screen()
        }
        
        // Page 4 (팀원 2 - Junbeom)
        composable(Screen.Page4.route) {
            Page4Screen()
        }
        
        // Page 6 (팀원 3 - Seokjin)
        composable(Screen.Page6.route) {
            Page6Screen()
        }
    }
}

/**
 * 화면 라우트 정의
 */
sealed class Screen(val route: String) {
    object Page2 : Screen("page2")
    object Page3 : Screen("page3")
    object Page4 : Screen("page4")
    object Page6 : Screen("page6")
}
