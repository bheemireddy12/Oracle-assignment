package java.oracle.devops;

import java.oracle.devops.nullchecks.NonNull;
import java.oracle.devops.nullchecks.NullHelpers;
import java.oracle.devops.nullchecks.Nullable;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BlockingQueue <T> {

    private @NonNull Queue<@NonNull T> internalQueue;
    
    private @NonNull Semaphore semaphore;
    
    private boolean end;

    public BlockingQueue() {
        internalQueue = new ArrayDeque<>();
        semaphore = new Semaphore(0, true);
    }
    

    public @Nullable T get() {
        T result = null;
        
        try {
            result = get(0);
        } catch (TimeoutException e) {
            // can't happen
        }
        
        return result;
    }
    
    public @Nullable T get(long timeout) throws TimeoutException {
        T result = null;
        
        boolean gotPermit = false;
        boolean waitSuccess = false;
        while (!waitSuccess) {
            try {
                if (timeout > 0) {
                    gotPermit = semaphore.tryAcquire(1, timeout, TimeUnit.MILLISECONDS);
                } else {
                    semaphore.acquire();
                    gotPermit = true;
                }
                waitSuccess = true;
            } catch (InterruptedException e) {
            }
        }
        
        if (!gotPermit) {
            throw new TimeoutException();
        }
        
        synchronized (internalQueue) {
            result = NullHelpers.maybeNull(internalQueue.poll());
        }
        
        return result;
    }
    
    public @Nullable T peek() {
        T result = null;
        
        try {
            result = peek(0);
        } catch (TimeoutException e) {
            
        }
        
        return result;
    }
    
    public @Nullable T peek(long timeout) throws TimeoutException {
        T result = null;
        
        boolean gotPermit = false;
        boolean waitSuccess = false;
        while (!waitSuccess) {
            try {
                if (timeout > 0) {
                    gotPermit = semaphore.tryAcquire(1, timeout, TimeUnit.MILLISECONDS);
                } else {
                    semaphore.acquire();
                    gotPermit = true;
                }
                waitSuccess = true;
            } catch (InterruptedException e) {
            }
        }
        
        if (!gotPermit) {
            throw new TimeoutException();
        }
        
        synchronized (internalQueue) {
            result = NullHelpers.maybeNull(internalQueue.peek());
            
            semaphore.release(); // release after we peeked the element
        }
        
        return result;
    }
    
    public void add(@NonNull T element) {
        synchronized (internalQueue) {
            
            if (end) {
                throw new IllegalStateException("Trying to add new elements while end() has already been called");
            }
            
            internalQueue.add(element);
            semaphore.release();
        }
    }
    
    /**
     * Signals that no more data is added after this call. This allows get() to return <code>null</code>
     * once all existing data has been read out.
     */
    public void end() {
        synchronized (internalQueue) {
            end = true;
            semaphore.release(Integer.MAX_VALUE / 2);
        }
    }
    
    public boolean isEnd() {
        synchronized (internalQueue) {
            return end;
        }
    }
    
    public int getCurrentSize() {
        synchronized (internalQueue) {
            return internalQueue.size();
        }
    }
    
}
