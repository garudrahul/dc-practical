import socket

# Create socket
client_socket = socket.socket()

# Server details
server_host = socket.gethostname()
ip = socket.gethostbyname(server_host)

port = 8080

print("Welcome to the Chat Room\n")

# Take input
server_host = input("Enter friend's IP address: ")
name = input("Enter your name: ")

# Connect to server
client_socket.connect((server_host, port))

# Send name
client_socket.send(name.encode())

# Receive server name
server_name = client_socket.recv(1024).decode()

print(server_name, "has joined...")

# Chat loop
while True:
    message = client_socket.recv(1024).decode()
    print(server_name, ":", message)

    message = input("Me: ")
    client_socket.send(message.encode())
