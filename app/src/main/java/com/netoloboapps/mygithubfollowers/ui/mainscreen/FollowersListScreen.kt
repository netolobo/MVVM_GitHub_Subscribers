package com.netoloboapps.mygithubfollowers.ui.mainscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.netoloboapps.mygithubfollowers.R
import com.netoloboapps.mygithubfollowers.data.model.Follower
import com.netoloboapps.mygithubfollowers.ui.theme.MyGithubFollowersTheme

@Composable
fun FollowersListScreen(
    state: FollowersScreenState
) {
    val circularProgressDescription = stringResource(id = R.string.loading_followers)
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
        }
    ) { contentPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 8.dp
                )
            ) {
                items(state.followers) { follower ->
                    FollowItem(follow = follower)
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    Modifier
                        .semantics {
                        this.contentDescription = circularProgressDescription
                    }
                )
            }
            if(!state.isLoading && state.followers.isEmpty()){
                Text(
                    text = stringResource(id = R.string.empty_list_message),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.error != null) {
                Text(
                    text = state.error,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun FollowItem(
    follow: Follower
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            FollowerAvatar(
                imageUrl = follow.avatarUrl,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .size(56.dp)
            )
            FollowerDetails(
                login = follow.login,
                url = follow.url,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun FollowerAvatar(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
    }

}

@Composable
fun FollowerDetails(
    login: String,
    url: String,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = login,
            style = MaterialTheme.typography.h6
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = url,
                style = MaterialTheme.typography.body2
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun FollowItemPreview() {
    FollowItem(
        follow = Follower(
            login = "neto_lobo",
            url = "https://github.com/netolobo",
            avatarUrl = "https://avatars.githubusercontent.com/u/641469?s=400&u=a2431f2f533cea172482fd43909b85fc1e4192aa&v=4"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun FollowAvatarPreview() {
    FollowerAvatar(
        imageUrl = "https://www.visiteosusa.com.br/sites/default/files/styles/4_3_2800x2100_/public/images/attraction_image/2017-01/dda80911363c5202c2b8d0a8f4256b3c.jpeg?h=accb55f9&itok=PRCde6H0",
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun FollowerDetailPreview() {
    MyGithubFollowersTheme {
        FollowerDetails(
            login = "neto_lobo",
            url = "https://github.com/netolobo",
            modifier = Modifier
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Composable
private fun DefaultPreview() {
    MyGithubFollowersTheme {
        FollowersListScreen(
            state = FollowersScreenState(
                listOf(),
                true
            )
        )
    }
}