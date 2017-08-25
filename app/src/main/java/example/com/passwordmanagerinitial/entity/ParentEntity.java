package example.com.passwordmanagerinitial.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class ParentEntity {

    private String name;
    private List<ChildEntity> children;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ChildEntity> children) {
        this.children = children;
    }

    public static class ChildEntity{
        private String name;

        public ChildEntity(String name) {
            this.name = name;
        }

        public ChildEntity() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
