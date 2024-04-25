from sympy import randprime, mod_inverse


p = randprime(2**10, 2**12)
print("Value of p:", p)

q = randprime(2**10, 2**12)
print("Value of q:", q)


n = p * q


phi = (p - 1) * (q - 1)


e = 65537  
print("Value of e:", e)


d = mod_inverse(e, phi)
print("Value of d:", d)



public_key = (e, n)
private_key = (d, n)
print("Public key:", public_key)
print("Private key:", private_key)


plaintext = input("Enter plaintext: ")
encrypted_message = [pow(ord(char), e, n) for char in plaintext]
print("Encrypted message:", encrypted_message)


decrypted_message = "".join([chr(pow(char, d, n)) for char in encrypted_message])
print("Decrypted message:", decrypted_message)
