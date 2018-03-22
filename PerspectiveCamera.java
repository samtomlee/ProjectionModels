public class PerspectiveCamera extends Camera
{   
    public Vector3D getVPN(){/*return the view plan normal vector*/
        return vpn;
    }

    protected Point3D projectionTransform(final Point3D p){
        //using the perspective projection formula (from the slides)
        double d = Math.abs(cop.z);
        double x = 0.0;
        double y = 0.0;
        if(d != 0){
            x = p.x/((p.z/d)+1);
            y = p.y/((p.z/d)+1);
        }   
        return new Point3D(x,y,0.0);
    }
    
    public PerspectiveCamera(double xmin_, double xmax_, double ymin_, double ymax_){
        super(xmin_,xmax_,ymin_,ymax_);
    }

    public void setupCOP(Point3D cop_){
        cop = cop_; 
    }     

    protected Point3D cameraTransform(final Point3D p){
        vuv.transform(m);
        cop.transform(m);
        return p;
    }

    public void setupUVN(Point3D vrp_, Vector3D vpn_, Vector3D vuv_){
        vrp = vrp_;
        vpn = vpn_;
        vuv = vuv_;
    }
    
    private Point3D cop=new Point3D(0,0,-4); //centre of projection
    
    private Matrix m=new Matrix(); //camera transformation matrix for perspectivve projection
    {
        m.setIdentity();
        m.m[2][2] = 0.0;
        double d = Math.abs(cop.z);
        m.m[3][2] = 1.0/d;
    }
    
    //view reference point: the origin of camera coordinating system
    private Point3D vrp=new Point3D(0,0,0); 
    
    //view plane normal (axis n) and the view up vector (axis v)
    private Vector3D vpn=new Vector3D(0,0,1), vuv=new Vector3D(0,1,0); 

}
