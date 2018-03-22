import java.awt.*;
import javax.swing.*;
import static java.lang.Math.*;

public class ParallelAnimator extends Animator
{
  private static final String[] files={"cube.dat", "pyramid.dat"};
  public ParallelAnimator()
  {
    super();
    
    scene=new Scene(files);
    setupCamera();
  }

  protected void setupCamera()
  {
    camera= new Camera(-5,5,-5,5);
  }

  protected void animate(Graphics g)
  {
    camera.setViewport(getWidth(),getHeight());
    
    if(g==null || scene==null || camera==null)
      return;

    Matrix mX=new Matrix(), mY=new Matrix(), mZ=new Matrix();
    mX.setRotationX(-PI/11);
    mY.setRotationY(PI/13);
    mZ.setRotationZ(PI/17); 
    scene.transform(mZ.multiply(mY.multiply(mX))); 

    scene.draw(camera,g);
  }

  public static void main(String[] args) 
  {
    new ParallelAnimator().loop();
  }

  private Scene scene;
  protected Camera camera;
}
