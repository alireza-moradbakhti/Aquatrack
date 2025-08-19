package com.example.aquatrack.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.aquatrack.R
import com.example.aquatrack.domain.model.WaterInTake


@Composable
fun WaterHistoryList(records: List<WaterInTake>, onDelete: (WaterInTake) -> Unit) {
    if (records.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                stringResource(R.string.action_add_glass),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(records) { record ->
                HistoryItem(
                    record,
                    records.size - records.indexOf(record),
                    onDelete = { onDelete(record) }
                )
            }
        }
    }
}