package br.com.jrocon.appplanetascompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.jrocon.appplanetascompose.models.Planetas
import br.com.jrocon.appplanetascompose.ui.TelaDetalhes
import br.com.jrocon.appplanetascompose.ui.theme.AppPlanetasComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPlanetasComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val planetas = listOf<Planetas>(
                        Planetas(
                            nome = "Mercurio",
                            descricao = "Planeta mais proximo do sol",
                            imagem = R.drawable.mercurio
                        ),
                        Planetas(
                            nome = "Venus",
                            descricao = "2 planeta do sistema solar",
                            imagem = R.drawable.venus
                        ),
                        Planetas(
                            nome = "Terra",
                            descricao = "Terra, nosso lar!!",
                            imagem = R.drawable.terra
                        ),
                        Planetas(
                            nome = "Marte",
                            descricao = "Planeta vermelho",
                            imagem = R.drawable.marte
                        ),
                        Planetas(
                            nome = "Jupiter",
                            descricao = "Planta gazoso",
                            imagem = R.drawable.jupiter
                        ),
                        Planetas(
                            nome = "Saturno",
                            descricao = "Planeta dos aneis",
                            imagem = R.drawable.saturno
                        ),
                        Planetas(
                            nome = "Urano",
                            descricao = "Penultimo planeta do sistema solar",
                            imagem = R.drawable.urano
                        ),
                        Planetas(
                            nome = "Neturno",
                            descricao = "Ultimo planeta do sistema solar",
                            imagem = R.drawable.netuno
                        )
                    )

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "TelaListagem") {
                        composable("TelaListagem") {
                            TelaInicial(
                                navController,
                                planetas = planetas
                            )
                        }
                        composable("TelaDetelhaPlaneta") {
                            val planeta =
                                navController.previousBackStackEntry?.arguments?.getParcelable<Planetas>(
                                    "planeta"
                                )
                            planeta?.let {
                                TelaDetalhes(navController, planeta = it)
                            }

                        }
                    }

//                    TelaDetalhes(navController, planeta = planetas[2])
                }
            }
        }
    }
}

@Composable
fun TelaInicial(navController: NavHostController, planetas: List<Planetas>) {
    LazyColumn() {
        itemsIndexed(planetas) { _, item ->
            meuCard(navController, item)
        }
    }
}

@Composable
fun meuCard(navController: NavHostController, planeta: Planetas) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, color = Color(0x77f5f5f5)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(120.dp)
            .clickable {
                navController.currentBackStackEntry?.arguments = Bundle().apply {
                    putParcelable("planeta", planeta)
                }
                navController.navigate("TelaDetelhaPlaneta")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = planeta.imagem),
                contentDescription = "",
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                text = planeta.nome,
                style = TextStyle(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
        }
    }
}