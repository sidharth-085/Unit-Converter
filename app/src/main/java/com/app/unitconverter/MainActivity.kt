package com.app.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.unitconverter.ui.theme.FirstApplicationTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstApplicationTheme {
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
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var iConversionFactor by remember { mutableStateOf(1.0) }
    var oConversionFactor by remember { mutableStateOf(1.0) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * iConversionFactor * oConversionFactor * 100).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter",
            modifier = Modifier,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputValue,
            label = {
                Text(text = "Enter the value")
            },
            onValueChange = {
                inputValue = it
                convertUnits()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box {
                Button(
                    onClick = {
                        iExpanded = true
                    }
                ) {
                    Text(text = inputUnit)
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(
                    expanded = iExpanded,
                    onDismissRequest = {
                        iExpanded = false;
                    }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Feet")
                        },
                        onClick = {
                            iExpanded = false;
                            inputUnit = "Feet"
                            iConversionFactor = 0.30
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Inch")
                        },
                        onClick = {
                            iExpanded = false;
                            inputUnit = "Inch"
                            iConversionFactor = 0.0254
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Meter")
                        },
                        onClick = {
                            iExpanded = false;
                            inputUnit = "Meter"
                            iConversionFactor = 1.0
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Centimeter")
                        },
                        onClick = {
                            iExpanded = false;
                            inputUnit = "Centimeter"
                            iConversionFactor = 0.01
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box {
                Button(
                    onClick = {
                        oExpanded = true
                    }
                ) {
                    Text(text = outputUnit)
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(
                    expanded = oExpanded,
                    onDismissRequest = {
                        oExpanded = false
                    }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            oExpanded = false;
                            outputUnit = "Feet"
                            oConversionFactor = 3.28
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text(text = "Inch") },
                        onClick = {
                            oExpanded = false;
                            outputUnit = "Inch"
                            oConversionFactor = 39.37
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {
                            oExpanded = false;
                            outputUnit = "Meter"
                            oConversionFactor = 1.0
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text(text = "Centimeter") },
                        onClick = {
                            oExpanded = false;
                            outputUnit = "Centimeter"
                            oConversionFactor = 100.0
                            convertUnits()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result : $outputValue $outputUnit",
            modifier = Modifier,
            color = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
fun UnitConverterPreview() {
    UnitConverter()
}