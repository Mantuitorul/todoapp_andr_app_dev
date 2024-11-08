package com.neocorehorizons.bemihaildragosvelicutodoproject.ui.screens

import ToDo
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hostens.mytodoapp.utils.DetailText
import be.hostens.mytodoapp.utils.ToDoImage
import com.neocorehorizons.bemihaildragosvelicutodoproject.R
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.theme.BemihaildragosvelicutodoprojectTheme
import com.neocorehorizons.bemihaildragosvelicutodoproject.util.MockupToDo

@Composable
fun ToDoDetailScreen(toDo: ToDo, modifier: Modifier = Modifier) {

    Column (
        modifier.padding(12.dp)
    ) {
        ToDoHeader(toDo = toDo)
        Divider(thickness = 1.dp, color = Color.Red)
        DetailText(text = toDo.title)
        DetailText(text = toDo.description)
        AnimatedVisibility(visible = (toDo.status != ToDo.Status.NEW )) {
            DetailText(text = stringResource(
                R.string.assigned_to,
                toDo.assignedToUser?.firstName ?: "",
                toDo.assignedToUser?.lastName ?: ""
            )
            )
        }
        DetailText(text = stringResource(
            R.string.time_estimated_hours,
            toDo.timeEstimated
        )
        )
        DetailText(text = stringResource(
            R.string.time_remaining_hours,
            toDo.timeRemaining
        )
        )
        ToDoFooter(toDo = toDo)
    }
}

@Composable
fun ToDoHeader(toDo: ToDo, modifier: Modifier = Modifier) {
    Row(modifier.padding(16.0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ToDoImage(modifier = Modifier)
        }
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "#${toDo.number}",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Text(
                text = toDo.statusDescription,
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier
            )

        }


    }
}


@Composable
fun ToDoFooter(toDo: ToDo, modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(16.0.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
            .wrapContentHeight())
    {
        ToDoFooterRow(text = stringResource(R.string.analysis_done), checked = toDo.analysisDone)
        ToDoFooterRow(text = stringResource(R.string.development_done), checked = toDo.developmentDone)
        ToDoFooterRow(text = stringResource(R.string.review_testing_done), checked = toDo.reviewAndTestingDone)
        ToDoFooterRow(text = stringResource(R.string.acceptance_done), checked = toDo.acceptanceDone)
    }
}

@Composable
fun ToDoFooterRow(text: String, checked: Boolean, modifier: Modifier = Modifier) {
    Row{
        Column (
            modifier
                .weight(2f)
                .align(alignment = Alignment.CenterVertically)) {
            Text(text = text,
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(10.dp))
        }
        Column (modifier.weight(1f)){
            Switch(checked = checked, onCheckedChange = {}, enabled = false)
        }
    }

}

//Preview data of ToDO
class ToDoPreviewParameterProvider : PreviewParameterProvider<ToDo> {
    private val tODos = MockupToDo.getToDos()
    override val values = tODos.asSequence()
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview(@PreviewParameter(ToDoPreviewParameterProvider::class, limit = 3) toDo: ToDo) {
    BemihaildragosvelicutodoprojectTheme {
        ToDoDetailScreen(toDo)
    }
}
