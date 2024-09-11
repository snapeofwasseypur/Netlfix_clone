package com.example.netflixclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.netflixclone.ui.theme.NetflixCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .statusBarsPadding()
                    .verticalScroll(rememberScrollState())
            ) {
                TopAppSection()
                NetflixContentChooser()
                FeaturedMovieSection()
                AddMovieList(movieType = "Watch It Again")
                AddMovieList(movieType = "Favourite")
                AddMovieList(movieType = "Latest Release")
                AddMovieList(movieType = "Action Thriller")
                AddMovieList(movieType = "Drama")
            }
        }
    }
}

@Composable
@Preview
fun TopAppSection(){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(painter = painterResource(id = R.drawable.main),
                contentDescription = "main_icon",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(60.dp)
            )
            Row() {
                Image(painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "ic_search",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(35.dp)
                    )
                Image(painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "ic_profile",
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(35.dp)
                    )
            }

        }

}

@Composable
@Preview
fun NetflixContentChooser(){
    Row (modifier = Modifier
        .fillMaxWidth()
        .background(Color.Black)
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text ="TV Shows", color = Color.White, fontSize = 20.sp)
        Text(text ="Movies", color = Color.White, fontSize = 20.sp)
        Row() {
            Text(text ="Categories", color = Color.White, fontSize = 20.sp)
            Image(painter = painterResource(id = R.drawable.ic_drop), contentDescription ="Drop_icon" )
        }

    }
}

@Composable
@Preview
fun FeaturedMovieSection(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(top = 40.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Featured image
        Image(painter = painterResource(id = randomMovieSelector().random().image), contentDescription = "FeaturePoster",
            modifier = Modifier.size(350.dp)
            )

        //for section of type of movie
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 80.dp, end = 80.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = " Explosive ",color=Color.White)
            Text(text = " • ",color=Color.LightGray)
            Text(text = " Slick ",color=Color.White)
            Text(text = " • ",color=Color.LightGray)
            Text(text = " Action ",color=Color.White)
            Text(text = " • ",color=Color.LightGray)
            Text(text = " Thriller ",color=Color.White)
        }

        //for third section for list play etc
        Row (modifier = Modifier
            .padding(top = 20.dp, start = 60.dp, end = 60.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically){

            //to at last items in list play btn etc
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.ic_add), contentDescription = "Add")
                Text(text = "My List", color = Color.LightGray, fontSize = 10.sp)
            }

            Button(onClick = {},
            colors= ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "Play", color = Color.Black, fontSize = 15.sp)
            }
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.ic_info), contentDescription = "Add")
                Text(text = "Info", color = Color.LightGray,fontSize = 10.sp)
            }
        }
    }
}


@Composable
fun AddMovieList(movieType:String){

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Black)) {

        Text(text = movieType,
            color = Color.LightGray,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 20.dp, start = 10.dp))

        LazyRow() {
            itemsIndexed(randomMovieSelector()){
                index, item ->
                MoviesCollectionUiModel(image = item.image)
            }
        }

    }
}

@Composable
fun MoviesCollectionUiModel(image: Int){
    Image(painter = painterResource(id = image), contentDescription ="moviecollectionimage",
        modifier = Modifier
            .padding(top = 10.dp, start = 4.dp, end = 4.dp)
            .width(150.dp)
            .height(250.dp)
    )
}

fun randomMovieSelector():List<MoviesCollection>{
    val listOfMovies= mutableListOf<MoviesCollection>()
    listOfMovies.add(MoviesCollection(R.drawable.anhihaltion))
    listOfMovies.add(MoviesCollection(R.drawable.sabrina))
    listOfMovies.add(MoviesCollection(R.drawable.bringh))
    listOfMovies.add(MoviesCollection(R.drawable.dayshirf))
    listOfMovies.add(MoviesCollection(R.drawable.heatstone))
    listOfMovies.add(MoviesCollection(R.drawable.stra))
    listOfMovies.add(MoviesCollection(R.drawable.bringh))
    listOfMovies.add(MoviesCollection(R.drawable.persuasion))
    listOfMovies.add(MoviesCollection(R.drawable.ice))
    listOfMovies.add(MoviesCollection(R.drawable.earth))
    listOfMovies.add(MoviesCollection(R.drawable.control))
    listOfMovies.add(MoviesCollection(R.drawable.anhihaltion))
    listOfMovies.add(MoviesCollection(R.drawable.s))
    listOfMovies.add(MoviesCollection(R.drawable.res))
    listOfMovies.add(MoviesCollection(R.drawable.thar))
    listOfMovies.add(MoviesCollection(R.drawable.devil))
    listOfMovies.add(MoviesCollection(R.drawable.how))
    listOfMovies.add(MoviesCollection(R.drawable.love))
    listOfMovies.add(MoviesCollection(R.drawable.match))
    listOfMovies.add(MoviesCollection(R.drawable.night))

    listOfMovies.shuffle()
    return listOfMovies
}

fun getRandomMovie(list:List<MoviesCollection>):MoviesCollection{
    return list.random()
}

data class MoviesCollection(
    val image:Int
)