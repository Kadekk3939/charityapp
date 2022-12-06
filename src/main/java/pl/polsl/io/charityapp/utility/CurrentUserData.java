package pl.polsl.io.charityapp.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.polsl.io.charityapp.model.User;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurrentUserData {
    private static Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static User getCurrentUser() {
        Object principal = getPrincipal();

        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    public static Integer getCurrentUserId() {
        Object principal = getPrincipal();

        if (principal instanceof User) {
            return ((User)principal).getId();
        }
        return null;
    }

    public static String getCurrentUserLogin() {
        Object principal = getPrincipal();

        if (principal instanceof User) {
            return ((User)principal).getUsername();
        }
        return null;
    }

    public static String getCurrentUserRole() {
        Object principal = getPrincipal();

        if (principal instanceof User) {
            return ((User)principal).getAuthorities().toString();
        }
        return null;
    }
}