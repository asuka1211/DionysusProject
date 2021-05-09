package com.serma.auth.ui.base

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.theme.SecondaryColor

@Preview
@Composable
private fun PreviewAuthBaseUi() {
    AuthTextPair(R.string.auth_login_need_account, R.string.auth_registration_btn, {})
}

@Composable
fun AuthTextPair(@StringRes leftTextId: Int, @StringRes rightTextId: Int, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = leftTextId),
            Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = stringResource(id = rightTextId),
            color = SecondaryColor,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.clickable { onClick() }
        )
    }
}