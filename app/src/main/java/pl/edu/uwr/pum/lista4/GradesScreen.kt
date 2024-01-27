package pl.edu.uwr.pum.lista4

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun GradesScreen(
    navController: NavHostController,
    exerciseListBank: List<ExerciseList>,
    subjectBank: List<Subject>
) {
    Column( modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Moje Oceny",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 25.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(MaterialTheme.colorScheme.onPrimary)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(535.dp)
                .padding(start = 15.dp, end = 15.dp, top = 15.dp)
        ) {
            items(subjectBank) { subject ->
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(3.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
                            .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)

                    ) {
                        val name = subject.name
                        var sumGrades = 0.0
                        var quantity = 0

                        for (exerciseList in exerciseListBank) {
                            if (exerciseList.subject == name) {
                                sumGrades += exerciseList.grade
                                quantity++
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth())
                        {
                            Text(
                                text = name,
                                modifier = Modifier.weight(1f),
                                fontSize = 28.sp
                            )
                            Text(
                                text = if (quantity > 0) "Średnia: ${"%.2f".format(sumGrades/quantity)}" else "Średnia: Brak",
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(1f),
                                fontSize = 23.sp
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth())
                        {
                            Text(
                                text = "Liczba list: $quantity",
                                modifier = Modifier.weight(1f),
                                fontSize = 18.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
        NavigationBar(navController, "gradesScreen")
    }
}