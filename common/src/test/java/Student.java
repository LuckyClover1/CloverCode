import com.clover.common.base.*;

import java.util.List;

public class Student extends BaseEntity {
    private String name;

    private List<Student> friends;
    public List<Student> getFriends() {
        return friends;
    }

    public void setFriends(List<Student> friends) {
        this.friends = friends;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, List<Student> fs){
        this.name = name;
        this.friends = fs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return "name:"+this.name+"\t"+"friends:"+this.friends;
    }
}
