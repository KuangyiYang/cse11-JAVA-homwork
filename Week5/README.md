## Project5: Cryptor
### Summary
The program focuses on using inheritance, abstract classes, handling simple exceptions, and handling command-line arguments. You will be creating a command-line java application that does very primitive encoding (encrypting) and decoding (decrypting). Please note that none of these algorithms should ever be thought of as good/safe/secure encryption algorithms.

There are three top-level classes: Cryptor, StreamPair, and CryptStream. Cryptor is the command line program. StreamPair creates an input/output pair of I/O streams, depending upon user input. CryptStream is an abstract class and is the superclass of the encoders/decoders. Your program will define three different encoders (and therefore subclasses of CryptStream) to do no encoding (plain), rotation encoding(rot13), and key-based XOR encoding (key).

### Files
Some Sample Files for PR5
- atw80d.txt        -- Jules Verne Around the World in 80 Days. A large file
- atw80d.rot13      -- Encoded with CSE11's version of rot13
- unknown-rot13-enc -- unknown file. If your rot13 decoder is working, it will obvious
- unknown-key-enc   -- encoded with key "keeler" using sum of bytes of the key string to create
                     the byte-sized key
                     
### Run Instruction
Make sure you in the top-level project directory 'hw/ '. Then run:
```
javac filename.java # compile doc
```
and
```
java filename # run program
```
