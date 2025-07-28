package com.example.codecraft_ap_04;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.codecraft_ap_04.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
 ActivityMainBinding binding ;
 private int player1_result = 0;
    private int player2_result = 0;
    private boolean gameActive = true;
    String winner;
    int activePlayer = 0 ;
    int []gameState = {2,2,2,2,2,2,2,2,2};
    int [][]winPosition = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView[]imgs_id ={binding.imageView0,binding.imageView1,binding.imageView2,binding.imageView3
                ,binding.imageView4,binding.imageView5,binding.imageView6,binding.imageView7,binding.imageView8};


        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameReset(gameState,imgs_id,activePlayer,player1_result,player2_result);
            }
        });


    }
    public void gameReset(int []gameState, ImageView[] imageView , int activePlayer , int player1_result, int player2_result){
        for(ImageView img:imageView){
            img.setImageResource(0);
        }
        for (int i =0 ;i<gameState.length;i++){
            gameState[i]=2;
        }
        activePlayer=0;
        player1_result=0;
        player1_result=0;

        binding.status.setText("");
        gameActive = true;

    }
    public void playerTap(View view){
        ImageView[]imgs_id ={binding.imageView0,binding.imageView1,binding.imageView2,binding.imageView3
                ,binding.imageView4,binding.imageView5,binding.imageView6,binding.imageView7,binding.imageView8};

        ImageView view1=(ImageView) view;
        int imgTag =Integer.parseInt(view1.getTag().toString());
        if(!gameActive){
            gameReset(gameState,imgs_id,activePlayer,player1_result,player2_result);
            gameActive =true;
        }else{
           gameState[imgTag]=activePlayer;
           if(activePlayer==0){
               view1.setImageResource(R.drawable.x);
               binding.status.setText("Player2's turn , tap to play");
               activePlayer=1;
               checkIfWin();


           }else {

                   view1.setImageResource(R.drawable.o);
               binding.status.setText("Player1's turn , tap to play");
               activePlayer=0;
               checkIfWin();



           }

        }
        checkIfWin();


    }
    public void checkIfWin() {
        // Check for a win
        for (int[] winPositions : winPosition) {
            if (gameState[winPositions[0]] != 2 &&
                    gameState[winPositions[0]] == gameState[winPositions[1]] &&
                    gameState[winPositions[1]] == gameState[winPositions[2]]) {

                gameActive = false;

                if (gameState[winPositions[0]] == 0) {
                    binding.status.setText("X is the winner!");
                    player1_result++;
                    activePlayer=0;
                    gameActive= false;
                } else {
                    binding.status.setText("O is the winner!");
                    player2_result++;
                    activePlayer=0;
                    gameActive= false;
                }
                return;
            }
        }


        boolean draw = true;
        for (int state : gameState) {
            if (state == 2) {
                draw = false;
                break;
            }
        }

        if (draw) {
            gameActive = false;
            binding.status.setText("It's a Draw!");
        }
    }




}