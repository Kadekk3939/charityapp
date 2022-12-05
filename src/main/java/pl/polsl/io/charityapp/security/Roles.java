package pl.polsl.io.charityapp.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Roles {
    private final List<String> authorities = Arrays.asList(
            "Admin",
            "Moderator",
            "Average_user",
            "Guest",
            "Below_average_user");

    // lower level == higher rank
    ArrayList<String> getAuthoritiesOnLevel(int level) {
        if (level < 0 || level > authorities.size()) {
            return new ArrayList<>();
        }
        return new ArrayList<> (authorities.subList(0, level));
    }

    public List<String> getAuthorities() {
        return authorities;
    }

}