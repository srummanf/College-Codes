from sympy import randprime, mod_inverse


def generate_keypair():
    # Step 1: Choose two large prime numbers
    p = randprime(2**10, 2**12)
    print("Value of p :", p)
    q = randprime(2**10, 2**12)
    print("Value of q :", q)

    # Step 2: Compute n = p * q
    n = p * q

    # Step 3: Compute Euler's totient function φ(n)
    phi = (p - 1) * (q - 1)

    # Step 4: Choose e such that 1 < e < φ(n) and gcd(e, φ(n)) = 1
    e = 65537  # Common choice for e

    # Step 5: Compute d, the modular multiplicative inverse of e modulo φ(n)
    d = mod_inverse(e, phi)

    # Public key: (e, n)
    # Private key: (d, n)
    return ((e, n), (d, n))


def encrypt(public_key, plaintext):
    e, n = public_key
    # Convert each character to its ASCII value and encrypt
    ciphertext = [pow(ord(char), e, n) for char in plaintext]
    return ciphertext


def decrypt(private_key, ciphertext):
    d, n = private_key
    # Decrypt each character and convert back to ASCII
    plaintext = "".join([chr(pow(char, d, n)) for char in ciphertext])
    return plaintext


# Example usage
public_key, private_key = generate_keypair()


print("Public key:", public_key)
print("Private key:", private_key)

message = input("Enter plaintext: ")
print("Original message:", message)

encrypted_message = encrypt(public_key, message)
print("Encrypted message:", encrypted_message)

decrypted_message = decrypt(private_key, encrypted_message)
print("Decrypted message:", decrypted_message)
