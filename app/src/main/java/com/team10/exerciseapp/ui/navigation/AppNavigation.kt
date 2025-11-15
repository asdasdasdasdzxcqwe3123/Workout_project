package com.team10.exerciseapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team10.exerciseapp.ui.member1_dongho.page1.Page1Screen
import com.team10.exerciseapp.ui.member1_dongho.page5.Page5Screen
import com.team10.exerciseapp.ui.member2_junbeom.page3.Page3Screen
import com.team10.exerciseapp.ui.member2_junbeom.page4.Page4Screen
import com.team10.exerciseapp.ui.member3_Seokjin.page2.Page2Screen
import com.team10.exerciseapp.ui.member3_Seokjin.page6.Page6Screen

/**
 * 앱 전체 네비게이션 설정
 * 객체지향 원칙: 네비게이션 로직을 한 곳에 집중
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "page1"
    ) {
        // Page 1 (팀원 1 - Dongho) - 홈 화면 (시작 화면)
        composable("page1") {
            Page1Screen(
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        
        // Page 2 (팀원 3 - Seokjin) - 운동 루틴 목록
        composable("page2") {
            Page2Screen(
                onNavigate = { route -> navController.navigate(route) },
                onRoutineClick = { routineId -> navController.navigate("page6") }
            )
        }
        
        // Page 3 (팀원 2 - Junbeom) - 하체 운동 기록
        composable("page3") {
            Page3Screen(
                onNavigate = { route -> navController.navigate(route) },
                onBackClick = { navController.popBackStack() }
            )
        }
        
        // Page 4 (팀원 2 - Junbeom) - 운동 기록 캘린더
        composable("page4") {
            Page4Screen(
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        
        // Page 5 (팀원 1 - Dongho) - 운동 백과사전
        composable("page5") {
            Page5Screen(
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        
        // Page 6 (팀원 3 - Seokjin) - 루틴 상세
        composable("page6") {
            Page6Screen(
                onNavigate = { route -> navController.navigate(route) },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
