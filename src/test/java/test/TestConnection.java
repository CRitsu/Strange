package test;

import io.critsu.entities.User;

import java.util.List;

public interface TestConnection {
    public int TestConnect();
    public List<User> AllUsers();
}
