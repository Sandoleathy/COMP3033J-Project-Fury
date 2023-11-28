package objects3D.models.animations;

public class Section4FOVChange {
    public boolean isAnimationFinished = false;
    public float FOV;
    public Section4FOVChange(float fov){
        this.FOV = fov;
    }
    public void update(){
        if(FOV <= 180){
            FOV += 1;
        }else{
            isAnimationFinished = true;
        }
    }
    public void updateReverse(){
        if(FOV >= 45){
            FOV--;
        }else {
            isAnimationFinished = true;
        }
    }
}
