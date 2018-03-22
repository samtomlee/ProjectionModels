/* THIS CLASS IS MORE LIKE PARALLEL PROJECTION
 *
 */

public class Camera
{
    public Vector3D getVPN(){
        /*return a vector that points towards the viewer. Used for face orientation*/
        return new Vector3D(0.0,0.0,1.0);
    }

    //3D to 3D process
    protected Point3D cameraTransform(final Point3D p){
        return p;
    }

    //3D to 2D process
    protected Point3D projectionTransform(final Point3D p){
        Matrix proj = new Matrix();
        proj.m[2][2] = 0.0;
        return p.transform(proj);
    } 

    //2D to 2D process
    private final Point3D viewportTransform(final Point3D p){
        double x1,y1;
        
        x1 = ax + bx*p.x;
        y1 = ay + by*p.y;

        return new Point3D(x1,y1,0.0);
    }

    public final Point3D project(final Point3D p){
        Point3D temp=cameraTransform(p);
        temp=projectionTransform(temp);
        return viewportTransform(temp);
    }

    public Camera(double xmin_, double xmax_, double ymin_, double ymax_){
        xmin = xmin_;
        xmax = xmax_;
        ymin = ymin_;
        ymax = ymax_;
    }

    public void setViewport(int width, int height){/*calculate ax, bx, ay, by*/
        double dUx = width;
        double dVx = xmax-xmin;
        double dUy = height;
        double dVy = ymax-ymin;

        bx = dUx/dVx;
        by = dUy/dVy;
        ax = 0 - bx*(xmin);
        ay = 0 - by*(ymin);
    }

    public String toString(){/* Make it look nice to save your debugging time! */
        String message = "Camera:\nxmin = " + xmin + "\nxmax = " + xmax + "\nymin = " + ymin
                + "\nymax = " + ymax + "\n";

        return message;
    }

    private double xmin, xmax, ymin, ymax;
    private double fcp, bcp;  //NOT USED: front & back clippling planes
    private double ax, bx, ay, by;
}
