import static java.lang.Math.*;

public class Vector3D implements Cloneable{

    public double x,y,z;
    
    public Vector3D(double X, double Y, double Z){
        x = X;
        y = Y;
        z = Z;
    }

    public String toString(){
        String output = "Vector: (" + Double.toString(this.x) + ", " + Double.toString(this.y) + ", " + Double.toString(this.z) + ")";
        return output;
    }

    public Vector3D clone() throws CloneNotSupportedException{
        Vector3D clone = new Vector3D(this.x,this.y,this.z);
        return clone;
    }

    public double L2norm(){
        return sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
    }

    public double dotProduct(Vector3D v){
        return (this.x*v.x+this.y*v.y+this.z*v.z);
    }
    
    public Vector3D crossProduct(Vector3D v){
        double a1, a2, a3;

        a1 = this.y*v.z - this.z*v.y;
        a2 = this.z*v.x - this.x*v.z;
        a3 = this.x*v.y - this.y*v.x;

        return new Vector3D(a1, a2, a3);
    }

    public void normalize(){
        double denom = this.L2norm();
        this.x = this.x/denom;
        this.y = this.y/denom;
        this.z = this.z/denom;
    }

    public Vector3D transform(Matrix m){
        Matrix vec = new Matrix();
        vec.m[1][1] = 0.0;
        vec.m[2][2] = 0.0;
        vec.m[3][3] = 0.0;
        vec.m[0][0] = this.x;
        vec.m[1][0] = this.y;
        vec.m[2][0] = this.z;
        vec.m[3][0] = 1.0;

        Matrix res  = m.multiply(vec);

        return new Vector3D(res.m[0][0]/res.m[3][0], res.m[1][0]/res.m[3][0], res.m[2][0]/res.m[3][0]);
    }
}
