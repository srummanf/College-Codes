# Generate a KeyStore
keytool -genkey -alias serverKey -keyalg RSA -keystore keystore.jks -keypass changeit -storepass changeit -validity 360 -keysize 2048

# Export the certificate from the KeyStore
keytool -export -alias serverKey -keystore keystore.jks -file server.cer -storepass changeit

# Create a TrustStore and import the server certificate
keytool -import -alias serverCert -file server.cer -keystore truststore.jks -storepass changeit
