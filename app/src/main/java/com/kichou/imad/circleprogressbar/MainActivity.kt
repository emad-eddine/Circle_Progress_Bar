package com.kichou.imad.circleprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kichou.imad.circleprogressbar.ui.theme.CircleProgressBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // content

            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {

                Column (Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally){

                    CircleProgeessBar(percentage = 0.8f, number =120)
                    Spacer(modifier = Modifier.height(10.dp))

                    CircleProgeessBar(
                        percentage = 0.6f,
                        number =500,
                        animationDelay = 350,
                        color = Color.Blue)
                    Spacer(modifier = Modifier.height(10.dp))

                    CircleProgeessBar(percentage = 0.9f,
                        number =762,
                        animationDelay = 600,
                        color = Color.Red)


                }


            }


        }
    }
}


@Composable
fun CircleProgeessBar(

    percentage : Float ,
    number : Int,
    fontSize : TextUnit = 28.sp,
    raduis : Dp = 50.dp,
    color : Color = Color.Green,
    strokeWidth : Dp = 8.dp,
    animationDuration : Int = 1000,
    animationDelay : Int = 10, ){

    var isAnimationPlayed by remember {
        mutableStateOf(false)
    }


    val currentPercentage =  animateFloatAsState(
        targetValue = if (isAnimationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        ),
        label = ""
    )


    LaunchedEffect(key1 = true){
        isAnimationPlayed = true
    }


    Box(modifier = Modifier.size(raduis * 2f),
        contentAlignment = Alignment.Center){

        Canvas(modifier = Modifier.size(raduis * 2f)){

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * currentPercentage.value,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

        }
        
        Text(text = (currentPercentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp))


    }


}