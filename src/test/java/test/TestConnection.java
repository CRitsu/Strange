package test;

import io.critsu.entities.User;

import java.util.List;

public interface TestConnection {
    int TestConnect();
    List<User> AllUsers();
}
