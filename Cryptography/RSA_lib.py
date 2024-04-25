from sympy import randprime, mod_inverse

# Step 1: Choose two large prime numbers
p = randprime(2**10, 2**12)
print("Value of p:", p)
q = randprime(2**10, 2**12)
print("Value of q:", q)

# Step 2: Compute n = p * q
n = p * q

# Step 3: Compute Euler's totient function φ(n)
phi = (p - 1) * (q - 1)

# Step 4: Choose e such that 1 < e < φ(n) and gcd(e, φ(n)) = 1
e = 65537  # Common choice for e
print("Value of e:", e)

# Step 5: Compute d, the modular multiplicative inverse of e modulo φ(n)
d = mod_inverse(e, phi)
print("Value of d:", d)

# Public key: (e, n)
# Private key: (d, n)
public_key = (e, n)
private_key = (d, n)
print("Public key:", public_key)
print("Private key:", private_key)

# Encryption
plaintext = input("Enter plaintext: ")
encrypted_message = [pow(ord(char), e, n) for char in plaintext]
print("Encrypted message:", encrypted_message)

# Decryption
decrypted_message = "".join([chr(pow(char, d, n)) for char in encrypted_message])
print("Decrypted message:", decrypted_message)
