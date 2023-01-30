package com.tooensure.googleadmob

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.tooensure.googleadmob.ui.theme.GoogleAdMobTheme
const val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)

        setContent {
            val vm: GoogleAdMobViewModel = viewModel()
            vm.loadAd(this)
            App {
                GoogleAdMobx(vm)
            }

        }
    }

    override fun onDestroy() {
        removeInterstitial()
        super.onDestroy()
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    GoogleAdMobTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}
@Composable
fun GoogleAdMobx(
    viewModel: GoogleAdMobViewModel
) {
    Text(text = "cd")
    viewModel.showInterstitial(LocalContext.current) {}
}
