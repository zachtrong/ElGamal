# ElGamal

## Requirements
Java Zulu 11

Maven 3

## Build
`mvn clean javafx:jlink`

## Run
`./bin/elgamaljava`

### Key Generation
First, input key set or generate one for further execution
![Screen Shot 2021-10-12 at 02 05 10](https://user-images.githubusercontent.com/17959734/136843298-e12389ca-3ed5-4957-b869-09b391407847.png)
<p align = "center">
  <b>Figure 1:</b> <i>Options to input arbitary key set or let the program to generate one</i>
</p>

![Screen Shot 2021-10-12 at 02 09 00](https://user-images.githubusercontent.com/17959734/136843676-badabe0e-bc19-45ae-babd-6565e9147714.png)
<p align = "center">
  <b>Figure 2:</b> <i>Key set output of program key generation</i>
</p>


### Message Encryption
Second, for message encryption, input message in `UTF-8` string. Program will output cipher in `base64` format which encodes the cipher pair `(C, r)`. Users ,therefore, can subsequently press on `Encrypt` button to view `(C, r)` pair in decimal format.
![Screen Shot 2021-10-12 at 02 09 26](https://user-images.githubusercontent.com/17959734/136843732-22a28287-51ff-4ec3-ab46-66a2784ff7ca.png)
<p align = "center">
  <b>Figure 3:</b> <i>An arbitary message in UTF-8 string</i>
</p>

![Screen Shot 2021-10-12 at 02 09 32](https://user-images.githubusercontent.com/17959734/136843744-acfe89a9-1e1b-499a-badd-9ec127278618.png)
<p align = "center">
  <b>Figure 4:</b> <i>Cipher pair (C, r) in base64 format</i>
</p>

![Screen Shot 2021-10-12 at 02 09 39](https://user-images.githubusercontent.com/17959734/136843754-323268b9-9bf5-4bcc-b486-24dcead04aa6.png)
<p align = "center">
  <b>Figure 5:</b> <i>Cipher pair (C, r) in decimal format</i>
</p>

### Message Decryption
Third, for message decryption, input either cipher `(C, r)` pair in decimal format or in `base64` format. Press `Decrypt` button to start decrypt the cipher with the private key `p` and `x` on the `Key Generation` section.

![Screen Shot 2021-10-12 at 02 14 34](https://user-images.githubusercontent.com/17959734/136844243-2a8d6b28-403f-4cd0-8000-7e805e8bdca9.png)
<p align = "center">
  <b>Figure 5:</b> <i>Decrypt cipher pair (C, r) in base64 format</i>
</p>

![Screen Shot 2021-10-12 at 02 15 05](https://user-images.githubusercontent.com/17959734/136844287-26f00de2-fc98-4344-b34f-336f1579a704.png)
<p align = "center">
  <b>Figure 6:</b> <i>Decrypt cipher pair (C, r) in decimal format</i>
</p>

![Screen Shot 2021-10-12 at 02 15 35](https://user-images.githubusercontent.com/17959734/136844321-23de12b0-b427-4e94-aaf5-bf23c7781a8c.png)
<p align = "center">
  <b>Figure 7:</b> <i>Successfully decrypt cipher pair with provided private key p and x</i>
</p>
