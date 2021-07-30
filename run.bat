@echo off
rem case 1
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.99 0.005 0.005 65000 1 2000 false
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.99 0.005 0.005 65000 2 2000 false
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.99 0.005 0.005 65000 4 2000 false
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.99 0.005 0.005 65000 8 2000 false
rem case 2
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.90 0.05 0.05 65000 1 2000 false
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.90 0.05 0.05 65000 2 2000 false
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.90 0.05 0.05 65000 4 2000 false
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.90 0.05 0.05 65000 8 2000 false
rem case 3
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.50 0.25 0.25 65000 1 2000 false
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.50 0.25 0.25 65000 2 2000 false
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.50 0.25 0.25 65000 4 2000 false
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.CompilerConfiguration=enterprise -jar .\lab_1.jar 1000 10000 0.50 0.25 0.25 65000 8 2000 false