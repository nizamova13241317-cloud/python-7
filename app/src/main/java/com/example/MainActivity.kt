package com.example

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge(
      statusBarStyle = SystemBarStyle.dark(Color.parseColor("#0F172A")),
      navigationBarStyle = SystemBarStyle.dark(Color.parseColor("#1E293B"))
    )
    setContent {
      MyApplicationTheme {
        PyTrainerWebScreen()
      }
    }
  }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun PyTrainerWebScreen() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(ComposeColor(0xFF0F172A))
      .systemBarsPadding()
      .imePadding()
  ) {
    AndroidView(
      modifier = Modifier
        .fillMaxSize()
        .testTag("python_trainer_webview"),
      factory = { context ->
        WebView(context).apply {
          layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
          )
          setBackgroundColor(Color.parseColor("#0F172A"))
          settings.javaScriptEnabled = true
          settings.domStorageEnabled = true
          settings.useWideViewPort = true
          settings.loadWithOverviewMode = true
          settings.builtInZoomControls = false
          settings.displayZoomControls = false
          webChromeClient = WebChromeClient()
          webViewClient = WebViewClient()
          loadUrl("file:///android_asset/index.html")
        }
      }
    )
  }
}
