package be.hostens.mytodoapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neocorehorizons.bemihaildragosvelicutodoproject.R


@Composable
fun DetailText(text: String, modifier: Modifier = Modifier) {
    Text(text = text,
        fontSize = 20.sp,
        modifier = modifier.padding(10.dp))
}


@Composable
fun ToDoImage(modifier: Modifier = Modifier) {
    val toDOImage = painterResource(id = R.drawable.neocore_horizons_logo_v1_whiteth_nobg_rect)
    Image(
        painter = toDOImage,
        contentDescription = stringResource(R.string.neocoreshorizons_logo),
        modifier = modifier
    )
}

