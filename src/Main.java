import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

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
		Vector2D biggestCornerV = null;
		Vector2D biggestCornerV2 = null;
		int[][] calculatedMatrix = new int[num+1][num+1]; 
		
		
		//calulatedMatrix( (2,3) + (3,1) - (2,1) ) + point(3,2) = calculated[3,2] = MONEY
		int p = 1;
		int o = 1;
//		for(int h=0;h<=num2;h++)
//		{
			for(int i = p ; i<=num ; i++)
			{
				for(int j = o ; j<=num ; j++)
				{
					calculatedMatrix[i][j] = (calculatedMatrix[i-1][j] + calculatedMatrix[i][j-1] - (calculatedMatrix[i-1][j-1])) + matrix[i][j];
					if(calculatedMatrix[i][j]>biggestDoll)
					{
						biggestDoll = calculatedMatrix[i][j];
						biggestCornerV = new Vector2D(p,o);
						biggestCornerV2 = new Vector2D(i,j);
					}
				}
			//}
			//clearMatrix(calculatedMatrix,num);
			calculatedMatrix = new int[num+1][num+1];
			if(o==num)
			{
				p++;
				o=1;
			}
			else
			{
				o++;
			}
			
		}
		
		System.out.println("MAX MONEY: "+biggestDoll);
		System.out.println(biggestCornerV.toString());
		System.out.println(biggestCornerV2.toString());
		
		FileWriter write = new FileWriter("output.txt");
		write.write(biggestCornerV.x + " " + biggestCornerV.y + "\n");

		write.write(biggestCornerV2.x + " " + biggestCornerV2.y);
		
		write.close();
		
	}
	public static void clearMatrix(int[][] matrix,int num)
	{
		for(int i = 0 ; i<num+1 ; i++)
		{
			for(int j = 0 ; j<num+1 ; j++)
			{
				matrix[i][j] = 0;
			}
		}
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
		Bulldozer BD = new Bulldozer(num,matrix);
		
		for(int t = 0;t<num2+10;t++)
		{
			for(int th = 0;th<num2+10;th++)
			{
				for(int i = 0;i<num+10;i++)
				{
					for(int j = 0;j<num+10;j++)
					{
						if((i+BD.x)>num+1 || (j+BD.y)>num+1)
						{
							break;
						}
						if(BD.calculateMoneyOfMatrix(i, j, matrix)>biggestDollar)
						{
							biggestDollar = BD.calculateMoneyOfMatrix(i, j, matrix);
							biggestX = BD.x;
							orginX = i;
							orginY = j;
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
		
//		System.out.println("Point on matrix: "+orginX + " " + orginY);
//		System.out.println("Point on matrix: "+(orginX+biggestX-2) + " "+ (biggestY+orginY-2));
		printMatrix(matrix);
		
		FileWriter write = new FileWriter("output.txt");
		write.write(orginX+" "+orginY+"\n");
		write.write((biggestX) + " "+ (biggestY));
		
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
