import socket

# Create socket
server_socket = socket.socket()

# Get hostname and IP
host_name = socket.gethostname()
ip = socket.gethostbyname(host_name)

port = 8080

print("Welcome to the Chat Room\n")

# Bind socket
server_socket.bind((host_name, port))
print("Binding Successful!")
print("This is your IP:", ip)

# Enter name
name = input("Enter your name: ")

# Start listening
server_socket.listen(1)

# Accept connection
conn, addr = server_socket.accept()

print("Received connection from", addr[0])
print("Connection Established. Connected from:", addr[0])

# Receive client name
client_name = conn.recv(1024).decode()
print(client_name, "has connected.")

# Send server name
conn.send(name.encode())

# Chat loop
while True:
    message = input("Me: ")
    conn.send(message.encode())

    message = conn.recv(1024).decode()
    print(client_name, ":", message)
