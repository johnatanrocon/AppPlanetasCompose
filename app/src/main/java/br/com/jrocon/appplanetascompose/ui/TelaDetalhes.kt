package br.com.jrocon.appplanetascompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.jrocon.appplanetascompose.models.Planetas

@Composable
fun TelaDetalhes(navHostController: NavHostController, planeta: Planetas) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(15.dp)
        ) {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = planeta.imagem),
                contentDescription = "",
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = planeta.nome,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    text = planeta.descricao,
                    textAlign = TextAlign.Center
                )
            }
        }
        Button(
            onClick = {
                navHostController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Voltar")
        }
    }
}