package com.rmakiyama.sealion.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
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
    cursorBrush: Brush = SolidColor(MaterialTheme.colors.secondary),
) {
    Box(modifier = modifier.padding(top = 8.dp, bottom = 8.dp)) {
        if (value.isEmpty()) {
            Text(
                text = hint,
                style = style,
                color = style.color.copy(alpha = ContentAlpha.medium)
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = style,
            modifier = Modifier.fillMaxWidth(),
            singleLine = singleLine,
            maxLines = maxLines,
            cursorBrush = cursorBrush,
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
