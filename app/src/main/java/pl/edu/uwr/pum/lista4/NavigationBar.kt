package pl.edu.uwr.pum.lista4

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun NavigationBar(
    navController: NavHostController,
    currentRoute: String
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(MaterialTheme.colorScheme.onPrimary)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(start = 30.dp)
                .width(140.dp)
                .height(100.dp)
                .background(
                    color = if (currentRoute == "exerciseListScreen") MaterialTheme.colorScheme.primary else Color.Transparent,
                    shape = RoundedCornerShape(25.dp)
                )
                .clickable {
                    navController.navigate("exerciseListScreen")
                }
        ) {
            val customIcon: Painter = painterResource(id = R.drawable.list)
            Icon(
                painter = customIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = "Listy zadań",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Column(
            modifier = Modifier
                .padding(end = 30.dp)
                .width(140.dp)
                .height(100.dp)
                .background(
                    color = if (currentRoute == "gradesScreen") MaterialTheme.colorScheme.primary else Color.Transparent,
                    shape = RoundedCornerShape(25.dp)
                )
                .clickable {
                    navController.navigate("gradesScreen")
                }
        ) {
            val customIcon: Painter = painterResource(id = R.drawable.grade)
            Icon(
                painter = customIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = "Oceny",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally)
            )

        }
    }

}