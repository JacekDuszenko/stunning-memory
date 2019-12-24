package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.HistogramView

fun CoroutineScope.display(histogramView: HistogramView) {
    launch {
        while (true) {
            histogramView.clearDisplay()
            histogramView.print()
            delay(2000L)
        }
    }
}

