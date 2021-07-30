package uom.cp.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is a parallel implementation of linked list with single mutex in the form of {@link ReentrantLock}
 * <p>
 * For easiness and understanding, rather than designing from scratch,
 * I'm going to extend existing {@link SerialLinkedList} and cover the methods using
 * parallel mutex implementation
 */
public class ParallelMutexLinkedList extends SerialLinkedList {

    // fair is set to true to give equal opportunity to all threads
    private final Lock lock = new ReentrantLock(true);

    @Override
    public boolean insert(int data) {

        // pre-protocol
        lock.lock();

        // critical section
        try {
            return super.insert(data);

            // post-protocol
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean member(int data) {
        lock.lock();
        try {
            return super.member(data);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean delete(int data) {
        lock.lock();
        try {
            return super.delete(data);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        lock.lock();
        try {
            return super.toString();
        } finally {
            lock.unlock();
        }
    }
}
