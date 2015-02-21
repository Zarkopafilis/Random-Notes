import socket 
import threading
import subprocess
import time
import atexit

print "GreestoryServer started"

def close_sock(socket):
    socket.close()

server = socket.socket(socket.AF_INET , socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
server.bind(("0.0.0.0" , 1235))
server.listen(5)
atexit.register(close_sock, server);

def handle_client(client_socket):
    request = client_socket.recv(1024)
    print request
    if(request == "get-version"):
        client_socket.send("1")
    elif(request == "get-groups"):
        client_socket.send("group0\ngroup1\n")
    client_socket.close()
	
while True:
    client,addr = server.accept();
    client_handler = threading.Thread(target=handle_client , args=(client,))
    client_handler.start()
