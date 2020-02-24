import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Bulldozer {

	
	public int x;
	public int y;
	public int matrixSize = 0;
	public int[][] matrix;
	Bulldozer(int matrixSize, int[][] myMatrix)
	{
		x = 2;
		y = 1;
		this.matrixSize = matrixSize;
		matrix = myMatrix;
	}
	public void incX()
	{
		x++;
	}
	public void incY()
	{
		y++;
	}
	public int calculateMoneyOfMatrix(int currentI, int currentJ, int[][] myMatrix)
	{
		int money = 0;
		Set<Vector2D> mySet = new HashSet<Vector2D>();

		findAllPoints(currentI, currentJ,mySet);
		
		System.out.println(mySet);
		for(Vector2D v : mySet)
		{
			Vector2D temp = new Vector2D(matrixSize,matrixSize);
			if(v.x>matrixSize || v.y>matrixSize)
			{
				return 0;
			}
		}
		money = addingFromSet(mySet);
		
		return money;
	}
	public void findAllPoints(int currentI, int currentJ,Set<Vector2D> mySet)
	{
		Vector2D v = new Vector2D(currentI, currentJ);
		
		mySet.add(v);
		for(int i = 0;i<x;i++)
		{
			v = new Vector2D(currentI+i,currentJ);
			mySet.add(v);
		}
		for(int i = 0;i<y;i++)
		{
			v = new Vector2D(currentI,currentJ+1);
			mySet.add(v);
		}
		for(int i = 0;i<x;i++)
		{
			for(int j =0;j<y;j++)
			{
				v = new Vector2D(currentI+i,currentJ+j);
				mySet.add(v);
			}
		}
	}
	public int addingFromSet(Set<Vector2D> s)
	{
		int money = 0;
		for(Vector2D a : s)
		{
			money += matrix[a.x][a.y];
			System.out.println(matrix[a.x][a.y]);
		}
		System.out.println("Money: " + money);
		return money;
	}
	
	
	
}
