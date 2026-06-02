package util;

import java.util.UUID;

public class IdGenerator {

    public static String generateCommitId() {
        return UUID.randomUUID()
                .toString()
                .substring(0, 8);
    }
}