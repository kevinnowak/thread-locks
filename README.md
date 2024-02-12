# Locking Threads - An Example

A quick demonstration on how to force threads using the ```java.util.concurrent.locks.ReadWriteLock``` interface to wait for exclusive access to the code.

There is a ```Teacher``` thread who writes code and ```Student``` threads which read the lines of code.

The implemented mechanism only allows editing or reading once at a time, meaning the students can't read the code while
the teacher is editing it and vice versa when students are reading the code, the teacher can't edit it.
