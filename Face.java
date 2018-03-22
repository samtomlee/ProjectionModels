import java.awt.Color;

public class Face{

	public int[] index;
	public Color color;
	
	public Face(int[] i, Color c){
		index = i;
		color = c;
	}

	public String toString(){
		String str = "Face with indexes: ";

		for(int i : index){
			str += i + " ";
		}

		str += "and color: " + color.toString();

		return str;
	}

}
