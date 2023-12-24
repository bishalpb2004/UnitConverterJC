package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember{mutableStateOf("")}
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Metres") }
    var outputUnit by remember{ mutableStateOf("Metres") }
    var iExpanded by remember{ mutableStateOf(false) }
    var oExpanded by remember{ mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oConversionFactor= remember { mutableStateOf(1.00) }

    val customTextStyle= androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Red

    )

    fun convertUnits(){

        //?: - elvis operator
        var inputValueDouble=inputValue.toDoubleOrNull() ?: 0.0
        val result=(inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() /100.0
        outputValue=result.toString()


    }

    Column(
        modifier=Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Unit Converter", modifier=Modifier.padding(30.dp),
            style = customTextStyle
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                // Here goes what should happen with value being changed
                inputValue=it
                convertUnits()
        },
            label = { Text(text = "Enter Value:")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //input Box
            Box{
                //input Button
                Button(onClick = { iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = null)
                }
                DropdownMenu(expanded =iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text(text ="Centimetres") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Centimetres"
                            conversionFactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Metres") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Metres"
                            conversionFactor.value=1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Feet"
                            conversionFactor.value=0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimetres") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Millimetres"
                            conversionFactor.value=0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = null)
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text ="Centimetres") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Centimetres"
                            oConversionFactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Metres") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Metres"
                            oConversionFactor.value=1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Feet") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Feet"
                            oConversionFactor.value=0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Milimetres") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Millimetres"
                            oConversionFactor.value=0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}