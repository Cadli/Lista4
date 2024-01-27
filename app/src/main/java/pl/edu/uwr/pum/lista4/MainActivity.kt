package pl.edu.uwr.pum.lista4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import pl.edu.uwr.pum.lista4.ui.theme.Lista4Theme

class MainActivity : ComponentActivity() {

    private val subjectBank = arrayListOf(
        Subject("Matematyka"),
        Subject("Fizyka"),
        Subject("Informatyka"),
        Subject("Historia"),
        Subject("Geologia")
    )

    private val exerciseListBank = arrayListOf(
        ExerciseList(1, arrayListOf("Zadanie 1", "Zadanie 2", "Zadanie 3"), "Matematyka", 4.5),
        ExerciseList(2, arrayListOf("Zadanie 1", "Zadanie 2"), "Matematyka", 3.5),
        ExerciseList(1, arrayListOf("Zadanie 1"), "Fizyka", 5.0),
        ExerciseList(1, arrayListOf("Zadanie 1", "Zadanie 2", "Zadanie 3"), "Informatyka", 2.0),
        ExerciseList(2, arrayListOf("Zadanie 1"), "Informatyka", 5.0),
        ExerciseList(3, arrayListOf("Zadanie 1", "Zadanie 2"), "Matematyka", 5.0),
        ExerciseList(2, arrayListOf("Zadanie 1", "Zadanie 2", "Zadanie 3"), "Fizyka", 5.0),
        ExerciseList(1, arrayListOf("Głazy", "Skały"), "Geologia", 4.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lista4Theme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "exerciseListScreen")
                {
                    composable(
                        "exerciseListScreen"
                    ) {
                        MainScreen(navController, "exerciseListScreen", exerciseListBank, subjectBank)
                    }
                    composable(
                        "gradesScreen",
                        arguments = listOf(navArgument("argExample") { type = NavType.StringType })
                    ) {
                        MainScreen(navController, "gradesScreen", exerciseListBank, subjectBank)
                    }

                    composable(
                        "exerciseScreen/{subject}/{listNumber}",
                        arguments = listOf(
                            navArgument("subject") { type = NavType.StringType },
                            navArgument("listNumber") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val subject = backStackEntry.arguments?.getString("subject") ?: ""
                        val listNumber = backStackEntry.arguments?.getInt("listNumber") ?: 0

                        MainScreen(navController, "exerciseScreen", exerciseListBank, subjectBank, subject, listNumber)

                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavHostController,
    currentRoute: String,
    exerciseListBank: List<ExerciseList>,
    subjectBank: List<Subject>,
    selectedSubject: String = "",
    selectedListNumber: Int = 0
) {
    Surface {
        when (currentRoute) {
            "exerciseListScreen" -> ExerciseListScreen(navController, exerciseListBank) { exerciseList ->
                navController.navigate("exerciseScreen/${exerciseList.subject}/${exerciseList.listNumber}")
            }
            "gradesScreen" -> GradesScreen(navController, exerciseListBank, subjectBank)
            "exerciseScreen" -> {
                val exerciseList =
                    exerciseListBank.find { it.subject == selectedSubject && it.listNumber == selectedListNumber }

                if (exerciseList != null) {
                    ExerciseScreen(navController, exerciseList)
                }
            }
        }
    }
}