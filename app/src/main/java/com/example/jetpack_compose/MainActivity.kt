package com.example.jetpack_compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose.MainActivity.Companion.messages
import com.example.jetpack_compose.ui.theme.JetpackcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Conversation(messages)
                }
            }
        }
    }

    companion object {
        val messages = listOf<Message>(
            Message("Daniel", "message1"),
            Message(
                "Daniel",
                "Composable functions can store local state in memory by using remember, and track changes to the value passed to mutableStateOf. Composables (and its children) using this state will get redrawn automatically when the value is updated. We call this recomposition."
            ),
            Message("Daniel", "message3"),
            Message("Daniel", "message4")
        )
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.daniel),
            contentDescription = "profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(
                    1.5.dp, MaterialTheme.colors.secondary, CircleShape
                )
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        Column(
            modifier = Modifier.clickable {
                isExpanded = !isExpanded
            }
        ) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium, elevation = 1.dp, color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body, style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}

@Preview(name = "Light Theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Theme")
@Composable
fun DefaultPreview() {
    JetpackcomposeTheme {
        MessageCard(Message("Daniel", "Jetpack Compose is good"))
    }
}

@Preview
@Composable
fun PreviewConversation() {
    JetpackcomposeTheme {
        Conversation(
            messages
        )
    }
}

