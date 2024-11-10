package com.embarkapps.snoozeloo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.embarkapps.snoozeloo.alarms.presentation.ui.theme.SnoozelooTheme
import com.embarkapps.snoozeloo.core.domain.navigation.Navigator
import com.embarkapps.snoozeloo.core.presentation.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            SnoozelooTheme {
                NavGraph(navigator)
            }
        }
    }
}