import java.awt.*;
import java.awt.image.BufferedImage;

public class Scene{

    private GObject[] obj;
    
    public Scene(String[] filename){
        obj = new GObject[filename.length];
        for(int i = 0; i < filename.length; i++){
            obj[i] = new GObject(filename[i]);
            //System.out.println(filename[i] + " added"); for debugging
        }
    }

    public void transform(Matrix m){
        for(int i = 0; i < obj.length; i++){
            obj[i].transform(m);
        }
    }

    public void draw(Camera c, Graphics g){
        Face[] faces;
        Point3D[] points;
        for(GObject b: obj){
            //make a new array for faces of an object for clarity
            faces = new Face[b.face.length];
            for(int i = 0; i < b.face.length; i++){
                faces[i] = b.face[i];
            }
            
            //create an array of all the points in front facing faces
            points = new Point3D[0];
            for(int j = 0; j < faces.length; j++){
                //seeing which face is the front face
                Vector3D v = c.getVPN();
                Point3D p1 = b.vertex[((faces[j]).index[0])];
                Point3D p2 = b.vertex[((faces[j]).index[1])];
                Point3D p3 = b.vertex[((faces[j]).index[2])];
                if(Point3D.isFrontFace(p1,p2,p3,v)){
                    //System.out.println("front face!"); for debugging
                    points = new Point3D[faces[j].index.length];
                    for(int k = 0; k < (faces[j].index).length; k++){
                        points[k] = b.vertex[(faces[j]).index[k]];
                    }
                    
                    //creating arrays of the x and y coordinates of a front face
                    int[] xVals = new int[points.length];
                    int[] yVals = new int[points.length];
                    for(int i = 0; i < points.length; i++){
                        xVals[i] = (int)(c.project(points[i])).x;
                        yVals[i] = (int)(c.project(points[i])).y;
                    }
                    
                    //displays the faces in their designated color
                    g.setColor(faces[j].color);
                    g.fillPolygon(xVals, yVals, points.length);
                }
            }
        }
    }

    public String toString(){
        String message = "Scene:\n";

        for(GObject go: obj){
            message += go.toString() + "\n";
        }
        
        return message;
    }
}
