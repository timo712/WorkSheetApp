package com.example.worksheetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.worksheetapp.screens.CreateSheetScreen
import com.example.worksheetapp.screens.EditSheetScreen
import com.example.worksheetapp.screens.HomeScreen
import com.example.worksheetapp.ui.theme.WorkSheetAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkSheetAppTheme {

            }
        }
    }
}

@Composable
fun WorkSheetApp(){
    // create a NavController
    // this object controls navigation between screen in the app
    val navController = rememberNavController()

    // NavHost connects the NavController to the navigation graph.
    // It defines all the screen (routes) that exist in the app.
    NavHost(navController = navController, startDestination = "home"){

        //This composable defines the "home screen route.
        // when the app start, this screen appears first because it is the startDestination
        composable("home"){
            // HomeScreen UI is displayed here.
            // It receives navigation functions so that buttons in the HomeScreen
            // can move the user to other screens
            HomeScreen(
                // when teh "Create Worksheet"  button is clicked,
                //navigate to the CreateSheetScreen.
                onCreateWorkSheetClick = { navController.navigate("createSheet") },

                // When a worksheet is selected, navigate to the edit/ view screen.
                // The sheetId is passed in the route so the next screen
                // knows which worksheet to loads.
                onWorkSheetClick = { sheetId -> navController.navigate("viewEditSheet/$sheetId") }
            )
        }

        //This composable defines the screen used to CREATE a new worksheet.
        // It is triggered when the user presses the create button on the HomeScreen.
        composable("createSheet"){
            // Displays the UI where a user can create a worksheet.
            CreateSheetScreen(

                //After saving, popBackStack() returns the user
                // to the previous screen ( usually HomeScreen).
                onSaveClick = {navController.popBackStack()}
            )
        }

        // This composable is used to View or EDIT an existing worksheet.
        // The sheetId is included in the route so we know which worksheet to edit.
        composable("viewEditSheet/$sheetId"){ backStackEntry ->
            // Extract the sheetId from the navigation arguments.
            // This allows the screen to load the correct worksheet data.
            val sheetId = backStackEntry.arguments?.getString("sheetId")?.toIntOrNull()

            // Open the EditSheetScreen and pass the sheetId to it.
            EditSheetScreen(
                //The screen receives the worksheet ID
                // So it can display or update the correct worksheet.
                sheetId = sheetId,

                // After saving changes, return to the previous screen.
                onSaveClick = {navController.popBackStack()}
            )
        }
    } // end NavHost
} // end fun WOrksheetApp
