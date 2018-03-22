import java.util.Scanner;
import java.io.*;
import java.awt.Color;

public class GObject{

    public Point3D[] vertex;
    public Face[] face;

    public GObject(Point3D[] v, Face[] f){
        vertex = v;
        face = f;
    }

    public GObject(String fileName){
        File text = new File(fileName);
        
        try{
            Scanner sc = new Scanner(text);

            //creating the vertex array
            int lenV = sc.nextInt();

            Point3D[] v = new Point3D[lenV];
            for(int i = 0; i < lenV; i++){
                double a = sc.nextDouble();
                double b = sc.nextDouble();
                double c = sc.nextDouble();
                Point3D p = new Point3D(a,b,c);
            
                v[i] = p;
            }

            //creating the face array
            int lenF = sc.nextInt();
    
            Face[] f = new Face[lenF];
    
            for(int j = 0; j < lenF; j++){
                int verts = sc.nextInt();
                int[] ind = new int[verts];
                
                for(int k = 0; k < verts; k++){
                    ind[k] = sc.nextInt();
                }
                
                double rVal = sc.nextDouble();
                double gVal = sc.nextDouble();
                double bVal = sc.nextDouble();
                
                Color col = new Color((float)rVal,(float)gVal,(float)bVal); 
            
                f[j] = new Face(ind, col);
            }
            sc.close();
            vertex = v;
            face = f;
            
            //System.out.println("File name: " + fileName + "\n" + this.toString()); for debugging
        }
        catch(FileNotFoundException ex){
            System.out.println("ERROR: File not found.");
        }
    }   

    public String toString(){
        String str = "Vertices: total = " + vertex.length + "\n";
    
        for(Point3D p : vertex){
            str += p.toString() + "\n";
        }

        str += "Faces: \n";

        for(Face f : face){
            str += f.toString() + "\n";
        }
        return str;
    }

    public void transform(Matrix m){
        for(int i = 0; i < vertex.length; i++){
            this.vertex[i] = (this.vertex[i]).transform(m);
        }
    }
}
