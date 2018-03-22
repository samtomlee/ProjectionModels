public class Point3D{
	public double x,y,z;
	
	public Point3D(double X, double Y, double Z){
		this.x = X;
		this.y = Y;
		this.z = Z;
	}

	public double distance(Point3D p){
		double distX = (this.x-p.x);
		double distY = (this.y-p.y);
		double distZ = (this.z-p.z);
		return Math.sqrt(distX*distX + distY*distY + distZ*distZ);
	}
	
	public String toString(){
		return "Point: (" + (this.x) + ", " + (this.y) + ", " + (this.z) + ")";
	}

	public Point3D transform(Matrix m){
		Matrix point = new Matrix();
		point.m[1][1] = 0.0;
		point.m[2][2] = 0.0;
		point.m[3][3] = 0.0;
		point.m[0][0] = this.x;
		point.m[1][0] = this.y;
		point.m[2][0] = this.z;
		point.m[3][0] = 1.0;
		
		Matrix res = m.multiply(point);

		return new Point3D(res.m[0][0], res.m[1][0], res.m[2][0]);
	}

	public Vector3D vector(Point3D p){/* return the vector that is between this point and p.*/
		return new Vector3D(p.x-this.x, p.y-this.y, p.z-this.z);
	}
	
	public static Vector3D faceNormal(Point3D p1, Point3D p2, Point3D p3){
		Vector3D v1 = p1.vector(p2);
		Vector3D v2 = p1.vector(p3);
		
		return v1.crossProduct(v2);	
	}

	public static boolean isFrontFace(Point3D p1, Point3D p2, Point3D p3, Vector3D vpn){
		Vector3D norm = faceNormal(p1,p2,p3);
		if(norm.dotProduct(vpn) > -0.1) //changed from 0 for perspective animator
			return true;
		return false;
	}
}
