package java.oracle.devops.nullchecks;

public class NullHelpers {


    private NullHelpers() {
    }
    
    public static final <T> @NonNull T notNull(@Nullable T value, @Nullable String msg) throws AssertionError {
        if (value == null) {
            throw new AssertionError(msg);
        }
        return value;
    }
    
    public static final <T> @NonNull T notNull(@Nullable T value) throws AssertionError {
        if (value == null) {
            throw new AssertionError("Supplied value is null, but was expected not null");
        }
        return value;
    }
    
    public static final <T> @Nullable T maybeNull(T value) {
        return value;
    }
    
}