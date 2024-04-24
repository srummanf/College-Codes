import hashlib


def compute_md5(data):
    # Create an MD5 hash object
    md5 = hashlib.md5()

    # Update the hash object with the data
    md5.update(data)

    # Get the hexadecimal representation of the hash
    md5_hash = md5.hexdigest()

    return md5_hash


if __name__ == "__main__":
    # Example usage
    data = "Rumman"
    md5_hash = compute_md5(data.encode())
    print("MD5 hash of '{}' is: {}".format(data, md5_hash))
