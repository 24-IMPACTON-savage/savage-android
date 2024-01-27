package team.retum.savage_android.feature

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapView
import team.retum.savage_android.R
import team.retum.savage_android.util.setUserLocation

@Composable
fun MapTest() {
    Column(modifier = Modifier.fillMaxSize()) {
        androidx.compose.ui.viewinterop.AndroidView(
            modifier = Modifier.weight(1f),
            factory = {
                initMapView(
                    context = it,
                )
            },
        )
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
        }
    })

    return view
}