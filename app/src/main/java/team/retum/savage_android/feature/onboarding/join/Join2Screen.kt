package team.retum.savage_android.feature.onboarding.join

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import team.retum.savage_android.data.RetrofitClient
import team.retum.savage_android.data.api.AuthApi
import team.retum.savage_android.model.request.SignInRequest
import team.retum.savage_android.model.request.SignUpRequest
import team.retum.savage_android.ui.component.SavageAppBar
import team.retum.savage_android.ui.component.SavageButton
import team.retum.savage_android.ui.component.SavageTextField
import team.retum.savage_android.ui.theme.SavageColor
import team.retum.savage_android.ui.theme.SavageTypography
import team.retum.savage_android.ui.theme.rememberKeyboardIsOpen
import team.retum.savage_android.ui.theme.savageClickable
import team.retum.savage_android.util.Constant.TAG
import team.retum.savage_android.util.PermissionUtil.requestPermissions
import java.io.IOException
import java.util.Locale


private val locationPermissions = arrayOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

@Composable
private fun Title(name: String) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp)
    ) {
        Text(
            text = name + "님! ",
            style = SavageTypography.HeadLine1
        )
        Text(
            text = "전화번호",
            style = SavageTypography.HeadLine1,
            color = SavageColor.Primary40
        )
        Text(
            text = "를 알려주세요!",
            style = SavageTypography.HeadLine1
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Join2Screen(
    navController: NavController,
    name: String
) {
    val context = LocalContext.current

    var tel by remember { mutableStateOf("") }
    val keyboardShow by rememberKeyboardIsOpen()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var isAllowLocationPermission by rememberSaveable { mutableStateOf(false) }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        if (permissionsMap.values.isEmpty()) return@rememberLauncherForActivityResult
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        isAllowLocationPermission = areGranted
    }

    // 내 위치 조회 요청 클라이언트
    val fusedLocationClient by remember {
        mutableStateOf(
            LocationServices.getFusedLocationProviderClient(
                context
            )
        )
    }

    var latitude by remember {
        mutableStateOf<Double?>(null)
    }

    var longitude by remember {
        mutableStateOf<Double?>(null)
    }

    // 마지막 위치 불러오는 함수
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locations: LocationResult) {
            for (location in locations.locations) {
//                currentLocation = LatLng.from(location.latitude, location.longitude)
                latitude = location.latitude
                longitude = location.longitude
                Log.d(
                    TAG,
                    "w - ${location.latitude} g - ${location.longitude} - onLocationResult() called"
                )
            }
        }
    }

    LaunchedEffect(true) {
        requestPermissions(context, locationPermissions, launcherMultiplePermissions)
    }

    fun getAllowLocationPermission() = ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    // 1초 마다 위치 업데이트 되었는지 체크
    fun locationUpdate() {
        if (!getAllowLocationPermission()) return

        val locationRequest = LocationRequest.Builder(1000) // 1초마다 체크
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .build()

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }


    LaunchedEffect(isAllowLocationPermission) {
        if (isAllowLocationPermission) {
            locationUpdate()
        }
    }

    SavageAppBar(
        callback = {
            navController.popBackStack()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Title(name = name)
            Spacer(modifier = Modifier.padding(top = 48.dp))
            SavageTextField(value = tel, hint = "성함을 입력해 주세요.", onValueChange = { tel = it })
            Spacer(modifier = Modifier.weight(1f))
            SavageButton(
                modifier = if (!keyboardShow) Modifier.padding(horizontal = 16.dp) else Modifier,
                onClick = {
                    if (tel.isNotBlank()) {
                        showBottomSheet = true
                    } else {
                        // handling
                    }
                },
                text = "완료",
                isAbleClick = tel.isNotBlank(),
                isKeyShow = keyboardShow
            )
            if (!keyboardShow)
                Spacer(modifier = Modifier.padding(bottom = 24.dp))

            if (showBottomSheet) {
                ModalBottomSheet(
                    containerColor = Color.White,
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    // Sheet content
//                    Button(onClick = {
//                        scope.launch { sheetState.hide() }.invokeOnCompletion {
//                            if (!sheetState.isVisible) {
//                                showBottomSheet = false
//                            }
//                        }
//                    }) {
//                        Text("Hide bottom sheet")
//                    }

                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 24.dp),
                            text = if (latitude == null || longitude == null) "잠시만 기다려주세요" else getAddress(
                                context,
                                latitude!!,
                                longitude!!
                            ),
                            style = SavageTypography.Body3,
                            color = SavageColor.Gray30
                        )
                        Text(
                            text = "현재 계신 곳이 이 주소가 맞나요?",
                            style = SavageTypography.HeadLine1,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "아니요, 여기가 아니에요",
                            style = SavageTypography.Body2,
                            color = SavageColor.Primary40,
                            modifier = Modifier
                                .padding(top = 48.dp)
                                .savageClickable(rippleEnable = false) {

                                }
                                .align(Alignment.CenterHorizontally)
                        )
                        SavageButton(
                            modifier = Modifier.padding(top = 20.dp, bottom = 30.dp),
                            onClick = {
                                RetrofitClient.userApi.signUp(
                                    SignUpRequest(
                                        name = name,
                                        number = tel,
                                        address = getAddress(context, latitude!!, longitude!!)
                                    )
                                )
                            },
                            text = "네, 여기에요",
                            isAbleClick = true
                        )
                    }
                }
            }
        }
    }
}

fun getAddress(mContext: Context?, lat: Double, lng: Double): String {
    var nowAddr = "대한민국 경기도 성남시 분당구 판교로 242"
    val geocoder = Geocoder(mContext!!, Locale.KOREA)
    val address: List<Address>?
    try {
        address = geocoder.getFromLocation(lat, lng, 1)
        if (!address.isNullOrEmpty()) {
            nowAddr = address[0].getAddressLine(0).toString()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return nowAddr
}