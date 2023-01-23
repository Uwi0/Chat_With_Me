package com.kakapo.chat_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.kakapo.chat_detail.R
import com.kakapo.model.Message
import kotlinx.coroutines.launch

const val CONVERSATION_TEST_TAG = "ConversationTestTag"
private val JumpToBottomThreshold = 56.dp
private val ChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)

@Composable
internal fun Message(
    message: List<Message>,
    navigateToProfile: (String) -> Unit,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier) {
        LazyColumn(
            reverseLayout = true,
            state = scrollState,
            contentPadding = WindowInsets.statusBars.add(WindowInsets(top = 90.dp))
                .asPaddingValues(),
            modifier = Modifier
                .testTag(CONVERSATION_TEST_TAG)
                .fillMaxSize()
        ) {
            for (index in message.indices) {
                val prevAuthor = message.getOrNull(-1)?.author
                val nextAuthor = message.getOrNull(+1)?.author
                val content = message[index]
                val isFirstMessageByAuthor = prevAuthor != content.author
                val isLastMessageByAuthor = nextAuthor != content.author

                if (index == message.size - 1) {
                    item {
                        DayHeader(dayString = "21 Jan")
                    }
                } else if (index == 2) {
                    item {
                        DayHeader(dayString = "Today")
                    }
                }

                item {
                    Message(
                        onAuthorClicked = { name -> navigateToProfile(name) },
                        msg = message[index],
                        userIsMe = message[index].isMe,
                        isFirstMessageByAuthor = isFirstMessageByAuthor,
                        isLastMessageByAuthor = isLastMessageByAuthor
                    )
                }
            }
        }

        val jumpThreshold = with(LocalDensity.current) {
            JumpToBottomThreshold.toPx()
        }

        val jumpToBottomEnabled by remember {
            derivedStateOf {
                scrollState.firstVisibleItemIndex != 0 ||
                    scrollState.firstVisibleItemScrollOffset > jumpThreshold
            }
        }

        JumpToBottom(
            enabled = jumpToBottomEnabled,
            onClicked = { scope.launch { scrollState.animateScrollToItem(0) } },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
internal fun DayHeader(dayString: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .height(16.dp)
    ) {
        DayHeaderLine()
        Text(
            text = dayString,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        DayHeaderLine()
    }
}

@Composable
private fun RowScope.DayHeaderLine() {
    Divider(
        modifier = Modifier
            .weight(1f)
            .align(Alignment.CenterVertically),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    )
}

@Composable
private fun Message(
    onAuthorClicked: (String) -> Unit,
    msg: Message,
    userIsMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean
) {
    val borderColor = if (userIsMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.tertiary
    }

    val spaceBetweenAuthor = if (isLastMessageByAuthor) Modifier.padding(top = 8.dp) else Modifier
    Row(modifier = spaceBetweenAuthor) {
        Image(
            modifier = Modifier
                .clickable(onClick = { onAuthorClicked(msg.author) })
                .padding(horizontal = 16.dp)
                .size(42.dp)
                .border((1.5).dp, borderColor, CircleShape)
                .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
                .clip(CircleShape)
                .align(Alignment.Top),
            painter = painterResource(id = msg.authorImage),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        AuthorAndTextMessage(
            msg = msg,
            userIsMe = msg.isMe,
            isFirstMessageByAuthor = isFirstMessageByAuthor,
            isLastMessageByAuthor = isFirstMessageByAuthor,
            authorClicked = onAuthorClicked,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
    }
}


@Composable
private fun AuthorAndTextMessage(
    msg: Message,
    userIsMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
    authorClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (isLastMessageByAuthor) {
            AuthorNameTimeStamp(msg = msg)
        }
        ChatItemBubble(message = msg, userIsMe = userIsMe, authorClicked = authorClicked)
        if (isFirstMessageByAuthor) {
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun AuthorNameTimeStamp(msg: Message) {
    Row(modifier = Modifier.semantics(mergeDescendants = true) { }) {
        Text(
            text = msg.author,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = msg.timeStamp,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.alignBy(LastBaseline),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ChatItemBubble(
    message: Message,
    userIsMe: Boolean,
    authorClicked: (String) -> Unit
) {

    val backgroundBubbleColor = if (userIsMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Column {
        Surface(
            color = backgroundBubbleColor,
            shape = ChatBubbleShape
        ) {
            ClickableMessage(message = message, userIsMe = userIsMe, authorClicked = authorClicked)
        }

        message.image?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                color = backgroundBubbleColor,
                shape = ChatBubbleShape
            ) {
                Image(
                    painter = painterResource(id = it),
                    contentScale = ContentScale.Fit,
                    contentDescription = stringResource(id = R.string.attached_image),
                    modifier = Modifier.size(160.dp)
                )
            }
        }
    }
}

@Composable
private fun ClickableMessage(
    message: Message,
    userIsMe: Boolean,
    authorClicked: (String) -> Unit
) {

    val uriHandler = LocalUriHandler.current
    val styleMessage = messageFormatter(text = message.content, primary = userIsMe)

    ClickableText(
        text = styleMessage,
        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
        modifier = Modifier.padding(16.dp),
        onClick = {
            styleMessage
                .getStringAnnotations(start = it, end = it)
                .firstOrNull()
                ?.let { annotation ->
                    when (annotation.tag) {
                        SymbolAnnotationType.LINK.name -> uriHandler.openUri(annotation.item)
                        SymbolAnnotationType.PERSON.name -> authorClicked(annotation.item)
                        else -> Unit
                    }
                }
        }
    )
}
