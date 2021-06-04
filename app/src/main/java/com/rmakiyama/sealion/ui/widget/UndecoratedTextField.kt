package com.rmakiyama.sealion.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmakiyama.sealion.ui.theme.SeaLionTheme

@Composable
fun UndecoratedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    style: TextStyle = LocalTextStyle.current,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    hint: String = "",
) {
    Box(modifier = modifier.padding(top = 8.dp, bottom = 8.dp)) {
        if (value.isEmpty()) {
            CompositionLocalProvider(LocalContentAlpha.provides(ContentAlpha.medium)) {
                Text(text = hint, style = style)
            }
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = style,
            modifier = Modifier.fillMaxWidth(),
            singleLine = singleLine,
            maxLines = maxLines,
        )
    }
}

@Preview(name = "undecorated text field")
@Composable
fun UndecoratedTextFieldPreview() {
    val value = ""
    SeaLionTheme {
        UndecoratedTextField(value = value, onValueChange = {}, hint = "hint text")
    }
}
