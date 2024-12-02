package com.loc.newsapp.presentation.onboarding

import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.loc.newsapp.presentation.Dimens.MediumPadding2
import com.loc.newsapp.presentation.common.NewsButton
import com.loc.newsapp.presentation.common.NewsTextButton
import com.loc.newsapp.presentation.onboarding.component.PagerIndicator
import com.loc.newsapp.presentation.onboarding.component.onBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onBoardingScreen() {
    Column(modifier = Modifier.fillMaxWidth()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            page.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "GetStarted")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pagerState) { index ->
            onBoardingPage(page = page[index])

        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
            PagerIndicator(
                modifier = Modifier.width(52.dp),
                pageSize = page.size,
                selectedPage = pagerState.currentPage
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                val context = LocalContext.current
                val scope = rememberCoroutineScope ()
                    if(buttonState.value[0].isNotEmpty()){
                        NewsTextButton(
                            text = buttonState.value[0],
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        page = pagerState.currentPage-1
                                    )
                                }
                            }
                        )
                    }
                NewsButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if(pagerState.currentPage == 3){
                                Toast.makeText(context,"Main Screen", LENGTH_LONG).show()
                            }else{
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                    
                                )
                            }
                        }
                    }
                )

            }
        }
    }

}