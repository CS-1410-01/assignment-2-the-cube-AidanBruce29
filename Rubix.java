import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Rubix {

    static String[][] cube = {{
        "R","R","R",
        "R","R","R",
        "R","R","R"
    },
    {
        "B","B","B",
        "B","B","B",
        "B","B","B"
    },
    {
        "O","O","O",
        "O","O","O",
        "O","O","O"
    },
    {
        "G","G","G",
        "G","G","G",
        "G","G","G"
    },
    {
        "Y","Y","Y",
        "Y","Y","Y",
        "Y","Y","Y"
    },
    {
        "W","W","W",
        "W","W","W",
        "W","W","W"
    }};


    class edgeFace {
        public int CurrentFace;
        int[] edge1 = new int[4];   //(1,0,1,2)
        int[] edge2 = new int[4];   //(0,0,1,2)
        int[] edge3 = new int[4];
        int[] edge4 = new int[4];
    

    public edgeFace(int face){
        CurrentFace = face;

        switch(face){
            case 0:
            edge1[0]=1;
            edge1[1]=2;
            edge1[2]=5;
            edge1[3]=8;

            edge2[0]=4;
            edge2[1]=2;
            edge2[2]=5;
            edge2[3]=8;

            edge3[0]=3;
            edge3[1]=0;
            edge3[2]=3;
            edge3[3]=6;

            edge4[0]=5;
            edge4[1]=2;
            edge4[2]=5;
            edge4[3]=8;
            break;

            case 1:
            edge1[0]=2;
            edge1[1]=2;
            edge1[2]=5;
            edge1[3]=8;

            edge2[0]=4;
            edge2[1]=6;
            edge2[2]=7;
            edge2[3]=8;

            edge3[0]=3;
            edge3[1]=0;
            edge3[2]=3;
            edge3[3]=6;

            edge4[0]=5;
            edge4[1]=0;
            edge4[2]=1;
            edge4[3]=2;
            break;

            case 2:
            edge1[0]=3;
            edge1[1]=2;
            edge1[2]=5;
            edge1[3]=8;

            edge2[0]=4;
            edge2[1]=0;
            edge2[2]=3;
            edge2[3]=6;

            edge3[0]=3;
            edge3[1]=0;
            edge3[2]=3;
            edge3[3]=6;

            edge4[0]=5;
            edge4[1]=0;
            edge4[2]=3;
            edge4[3]=6;
            break;

            case 3:
            edge1[0]=0;
            edge1[1]=2;
            edge1[2]=5;
            edge1[3]=8;

            edge2[0]=4;
            edge2[1]=0;
            edge2[2]=1;
            edge2[3]=2;

            edge3[0]=2;
            edge3[1]=0;
            edge3[2]=3;
            edge3[3]=6;

            edge4[0]=5;
            edge4[1]=6;
            edge4[2]=7;
            edge4[3]=8;
            break;

            case 4:
            edge1[0]=2;
            edge1[1]=0;
            edge1[2]=1;
            edge1[3]=2;

            edge2[0]=3;
            edge2[1]=0;
            edge2[2]=1;
            edge2[3]=2;

            edge3[0]=0;
            edge3[1]=0;
            edge3[2]=1;
            edge3[3]=2;

            edge4[0]=1;
            edge4[1]=0;
            edge4[2]=1;
            edge4[3]=2;
            break;

            case 5:
            edge1[0]=2;
            edge1[1]=6;
            edge1[2]=7;
            edge1[3]=8;

            edge2[0]=1;
            edge2[1]=6;
            edge2[2]=7;
            edge2[3]=8;

            edge3[0]=0;
            edge3[1]=6;
            edge3[2]=7;
            edge3[3]=8;

            edge4[0]=3;
            edge4[1]=6;
            edge4[2]=7;
            edge4[3]=8;
            break;
        }
    }
}

    public void turnFace(int index, String direction){
        edgeFace eface = new edgeFace(index);

        String[][] copy = new String[6][9];

       for(int i=0;i<6;i++){
        for(int j=0;j<9;j++){
            copy[i][j] = cube[i][j];
        }
       }

       switch(direction){
        case"c":
        cube[eface.CurrentFace][0] = copy[eface.CurrentFace][2];
        cube[eface.CurrentFace][1] = copy[eface.CurrentFace][5];
        cube[eface.CurrentFace][2] = copy[eface.CurrentFace][8];
        cube[eface.CurrentFace][3] = copy[eface.CurrentFace][1];
        cube[eface.CurrentFace][5] = copy[eface.CurrentFace][7];
        cube[eface.CurrentFace][6] = copy[eface.CurrentFace][0];
        cube[eface.CurrentFace][7] = copy[eface.CurrentFace][3];
        cube[eface.CurrentFace][8] = copy[eface.CurrentFace][6];

        //e1
        cube[eface.edge1[0]][eface.edge1[1]] = copy[eface.edge4[0]][eface.edge4[1]];
        cube[eface.edge1[0]][eface.edge1[2]] = copy[eface.edge4[0]][eface.edge4[2]];
        cube[eface.edge1[0]][eface.edge1[3]] = copy[eface.edge4[0]][eface.edge4[3]];

        //e2
        cube[eface.edge2[0]][eface.edge2[1]] = copy[eface.edge1[0]][eface.edge1[1]];
        cube[eface.edge2[0]][eface.edge2[2]] = copy[eface.edge1[0]][eface.edge1[2]];
        cube[eface.edge2[0]][eface.edge2[3]] = copy[eface.edge1[0]][eface.edge1[3]];

        //e3
        cube[eface.edge3[0]][eface.edge3[1]] = copy[eface.edge2[0]][eface.edge2[1]];
        cube[eface.edge3[0]][eface.edge3[2]] = copy[eface.edge2[0]][eface.edge2[2]];
        cube[eface.edge3[0]][eface.edge3[3]] = copy[eface.edge2[0]][eface.edge2[3]];

        //e4
        cube[eface.edge4[0]][eface.edge4[1]] = copy[eface.edge3[0]][eface.edge3[1]];
        cube[eface.edge4[0]][eface.edge4[2]] = copy[eface.edge3[0]][eface.edge3[2]];
        cube[eface.edge4[0]][eface.edge4[3]] = copy[eface.edge3[0]][eface.edge3[3]];
        break;

        case"cc":
        cube[eface.CurrentFace][0] = copy[eface.CurrentFace][6];
        cube[eface.CurrentFace][1] = copy[eface.CurrentFace][3];
        cube[eface.CurrentFace][2] = copy[eface.CurrentFace][0];
        cube[eface.CurrentFace][3] = copy[eface.CurrentFace][7];
        cube[eface.CurrentFace][5] = copy[eface.CurrentFace][1];
        cube[eface.CurrentFace][6] = copy[eface.CurrentFace][8];
        cube[eface.CurrentFace][7] = copy[eface.CurrentFace][5];
        cube[eface.CurrentFace][8] = copy[eface.CurrentFace][2];

        //e1
        cube[eface.edge1[0]][eface.edge1[1]] = copy[eface.edge2[0]][eface.edge2[1]];
        cube[eface.edge1[0]][eface.edge1[2]] = copy[eface.edge2[0]][eface.edge2[2]];
        cube[eface.edge1[0]][eface.edge1[3]] = copy[eface.edge2[0]][eface.edge2[3]];

        //e2
        cube[eface.edge2[0]][eface.edge2[1]] = copy[eface.edge3[0]][eface.edge3[1]];
        cube[eface.edge2[0]][eface.edge2[2]] = copy[eface.edge3[0]][eface.edge3[2]];
        cube[eface.edge2[0]][eface.edge2[3]] = copy[eface.edge3[0]][eface.edge3[3]];

        //e3
        cube[eface.edge3[0]][eface.edge3[1]] = copy[eface.edge4[0]][eface.edge4[1]];
        cube[eface.edge3[0]][eface.edge3[2]] = copy[eface.edge4[0]][eface.edge4[2]];
        cube[eface.edge3[0]][eface.edge3[3]] = copy[eface.edge4[0]][eface.edge4[3]];

        //e4
        cube[eface.edge4[0]][eface.edge4[1]] = copy[eface.edge1[0]][eface.edge1[1]];
        cube[eface.edge4[0]][eface.edge4[2]] = copy[eface.edge1[0]][eface.edge1[2]];
        cube[eface.edge4[0]][eface.edge4[3]] = copy[eface.edge1[0]][eface.edge1[3]];
        break;

       }
    }

public void displayCube() {
    int ind=0;
        for(int x=0; x<6;x++){
            for(int y=0;y<3; y++){
                for(int z=0; z<3;z++){
                    System.out.print(cube[x][ind++]);
                }
                System.out.println();
            }
            ind=0;
            System.out.println();
        }
}

public static void main(String[] args) 
throws IOException
{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    Rubix RubixCube = new Rubix();

    boolean argsCheck = false;
    int argsRunIndex = 0;
    if(args.length>0){
        argsCheck = true;
    }

    boolean proceed = true;

    while(proceed){
        String input;
        if(argsCheck){
            input = reader.readLine();
        }else{
            if(argsRunIndex == args.length){
                argsCheck = false;
                input = "s";
            }else{
                input = args[argsRunIndex];
                argsRunIndex++;
            }
        }
        switch(input){
            case "u":
            RubixCube.turnFace(4,"c");
            RubixCube.displayCube();
            break;
            case "d":
            RubixCube.turnFace(5,"c");
            RubixCube.displayCube();
            break;
            case "r":
            RubixCube.turnFace(0,"c");
            RubixCube.displayCube();
            break;
            case "l":
            RubixCube.turnFace(2,"c");
            RubixCube.displayCube();
            break;
            case "f":
            RubixCube.turnFace(1,"c");
            RubixCube.displayCube();
            break;
            case "b":
            RubixCube.turnFace(3,"c");
            RubixCube.displayCube();
            break;
            case "u'":
            RubixCube.turnFace(4,"cc");
            RubixCube.displayCube();
            break;
            case "d'":
            RubixCube.turnFace(5,"cc");
            RubixCube.displayCube();
            break;
            case "r'":
            RubixCube.turnFace(0,"cc");
            RubixCube.displayCube();
            break;
            case "l'":
            RubixCube.turnFace(2,"cc");
            RubixCube.displayCube();
            break;
            case "f'":
            RubixCube.turnFace(1,"cc");
            RubixCube.displayCube();
            break;
            case "b'":
            RubixCube.turnFace(3,"cc");
            RubixCube.displayCube();
            break;
            case "s":
            break;
            case "q":
            proceed = false;
            break;
        }
    }
    }
}