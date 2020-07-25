# Summary
A Java demo of secure login protocol with username and password using following technique(s):
- Random number generator
- Nonce
- Encryption Algorithms (AES, Blowfish, RSA… etc)
- Hash Algorithms (MD5, SHA… etc)

# Sequence Diagram
![picture](img/SequenceDiagram.png)

# Classes

## MainForm
Dummy UI to show login process;

## AuthenticationServiceClient
Client Interface of AuthenticationService, it used encrypt / hash feature and wrapped server call to ensure security on data transit level;
Also UUID can ensure reply attack.

## AuthenticationService
Dummy backend service to authenticate user. 

# Process
1. When form load, it will create AuthenticationServiceClient object;
2. AuthenticationServiceClient will get metadata from AuthneticationService in server, it will include correlation ID (Unique ID to identify user session process), UUID (Unique ID for each request), one-time password salt and service public key;
3. When user call authenticate() in AuthenticationServiceClient, it will alter message below before send to server to authneticate:
  1. Encrpyt login name by AuthenticationService public key;
  2. Salted plain password;
  3. Hash salted password;
4. AuthenticationService find related user, if found, it will compare with hashed password and decide it is match or not;
5. Send back result to caller;
