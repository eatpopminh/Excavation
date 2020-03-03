import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
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
		
		int[][] matrix = new int[num][num];
		
		textFileToMatrix(lines, matrix);
		
		//Done without Dynamic Programming.
		//I think this can be done with Dynamic Programming.
		Bulldozer BD = new Bulldozer(num,matrix);
		for(int t = 0;t<num2;t++)
		{
			for(int th = 0;th<num2;th++)
			{
				for(int i = 0;i<num-1;i++)
				{
					for(int j = 0;j<num-1;j++)
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
		int h = 0;
		int k = 0;
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
			k=0;
			h++;
		}
	}

}
