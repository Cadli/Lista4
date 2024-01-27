package pl.edu.uwr.pum.lista4

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
fun ExerciseScreen(
    navController: NavHostController,
    exerciseList: ExerciseList
) {
    Column( modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "${exerciseList.subject} \nLista ${exerciseList.listNumber}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 15.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 31.sp
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
            items(exerciseList.exercises) { exercise ->
                Column{
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(3.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
                            .padding(start = 25.dp, top = 20.dp, end = 25.dp, bottom = 25.dp)
                    ) {
                        val ex = Exercise.createRandomExercise()
                        val points = ex.points
                        val content = ex.content

                        Text(
                            text = "pkt: $points",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 23.sp,
                            textAlign = TextAlign.End
                        )
                        Spacer(modifier = Modifier.height(0.dp))
                        Text(
                            text = "$exercise",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "$content",
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }

        NavigationBar(navController, "exerciseScreen")
    }
}
