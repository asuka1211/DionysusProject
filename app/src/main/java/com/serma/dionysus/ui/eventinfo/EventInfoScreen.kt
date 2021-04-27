package com.serma.dionysus.ui.eventinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.common.ui.PersonData
import com.serma.dionysus.common.common.ui.ReadOnlyTextFieldWithTitle
import com.serma.dionysus.common.common.ui.UserCardsHolderWithTitle
import com.serma.dionysus.common.theme.BackgroundColor

@Composable
fun EventInfoScreen(data: EventInfoData) {
    Surface(modifier = Modifier.background(BackgroundColor)) {
        Column(
            modifier = Modifier
                .wrapContentHeight(align = Top)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp)
        ) {

            ReadOnlyTextFieldWithTitle(
                titleTextId = R.string.event_name,
                innerText = data.name,
            )
            ReadOnlyTextFieldWithTitle(
                titleTextId = R.string.event_date,
                innerText = data.date,
            )
            ReadOnlyTextFieldWithTitle(
                titleTextId = R.string.event_description,
                innerText = data.description,
            )
            UserCardsHolderWithTitle(
                titleTextId = R.string.manager,
                data = data.managerData
            )
            ReadOnlyTextFieldWithTitle(
                titleTextId = R.string.event_location,
                innerText = data.location,
            )
            ReadOnlyTextFieldWithTitle(
                titleTextId = R.string.budget,
                innerText = data.budget,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

data class EventInfoData(
    val name: String,
    val date: String,
    val description: String,
    val managerData: List<PersonData>,
    val location: String,
    val budget: String
)
