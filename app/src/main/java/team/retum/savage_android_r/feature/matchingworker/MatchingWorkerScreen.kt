package team.retum.savage_android_r.feature.matchingworker

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.launch
import team.retum.savage_android_r.R
import team.retum.savage_android_r.data.RetrofitClient
import team.retum.savage_android_r.feature.root.NavGroup
import team.retum.savage_android_r.model.Worker
import team.retum.savage_android_r.ui.component.SavageAppBar
import team.retum.savage_android_r.ui.component.SavageButton
import team.retum.savage_android_r.ui.theme.SavageColor
import team.retum.savage_android_r.ui.theme.SavageTypography
import team.retum.savage_android_r.ui.theme.savageClickable
import team.retum.savage_android_r.util.Constant
import team.retum.savage_android_r.util.PermissionUtil

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
internal fun MatchingWorkerScreen(
    navController: NavController
) {
    val context = LocalContext.current

    var latitude by remember {
        mutableStateOf<Double?>(null)
    }

    var longitude by remember {
        mutableStateOf<Double?>(null)
    }

    var isAllowLocationPermission by rememberSaveable { mutableStateOf(false) }

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        if (permissionsMap.values.isEmpty()) return@rememberLauncherForActivityResult
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        isAllowLocationPermission = areGranted
    }

    LaunchedEffect(true) {
        PermissionUtil.requestPermissions(context, locationPermissions, launcherMultiplePermissions)
    }
    val coroutine = rememberCoroutineScope()
    // 마지막 위치 불러오는 함수
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locations: LocationResult) {
            for (l in locations.locations) {
//                currentLocation = LatLng.from(location.latitude, location.longitude)
                latitude = l.latitude
                longitude = l.longitude
                Log.d(
                    Constant.TAG,
                    "w - ${l.latitude} g - ${l.longitude} - onLocationResult() called"
                )
            }
        }
    }
    var workers = remember { mutableStateOf(emptyList<Worker>()) }

    coroutine.launch {
        workers.value = RetrofitClient.userApi.getNearUser(latitude ?: 0.0, longitude ?: 0.0).data!!
    }


    Column {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .paint(painter = painterResource(id = R.drawable.bg_matching_worker))
        ) {
            SavageAppBar(
                modifier = Modifier.padding(top = 8.dp),
                color = SavageColor.Transparent,
                callback = {
                    navController.popBackStack()
                },
                content = {},
                tint = SavageColor.White,
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "안녕하세요! \uD83D\uDC4B",
                    color = SavageColor.White,
                    style = SavageTypography.HeadLine1,
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = workers.value[0].name + "님 외 " + (workers.value.size - 1) + "명",
                    color = SavageColor.White,
                    style = SavageTypography.Body2,
                )
                Text(
                    modifier = Modifier.padding(top = 32.dp),
                    text = "편하게" + workers.value[0].introduce + "라고 불러주세요!",
                    color = SavageColor.White,
                    style = SavageTypography.Body2,
                )
            }
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                modifier = Modifier.padding(top = 28.dp),
                text = "시간대",
                style = SavageTypography.Body1,
                color = SavageColor.Gray60,
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "12:00 ~ 15:00",
                style = SavageTypography.HeadLine1,
            )
            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = "입금 정보",
                style = SavageTypography.Body1,
                color = SavageColor.Gray60,
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "시간 당 / 20000원",
                style = SavageTypography.HeadLine1,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "무료 매칭이 4번 남았어요!",
            color = SavageColor.Gray40,
            style = SavageTypography.Body1,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 14.dp)
                .savageClickable(rippleEnable = false) {
                    navController.navigate(NavGroup.Main.Membership.id)
                },
            text = "지금 바로 구독하기",
            style = SavageTypography.Body2,
            color = SavageColor.Primary40,
        )
        Spacer(modifier = Modifier.height(8.dp))
        SavageButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "전화 걸기",
            isAbleClick = true,
            onClick = {},
        )
        Spacer(modifier = Modifier.padding(bottom = 24.dp))

    }
}