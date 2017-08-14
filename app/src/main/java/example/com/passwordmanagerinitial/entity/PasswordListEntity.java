package example.com.passwordmanagerinitial.entity;

/**
 * Created by Administrator on 2017/6/29.
 */

public class PasswordListEntity {

    private String passDescribe;
    private int ImageId;

    public PasswordListEntity(String passDescribe, int imageId) {
        this.passDescribe = passDescribe;
        ImageId = imageId;
    }


    public PasswordListEntity(String passDescribe) {
        this.passDescribe = passDescribe;
    }

    public PasswordListEntity() {
    }

    public String getPassDescribe() {
        return passDescribe;
    }

    public void setPassDescribe(String passDescribe) {
        this.passDescribe = passDescribe;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }
}
