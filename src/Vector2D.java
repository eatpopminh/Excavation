
public class Vector2D {

	public int x;
	public int y;
	Vector2D(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public boolean equals(Object o)
	{
		Vector2D vec = (Vector2D) o;
		return this.x == vec.x && vec.y == this.y;
	}
	@Override
	public int hashCode()
	{
		return x+y;
	}
	public String toString()
	{
		//System.out.println("<" + x + "," + y +  ">");
		return "<" + x + "," + y +  ">";
	}
	
			
}
