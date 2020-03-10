import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//withOutDP();
		withDP();
	}
	public static void withDP() throws IOException
	{
		File f = new File("input.txt");
		List<String> lines = Files.readAllLines(Paths.get("input.txt"));
		int num = Integer.parseInt(lines.get(0));
		int num2 = num*num;
		int [][] matrix = new int[num+1][num+1];
		textFileToMatrix(lines, matrix);
		int biggestDoll = 0;
		Vector2D biggestcornerV;
		int[][] calculatedMatrix = new int[num+1][num+1]; 
		//calulatedMatrix( (2,3) + (3,1) - (2,1) ) + point(3,2) = calculated[3,2] = MONEY
		
		for(int i = 1 ; i<=num ; i++)
		{
			for(int j = 1 ; j<=num ; j++)
			{
				calculatedMatrix[i][j] = (calculatedMatrix[i-1][j] + calculatedMatrix[i][j-1] - (calculatedMatrix[i-1][j-1])) + matrix[i][j];
				if(calculatedMatrix[i][j]>biggestDoll)
				{
					biggestDoll = calculatedMatrix[i][j];
				}
			}
		}
		
		printMatrix(calculatedMatrix);
		System.out.println("MAX MONEY: "+biggestDoll);
		//printMatrix(matrix);
		//withOutDP();
		
	}
	public static void withOutDP() throws IOException
	{
		File f = new File("input.txt");
		List<String> lines = Files.readAllLines(Paths.get("input.txt"));
		int num = Integer.parseInt(lines.get(0));
		int num2 = num*num;
		int biggestDollar = 0;
		int biggestX = 0;
		int biggestY = 0;
		int orginX = 0;
		int orginY = 0;
		//Set<Vector2D> biggestSet;
		
		int[][] matrix = new int[num+1][num+1];
		
		textFileToMatrix(lines, matrix);
		
		//Done without Dynamic Programming.
		Bulldozer BD = new Bulldozer(num+10,matrix);
		
		for(int t = 0;t<num2+10;t++)
		{
			for(int th = 0;th<num2+10;th++)
			{
				for(int i = 0;i<num+10;i++)
				{
					for(int j = 0;j<num+10;j++)
					{
						if((i+BD.x)>num || (j+BD.y)>num)
						{
							break;
						}
						if(BD.calculateMoneyOfMatrix(i, j, matrix)>biggestDollar)
						{
							biggestDollar = BD.calculateMoneyOfMatrix(i, j, matrix);
							biggestX = BD.x;
							orginX = i+1;
							orginY = j+1;
							biggestY = BD.y;
							
						}
					}
				}
				BD.incX();
			}
			BD.zeroX();
			BD.incY();
		}

		//BD.calculateMoneyOfMatrix(2, 0, matrix);
		System.out.println("MONEY MONEY MONEY");
		System.out.println("Max money: " + biggestDollar);
		System.out.println("Orgin of Bulldozer: "+orginX + " and "+ orginY);
		System.out.println("Size of Bulldozer: "+biggestX + " and "+ biggestY);
		
		System.out.println("Point on matrix: "+orginX + " " + orginY);
		System.out.println("Point on matrix: "+(orginX+biggestX-1) + " "+ (biggestY+orginY-1));
		printMatrix(matrix);
		
		FileWriter write = new FileWriter("output.txt");
		write.write(orginX+" "+orginY+"\n");
		write.write((orginX+biggestX-1) + " "+ (biggestY+orginY-1));
		
		write.close();
	}
	public static void printMatrix(int matrix[][])
	{
		for(int i = 0 ; i< matrix.length ; i++)
		{
			for(int j = 0 ; j <matrix[0].length ;j++)
			{
				System.out.print(matrix[i][j]);
				System.out.print(" , ");
			}
			System.out.println();
		}
	}
	public static void textFileToMatrix(List<String> myLines, int[][] myMatrix)
	{
		String temp = "";
		int h = 1;
		int k = 1;
		for(int j = 1;j<myLines.size();j++)
		{
			for(int i = 0;i<myLines.get(j).length();i++)
			{
				if(myLines.get(j).toCharArray()[i]==' ')
				{
					myMatrix[h][k] = Integer.parseInt(temp.trim());
					k++;
					temp = "";	
				}
				else
				{
					temp += myLines.get(j).toCharArray()[i];
				}
			}
			k=1;
			h++;
		}
	}

}
