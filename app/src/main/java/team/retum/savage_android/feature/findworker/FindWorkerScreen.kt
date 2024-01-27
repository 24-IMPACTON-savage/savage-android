package team.retum.savage_android.feature.findworker

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.view.LayoutInflater
import android.view.View
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapView
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.retum.savage_android.R
import team.retum.savage_android.feature.complete.CompleteScreen
import team.retum.savage_android.feature.loading.LoadingScreen
import team.retum.savage_android.feature.root.NavGroup
import team.retum.savage_android.ui.component.SavageBoxTextField
import team.retum.savage_android.ui.component.SavageButton
import team.retum.savage_android.ui.theme.SavageColor
import team.retum.savage_android.ui.theme.SavageTypography
import team.retum.savage_android.util.setUserLocation

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun FindWorkerScreen(
    navController: NavController
) {
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
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler {

    }

    val coroutine = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(false)
    }

    var view by remember {
        mutableStateOf(0)
    }
    val alpha1: Float by animateFloatAsState(targetValue = if (view == 1) 1f else 0f, label = "", animationSpec = TweenSpec(500))
    val alpha2: Float by animateFloatAsState(targetValue = if (view == 2) 1f else 0f, label = "", animationSpec = TweenSpec(500))

    Box {


        when (view) {
            0 -> ModalBottomSheetLayout(
                sheetShape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                ),
                sheetState = sheetState,
                sheetContent = { SetAddress() },
            ) {
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
                                modifier = Modifier
                                    .padding(bottom = 20.dp)
                                    .clickable(onClick = {}),
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
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = {
                                        coroutineScope.launch {
                                            sheetState.show()
                                        }
                                    },
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_add_24),
                                        tint = SavageColor.White,
                                        contentDescription = null,
                                    )
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
                            onClick = {
                                if (!isLoading) {
                                    isLoading = true
                                    coroutine.launch {
                                        view = 1
                                        delay(1500)
                                        view = 2
                                        delay(1500)
                                        navController.navigate(NavGroup.Main.MatchingWorker.id)
                                    }
                                }
                            },
                            text = "노동자 찾기",
                            isAbleClick = true,
                        )
                    }
                }
            }
            1 -> LoadingScreen(modifier = Modifier.alpha(alpha1))
            2 -> CompleteScreen(modifier = Modifier.alpha(alpha2))
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
    val styles = labelManager?.addLabelStyles(labelStyles)

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

@Composable
fun SetAddress() {
    var value by remember { mutableStateOf("") }
    val list = remember {
        listOf(
            Farm(
                title = "밭1",
                address = "광주광역시 서구 경열로 65번길 7-1 농성제일파크",
                id = 1,
            ),
            Farm(
                title = "밭2",
                address = "광주광역시 서구 경열로 65번길 7-1 농성제일파크",
                id = 2,
            ),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                )
            )
            .padding(horizontal = 22.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "주소 설정",
                style = SavageTypography.Body2,
            )
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = "선택",
                style = SavageTypography.Body2,
                color = SavageColor.Primary20,
            )
        }
        SavageBoxTextField(
            modifier = Modifier.padding(top = 24.dp),
            value = value,
            onValueChange = { value = it },
            hint = "지번, 도로명, 건물명으로 검색",
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {})
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                ), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = R.drawable.ic_gps),
                contentDescription = null,
            )
            Text(
                text = "현재 위치로 설정",
                style = SavageTypography.Body1,
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
            )
        }
        list.forEach {
            ListItem(
                farm = it,
                onClick = { id -> },
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        SavageButton(
            modifier = Modifier.padding(bottom = 24.dp),
            onClick = {},
            text = "주소 추가",
            isAbleClick = true,
        )
    }
}

data class Farm(
    val title: String,
    val address: String,
    val id: Long,
)

@Composable
internal fun ListItem(
    farm: Farm,
    onClick: (id: Long) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = { onClick(farm.id) },
                )
                .padding(
                    vertical = 14.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = farm.title,
                    style = SavageTypography.Body2,
                )
                Text(
                    text = farm.address,
                    style = SavageTypography.Body1,
                    color = SavageColor.Gray20,
                )
            }
            Text(
                text = "수정",
                color = SavageColor.Primary20,
                style = SavageTypography.Body2,
            )
        }
    }
    Divider(
        modifier = Modifier.fillMaxWidth(),
        color = SavageColor.Gray20,
    )
}
