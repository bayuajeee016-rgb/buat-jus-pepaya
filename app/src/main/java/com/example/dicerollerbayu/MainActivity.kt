package com.example.dicerollerbayu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicerollerbayu.ui.theme.DiceRollerBayuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerBayuTheme() {
                PepayaApp()
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PepayaApp() {
    var step by remember { mutableStateOf(1) }
    var alpukatClicks by remember { mutableStateOf(0) }

    // MENAMBAHKAN ACAK KLIK: Nilai acak diawal antara 2 sampai 5
    var targetClicks by remember { mutableStateOf((2..5).random()) }

    val imageResource = when (step) {
        1 -> R.drawable.pohonpepaya
        2 -> R.drawable.pepaya
        3 -> R.drawable.juspepaya
        4 -> R.drawable.gelas
        else -> R.drawable.pohonpepaya
    }

    // Teks menyesuaikan dengan target acak ($targetClicks)
    val stepText = when (step) {
        1 -> "Tahap 1: Pohon Pepaya\n(Ketuk gambar untuk memetik)"
        2 -> "Tahap 2: Buah Pepaya\n(Ketuk gambar untuk membuat jus: $alpukatClicks/$targetClicks)"
        3 -> "Tahap 3: Jus Pepaya\n(Ketuk gambar untuk meminum)"
        4 -> "Tahap 4: Gelas Kosong\n(Ketuk gambar untuk membuat kembali)"
        else -> ""
    }
// check username
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Jus Pepaya Interactive",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Proses membuat jus pepaya",
                modifier = Modifier
                    .size(250.dp)
                    .clickable {
                        when (step) {
                            1 -> step = 2
                            2 -> {
                                alpukatClicks++
                                // Mengecek apakah klik sudah mencapai target acak
                                if (alpukatClicks >= targetClicks) {
                                    step = 3
                                    alpukatClicks = 0
                                    // Membuat target acak baru untuk putaran selanjutnya
                                    targetClicks = (2..9).random()
                                }
                            }
                            3 -> step = 4
                            4 -> step = 1
                        }
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stepText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlpukatAppPreview() {
    DiceRollerBayuTheme() {
        PepayaApp()
    }
}