package com.example.composenewsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composenewsapp.presentation.Dimens.MediumPadding2
import com.example.composenewsapp.presentation.common.NewsButton
import com.example.composenewsapp.presentation.common.NextTextButton
import com.example.composenewsapp.presentation.onboarding.components.OnBoardingPage
import com.example.composenewsapp.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val pageState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pageState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pageState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PageIndicator(
                modifier = Modifier.width(52.dp),
                pageSize = pages.size,
                selectedPage = pageState.currentPage
            )
            Spacer(modifier = Modifier.width(16.dp)) // Butonlar ile PageIndicator arasında boşluk
            val scope = rememberCoroutineScope()
            if (buttonState.value[0].isNotEmpty()) {
                NextTextButton(
                    text = buttonState.value[0],
                    onClick = {
                        scope.launch {
                            pageState.animateScrollToPage(pageState.currentPage - 1)
                        }
                    }
                )
            }
            NewsButton(
                text = buttonState.value[1],
                onClick = {
                    scope.launch {
                        if (pageState.currentPage == 3) {
                            //TODO: Go to the Home Screen
                        } else {
                            pageState.animateScrollToPage(
                                page = pageState.currentPage + 1
                            )
                        }
                    }
                }
            )
        }
    }
}
