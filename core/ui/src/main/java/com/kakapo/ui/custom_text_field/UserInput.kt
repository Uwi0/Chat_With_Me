package com.kakapo.ui.custom_text_field

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kakapo.designsystem.R
import com.kakapo.model.emojis
import com.kakapo.ui.AppPreview
import com.kakapo.ui.FunctionalityNotAvailablePanel
import com.kakapo.ui.FunctionalityNotAvailablePopup

enum class InputSelector {
    NONE,
    MAP,
    DM,
    EMOJI,
    PHONE,
    PICTURE,
    VOICE
}

enum class EmojiStickerSelector {
    EMOJI,
    STICKER,
    GIF
}

private const val EMOJI_COLUMNS = 10

val KeyboardShownKey = SemanticsPropertyKey<Boolean>("KeyboardShownKey")
var SemanticsPropertyReceiver.keyboardShownProperty by KeyboardShownKey

@Composable
fun UserInput(
    onMessageSent: (String) -> Unit,
    modifier: Modifier = Modifier,
    resetScroll: () -> Unit = {}
) {
    var currentInputSelector by rememberSaveable { mutableStateOf(InputSelector.NONE) }
    val dismissKeyboard = { currentInputSelector = InputSelector.NONE }

    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    var textFieldFocusState by remember { mutableStateOf(false) }

    Surface(tonalElevation = 2.dp) {
        Column(modifier = modifier) {
            UserInputText(
                textFieldValue = textState,
                onTextChangeValue = { textState = it },
                keyboardShown = currentInputSelector == InputSelector.NONE && textFieldFocusState,
                onTextFieldFocused = { focused ->
                    if (focused) {
                        currentInputSelector = InputSelector.NONE
                        resetScroll()
                    }
                    textFieldFocusState = focused
                },
                focusState = textFieldFocusState
            )
            UserInputSelector(
                onSelectorChange = { currentInputSelector = it },
                sendMessageEnable = textState.text.isNotBlank(),
                onMessageSent = {
                    onMessageSent(textState.text)
                    textState = TextFieldValue()
                    resetScroll()
                    dismissKeyboard()
                },
                currentInputSelector = currentInputSelector
            )
            SelectorExpanded(
                onCloseRequested = dismissKeyboard,
                onTextAdded = { textState = textState.addText(it) },
                currentSelector = currentInputSelector
            )
        }
    }
}

@Composable
private fun UserInputText(
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChangeValue: (TextFieldValue) -> Unit,
    textFieldValue: TextFieldValue,
    keyboardShown: Boolean,
    onTextFieldFocused: (Boolean) -> Unit,
    focusState: Boolean
) {
    val label = stringResource(id = R.string.textfield_desc)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .semantics {
                contentDescription = label
                keyboardShownProperty = keyboardShown
            },
        horizontalArrangement = Arrangement.End
    ) {
        Surface {
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .weight(1f)
                    .align(Alignment.Bottom)
            ) {
                var lastFocusState by remember { mutableStateOf(false) }
                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { onTextChangeValue(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp)
                        .align(Alignment.CenterStart)
                        .onFocusChanged { state ->
                            if (lastFocusState != state.isFocused) {
                                onTextFieldFocused(state.isFocused)
                            }
                            lastFocusState = state.isFocused
                        },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = ImeAction.Send
                    ),
                    maxLines = 1,
                    cursorBrush = SolidColor(LocalContentColor.current),
                    textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current)
                )

                val disableContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                if (textFieldValue.text.isEmpty() && !focusState) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 32.dp),
                        text = stringResource(id = R.string.hint),
                        style = MaterialTheme.typography.bodyLarge.copy(color = disableContentColor)
                    )
                }
            }
        }
    }
}


@Composable
private fun UserInputSelector(
    onSelectorChange: (InputSelector) -> Unit,
    sendMessageEnable: Boolean,
    onMessageSent: () -> Unit,
    currentInputSelector: InputSelector,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(72.dp)
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val scrollState = rememberScrollState()
        Row(modifier = Modifier.horizontalScroll(scrollState)) {
            InputSelectorButton(
                onClick = { onSelectorChange(InputSelector.EMOJI) },
                icon = Icons.Outlined.Mood,
                description = stringResource(id = R.string.emoji_selector_des),
                selected = currentInputSelector == InputSelector.EMOJI
            )
            InputSelectorButton(
                onClick = { onSelectorChange(InputSelector.DM) },
                icon = Icons.Outlined.AlternateEmail,
                description = stringResource(id = R.string.dm_desc),
                selected = currentInputSelector == InputSelector.DM
            )
            InputSelectorButton(
                onClick = { onSelectorChange(InputSelector.PICTURE) },
                icon = Icons.Outlined.InsertPhoto,
                description = stringResource(id = R.string.phot_desc),
                selected = currentInputSelector == InputSelector.PICTURE
            )
            InputSelectorButton(
                onClick = { onSelectorChange(InputSelector.MAP) },
                icon = Icons.Outlined.Place,
                description = stringResource(id = R.string.map_selector_desc),
                selected = currentInputSelector == InputSelector.MAP
            )
            InputSelectorButton(
                onClick = { onSelectorChange(InputSelector.PHONE) },
                icon = Icons.Outlined.Duo,
                description = stringResource(id = R.string.video_chat_desc),
                selected = currentInputSelector == InputSelector.PHONE
            )
            InputSelectorButton(
                onClick = { onSelectorChange(InputSelector.VOICE) },
                icon = Icons.Outlined.VoiceChat,
                description = stringResource(id = R.string.voice_chat_desc),
                selected = currentInputSelector == InputSelector.VOICE
            )
        }
        val border = if (!sendMessageEnable) {
            BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
        } else {
            null
        }

        Spacer(modifier = Modifier.weight(1f))

        val disableContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

        val buttonColor = ButtonDefaults.buttonColors(
            disabledContainerColor = Color.Transparent,
            disabledContentColor = disableContentColor
        )


        Button(
            modifier = Modifier.height(36.dp),
            enabled = sendMessageEnable,
            onClick = onMessageSent,
            colors = buttonColor,
            border = border,
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = stringResource(id = R.string.send),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun InputSelectorButton(
    onClick: () -> Unit,
    icon: ImageVector,
    description: String,
    selected: Boolean
) {
    val backgroundModifier = if (selected) {
        Modifier.background(
            color = MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(14.dp)
        )
    } else {
        Modifier
    }
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp)
            .then(backgroundModifier)
    ) {
        val tint = if (selected) {
            MaterialTheme.colorScheme.onSecondary
        } else {
            MaterialTheme.colorScheme.secondary
        }
        Icon(
            icon,
            tint = tint,
            modifier = Modifier.padding(16.dp),
            contentDescription = description
        )
    }
}

