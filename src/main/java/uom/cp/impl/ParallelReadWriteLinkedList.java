package uom.cp.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This is a parallel implementation of linked list with mutex of form {@link ReentrantReadWriteLock}
 * <p>
 * For easiness and understanding, rather than designing from scratch,
 * I'm going to extend existing {@link SerialLinkedList} and cover the methods using
 * parallel read write lock implementation
 */
public class ParallelReadWriteLinkedList extends SerialLinkedList {

    // fair is set to true to give equal opportunity to all threads
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    // extracting both ReadLock and WriteLock to simplify usage
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    @Override
    public boolean insert(int data) {
        writeLock.lock();
        try {
            return super.insert(data);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean member(int data) {
        readLock.lock();
        try {
            return super.member(data);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean delete(int data) {
        writeLock.lock();
        try {
            return super.delete(data);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public String toString() {
        readLock.lock();
        try {
            return super.toString();
        } finally {
            readLock.unlock();
        }
    }
}
