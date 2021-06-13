package commons;

import java.util.UUID;

public class UUIDGenerator implements IdGenerator{
    @Override
    public String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