@Composable
private fun SelectorExpanded(
    currentSelector: InputSelector,
    onCloseRequested: () -> Unit,
    onTextAdded: (String) -> Unit
) {
    if (currentSelector == InputSelector.NONE) return

    val focusRequester = FocusRequester()

    SideEffect {
        if (currentSelector == InputSelector.EMOJI) {
            focusRequester.requestFocus()
        }
    }

    Surface(tonalElevation = 8.dp) {
        when (currentSelector) {
            InputSelector.EMOJI -> EmojiSelector(onTextAdded = onTextAdded, focusRequester = focusRequester)
            InputSelector.MAP -> FunctionalityNotAvailablePanel()
            InputSelector.DM -> NotAvailablePopUp(onCloseRequested)
            InputSelector.PHONE -> FunctionalityNotAvailablePanel()
            InputSelector.PICTURE -> FunctionalityNotAvailablePanel()
            InputSelector.VOICE -> FunctionalityNotAvailablePanel()
            else -> throw NotImplementedError()
        }
    }

}

@Composable
private fun EmojiSelector(
    onTextAdded: (String) -> Unit,
    focusRequester: FocusRequester
) {
    var selected by remember { mutableStateOf(EmojiStickerSelector.EMOJI) }

    val label = stringResource(id = R.string.emoji_selector_des)
    Column(
        modifier = Modifier
            .focusRequester(focusRequester)
            .focusTarget()
            .semantics { contentDescription = label }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            ExtenderSelectorInnerButton(
                text = stringResource(id = R.string.emoji_label),
                onClick = { selected = EmojiStickerSelector.EMOJI },
                selected = selected == EmojiStickerSelector.EMOJI,
                modifier = Modifier.weight(1f)
            )
            ExtenderSelectorInnerButton(
                text = stringResource(id = R.string.sticker_label),
                onClick = { selected = EmojiStickerSelector.STICKER },
                selected = selected == EmojiStickerSelector.STICKER,
                modifier = Modifier.weight(1f)
            )
            ExtenderSelectorInnerButton(
                text = stringResource(id = R.string.gif_label),
                onClick = { selected = EmojiStickerSelector.GIF },
                selected = selected == EmojiStickerSelector.GIF,
                modifier = Modifier.weight(1f)
            )
        }
        Row(modifier = Modifier.verticalScroll(rememberScrollState())) {
            EmojiTable(onTextAdded = onTextAdded, modifier = Modifier.padding(8.dp))
        }
        if (selected != EmojiStickerSelector.EMOJI) {
            NotAvailablePopUp(onDismissed = { selected = EmojiStickerSelector.EMOJI })
        }
    }
}

@Composable
private fun ExtenderSelectorInnerButton(
    text: String,
    onClick: () -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val colors = ButtonDefaults.buttonColors(
        containerColor = if (selected) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
        else Color.Transparent,
        disabledContentColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.74f)
    )
    TextButton(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
            .height(36.dp),
        colors = colors,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
private fun EmojiTable(
    onTextAdded: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        repeat(4) { x ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(EMOJI_COLUMNS) { y ->
                    val emoji = emojis[x * EMOJI_COLUMNS + y]
                    Text(
                        modifier = Modifier
                            .clickable { onTextAdded(emoji) }
                            .sizeIn(minWidth = 42.dp, minHeight = 42.dp)
                            .padding(8.dp),
                        text = emoji,
                        style = LocalTextStyle.current.copy(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun NotAvailablePopUp(onDismissed: () -> Unit) {
    FunctionalityNotAvailablePopup(onDismiss = onDismissed)
}

private fun TextFieldValue.addText(newString: String): TextFieldValue {
    val newText = this.text.replaceRange(
        this.selection.start,
        this.selection.end,
        newString
    )
    val newSelection = TextRange(
        start = newText.length,
        end = newText.length
    )

    return this.copy(text = newText, selection = newSelection)
}

@AppPreview
@Composable
private fun PreviewUserInput() {
    UserInput(onMessageSent = {})
}