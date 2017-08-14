package example.com.passwordmanagerinitial.entity;

/**
 * Created by Administrator on 2017/7/11.
 */

public class SearchListEntity {

    private String passDesc;

    public SearchListEntity(String passDesc) {
        this.passDesc = passDesc;
    }

    public String getPassDesc() {
        return passDesc;
    }

    public void setPassDesc(String passDesc) {
        this.passDesc = passDesc;
    }
}
