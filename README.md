# lab_1

A concurrent implementation of LinkedList with Mutexes 
and RW Locks in Java.

The run.bat file would look something like this:

`java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI 
-XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise 
-jar .\lab_1.jar 1000 10000 0.99 0.005 0.005 65000 1 2000 false`

Take a close look at following part:

`-jar .\lab_1.jar 1000 10000 0.90 0.06 0.04 65000 1 2000 false`

The values represented are as follows
1. `1000`     ->    n
2. `10000`    ->    m
3. `0.90`     ->    mMember
4. `0.06`     ->    mInsert
5. `0.04`     ->    mDelete
6. `65000`    ->    bound       -> upper bound for the int values inserted into the list. Should be between 0 and (2**16 - 1)
7. `1`        ->    nThreads    -> number of threads to use
8. `2000`     ->    testRuns    -> repeat test runs
9. `false`    ->    allowStats  -> allow displaying statistics for each test run

The code is executed with Java 11 GraalVM implementation.

`-XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI
-XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise`

Above hyper parameters are to tune the JVM to use the 
latest JVMCI JIT compiler rather than existing C1/C2 
compilers in HotSpot VM.

Sample run.bat file is also included for experiment. 
The same file is used to analyse the differences 
between each List implementation.