package com.gahov.musenergy.feature.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import com.gahov.musenergy.common.composable.Colors.BlueGray15
import com.gahov.musenergy.common.composable.Fonts.circularXXFontFamily
import com.gahov.musenergy.common.extensions.LoadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ProfileScreen()
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val profileState = viewModel.userData.observeAsState()

    profileState.value?.let { profile ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadImage(
                modifier = Modifier
                    .size(250.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape),
                url = profile.avatar
            )
            Spacer(modifier = Modifier.height(46.dp))
            ProfileField(label = "Name", value = profile.name.toString())
            ProfileField(label = "Last Name", value = profile.lastName.toString())
            ProfileField(label = "Birth date", value = profile.birthDate.toString())
            ProfileField(label = "Email", value = profile.email.toString())
        }
    }
}

@Composable
fun ProfileField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(top = 2.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
            text = label, style = TextStyle(
                fontFamily = circularXXFontFamily,
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )
        )
        Text(
            modifier = Modifier.padding(top = 2.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
            text = value, style = TextStyle(
                fontFamily = circularXXFontFamily,
                fontSize = 20.sp,
                color = BlueGray15,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}
