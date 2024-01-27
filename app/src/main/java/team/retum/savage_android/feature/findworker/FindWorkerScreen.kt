package team.retum.savage_android.feature.findworker

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapView
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import team.retum.savage_android.R
import team.retum.savage_android.ui.component.SavageButton
import team.retum.savage_android.ui.theme.SavageColor
import team.retum.savage_android.ui.theme.SavageTypography
import team.retum.savage_android.util.setUserLocation

@Composable
fun FindWorkerScreen() {
    val fields = remember {
        listOf("밭1", "밭2", "밭3", "밭4", "밭5")
    }
    var currentValue by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }
    val rotate by animateFloatAsState(
        targetValue = if (expanded) 180f
        else 0f,
        label = "",
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            AndroidView(
                factory = {
                    initMapView(context = it)
                },
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp,
                        )
                    )
                    .background(
                        color = SavageColor.Primary40,
                    )
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
                Row(
                    modifier = Modifier.padding(bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = fields[currentValue],
                        style = SavageTypography.HeadLine1,
                        color = SavageColor.White,
                    )
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            modifier = Modifier.rotate(rotate),
                            painter = painterResource(id = R.drawable.arrow_drop_down),
                            contentDescription = null,
                            tint = SavageColor.White,
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        fields.forEachIndexed { index, s ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = s,
                                        style = SavageTypography.Body3,
                                        color = SavageColor.Black,
                                    )
                                },
                                onClick = {
                                    currentValue = index
                                    expanded = false
                                },
                            )
                        }
                    }
                }
            }
            SavageButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp,
                    ),
                onClick = { },
                text = "노동자 찾기",
                isAbleClick = true,
            )
        }
    }
}

private fun initMapView(context: Context): View {
    val view = LayoutInflater.from(context).inflate(
        /* resource = */ R.layout.select_position,
        /* root = */ null,
        /* attachToRoot = */ false,
    )

    val mapView = view.findViewById<MapView>(R.id.map_view)

    mapView.start(object : KakaoMapReadyCallback() {
        override fun onMapReady(kakaoMap: KakaoMap) {
            setUserLocation(
                context = context,
                kakaoMap = kakaoMap,
            )
            setMarker(
                context = context,
                kakaoMap = kakaoMap,
            )
        }
    })

    return view
}

@SuppressLint("MissingPermission")
private fun setMarker(
    context: Context,
    kakaoMap: KakaoMap,
) {
    val labelManager = kakaoMap.labelManager
    val labelStyle = LabelStyle.from(R.drawable.ic_marker)
    val labelStyles = LabelStyles.from(labelStyle)
    val styles =
        labelManager?.addLabelStyles(labelStyles)

    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

    val options = LabelOptions.from(
        LatLng.from(
            location?.latitude ?: 37.394660,
            location?.longitude ?: 127.111182,
        )
    ).setStyles(styles)

    val layer = labelManager?.layer
    val label = layer?.addLabel(options)
}